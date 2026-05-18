package hu.pte.mik.prog4.zh2_2026.jaas;

import hu.pte.mik.prog4.zh2_2026.repository.RoleRepository;
import hu.pte.mik.prog4.zh2_2026.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AuthenticationModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;

    private String username;
    private boolean loginSucceeded = false;

    private UserPrincipal userPrincipal;

    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Nincs CallbackHandler konfigurálva!");
        }

        NameCallback nameCallback = new NameCallback("Username: ");
        PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);

        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());

            // Adatbázis ellenőrzés
            UserRepository userRepo = new UserRepository();
            String hashedPassword = userRepo.getPasswordByUsername(username);

            // BCrypt jelszó-ellenőrzés a PDF-ben megadott elvárás szerint
            if (hashedPassword != null && BCrypt.checkpw(password, hashedPassword)) {
                loginSucceeded = true;
                return true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }

        throw new LoginException("Hibás felhasználónév vagy jelszó!");
    }

    @Override
    public boolean commit() throws LoginException {
        if (!loginSucceeded) {
            return false;
        }

        // Felhasználó hozzáadása a session-höz
        userPrincipal = new UserPrincipal(username);
        subject.getPrincipals().add(userPrincipal);

        // Jogosultságok (Szerepkörök) lekérése és hozzáadása az adatbázisból
        RoleRepository roleRepo = new RoleRepository();
        List<String> roles = roleRepo.getRolesByUsername(username);

        for (String role : roles) {
            subject.getPrincipals().add(new RolePrincipal(role));
        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        logout();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        if (userPrincipal != null) {
            subject.getPrincipals().remove(userPrincipal);
        }
        loginSucceeded = false;
        return true;
    }

}
