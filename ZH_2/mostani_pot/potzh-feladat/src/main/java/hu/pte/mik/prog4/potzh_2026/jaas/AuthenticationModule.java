package hu.pte.mik.prog4.potzh_2026.jaas;

import hu.pte.mik.prog4.potzh_2026.entity.UserEntity;
import hu.pte.mik.prog4.potzh_2026.repository.RoleRepository;
import hu.pte.mik.prog4.potzh_2026.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.List;
import java.util.Map;

public class AuthenticationModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean loginSucceeded = false;
    private String username;

    private final UserRepository userRepository = new UserRepository();
    private final RoleRepository roleRepository = new RoleRepository();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password", false);

        try {
            callbackHandler.handle(callbacks);
            String providedUsername = ((NameCallback) callbacks[0]).getName();
            String providedPassword = new String(((PasswordCallback) callbacks[1]).getPassword());

            // 1. Felhasználó keresése az adatbázisban
            UserEntity user = userRepository.findByUsername(providedUsername);

            // 2. Jelszó ellenőrzése BCrypt segítségével
            if (user != null && BCrypt.checkpw(providedPassword, user.getPassword())) {
                this.username = providedUsername;
                this.loginSucceeded = true;
                return true;
            }
        } catch (Exception e) {
            throw new LoginException("Hiba a bejelentkezés során: " + e.getMessage());
        }

        throw new LoginException("Hibás felhasználónév vagy jelszó!");
    }

    @Override
    public boolean commit() throws LoginException {
        if (!loginSucceeded) return false;

        // Felhasználó hozzáadása
        subject.getPrincipals().add(new UserPrincipal(username));

        // Jogosultságok (szerepkörök) lekérdezése és hozzáadása
        List<String> roles = roleRepository.findRolesByUsername(username);
        for (String role : roles) {
            subject.getPrincipals().add(new RolePrincipal(role));
        }
        return true;
    }

    @Override
    public boolean abort() { return false; }

    @Override
    public boolean logout() {
        subject.getPrincipals().clear();
        return true;
    }
}