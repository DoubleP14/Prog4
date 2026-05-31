package hu.pte.mik.prog4.potpotzh_2026.jaas;

import org.mindrot.jbcrypt.BCrypt;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthenticationModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean loginSucceeded = false;
    private UserPrincipal userPrincipal;
    private List<RolePrincipal> rolePrincipals = new ArrayList<>();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Nincs CallbackHandler megadva");
        }

        NameCallback nameCallback = new NameCallback("Felhasználónév: ");
        PasswordCallback passwordCallback = new PasswordCallback("Jelszó: ", false);

        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            String username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());

            // Adatbázis ellenőrzés
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/PotPotZH_security_MariaDB");

            try (Connection conn = ds.getConnection()) {
                // 1. Jelszó ellenőrzés BCrypt-tel
                String userQuery = "SELECT id, jelszo FROM potpotzh_security_database.felhasznalo WHERE felhasznalonev = ?";
                int userId = -1;
                try (PreparedStatement ps = conn.prepareStatement(userQuery)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String hashedDbPassword = rs.getString("jelszo");
                            if (BCrypt.checkpw(password, hashedDbPassword)) {
                                loginSucceeded = true;
                                userId = rs.getInt("id");
                                userPrincipal = new UserPrincipal(username);
                            } else {
                                throw new LoginException("Hibás jelszó!");
                            }
                        } else {
                            throw new LoginException("Nem található ilyen felhasználó!");
                        }
                    }
                }

                // 2. Szerepkörök (Roles) betöltése, ha sikeres a login
                if (loginSucceeded) {
                    String roleQuery = "SELECT s.kod FROM potpotzh_security_database.szerepkor s " +
                            "JOIN potpotzh_security_database.felhasznalo_szerepkor fs ON s.id = fs.szerepkor_id " +
                            "WHERE fs.felhasznalo_id = ?";
                    try (PreparedStatement psRole = conn.prepareStatement(roleQuery)) {
                        psRole.setInt(1, userId);
                        try (ResultSet rsRole = psRole.executeQuery()) {
                            while (rsRole.next()) {
                                rolePrincipals.add(new RolePrincipal(rsRole.getString("kod")));
                            }
                        }
                    }
                }
            }
            return loginSucceeded;
        } catch (Exception e) {
            throw new LoginException("Hiba a bejelentkezés során: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (!loginSucceeded) {
            return false;
        }
        subject.getPrincipals().add(userPrincipal);
        subject.getPrincipals().addAll(rolePrincipals);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        logout();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().removeAll(rolePrincipals);
        userPrincipal = null;
        rolePrincipals.clear();
        loginSucceeded = false;
        return true;
    }
}
