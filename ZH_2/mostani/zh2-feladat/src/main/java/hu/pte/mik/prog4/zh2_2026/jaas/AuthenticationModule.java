package hu.pte.mik.prog4.zh2_2026.jaas;

import org.mindrot.jbcrypt.BCrypt;
import hu.pte.mik.prog4.zh2_2026.entity.RoleEntity;
import hu.pte.mik.prog4.zh2_2026.entity.UserEntity;
import hu.pte.mik.prog4.zh2_2026.repository.RoleRepository;
import hu.pte.mik.prog4.zh2_2026.repository.UserRepository;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthenticationModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;

    private UserPrincipal userPrincipal;
    private final List<RolePrincipal> rolePrincipals = new ArrayList<>();
    private boolean loginSucceeded = false;

    // A jelenlegi vizsga Repository-jai
    private final UserRepository userRepository = new UserRepository();
    private final RoleRepository roleRepository = new RoleRepository();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Nincs CallbackHandler konfigurálva!");
        }

        NameCallback nameCallback = new NameCallback("username");
        PasswordCallback passwordCallback = new PasswordCallback("password", false);

        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            String username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());

            // 1. Felhasználó lekérése adatbázisból
            UserEntity user = userRepository.findByUsername(username);

            if (user != null) {
                // 2. BCrypt jelszó ellenőrzése (A jbcrypt könyvtárral!)
                if (BCrypt.checkpw(password, user.getPassword())) {
                    this.loginSucceeded = true;
                    this.userPrincipal = new UserPrincipal(username);

                    // 3. Szerepkörök lekérése (ID alapján) és előkészítése
                    List<RoleEntity> roles = roleRepository.findRolesByUserId(user.getId());
                    for (RoleEntity role : roles) {
                        this.rolePrincipals.add(new RolePrincipal(role.getCode()));
                    }
                    return true;
                }
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Hiba a CallbackHandler-ben: " + e.getMessage());
        }

        // Ha idáig eljut, hibás volt a felhasználónév vagy a jelszó
        throw new LoginException("Hibás felhasználónév vagy jelszó!");
    }

    @Override
    public boolean commit() throws LoginException {
        if (!loginSucceeded) {
            return false;
        }
        // Sikeres belépés esetén rögzítjük a usert és a jogait a Session-ben
        subject.getPrincipals().add(userPrincipal);
        subject.getPrincipals().addAll(rolePrincipals);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        loginSucceeded = false;
        userPrincipal = null;
        rolePrincipals.clear();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().removeAll(rolePrincipals);

        loginSucceeded = false;
        userPrincipal = null;
        rolePrincipals.clear();
        return true;
    }
}