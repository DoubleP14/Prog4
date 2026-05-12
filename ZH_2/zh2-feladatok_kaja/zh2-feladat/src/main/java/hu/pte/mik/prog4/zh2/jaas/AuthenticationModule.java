package hu.pte.mik.prog4.zh2.jaas;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import hu.pte.mik.prog4.zh2.repository.RoleRepository;
import hu.pte.mik.prog4.zh2.repository.UserRepository;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthenticationModule implements LoginModule {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private CallbackHandler callbackHandler;
    private Subject subject;
    private String login;
    private List<String> userGroups; // Beszédesebb név

    public AuthenticationModule() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }

    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        try {
            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("login");
            callbacks[1] = new PasswordCallback("password", true);

            this.callbackHandler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            // A Szaktársad memóriabiztos megoldása (nincs String konverzió)
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            if (name != null) {
                UserEntity user = this.userRepository.findByUsername(name);

                // Meg kell vizsgálni, hogy létezik-e egyáltalán a user!
                if (user != null) {
                    // Közvetlen char[] ellenőrzés
                    BCrypt.Result verify = BCrypt.verifyer().verify(password, user.getPassword());

                    if (verify.verified) {
                        this.login = name;
                        this.userGroups = this.roleRepository.findRolesByUser(user)
                                .stream()
                                .map(RoleEntity::getCode)
                                .collect(Collectors.toList());
                        return true;
                    }
                }
            }

            throw new LoginException("Hibás Authentikáció");

            // A Te helyes, szabványkövető hibakezelésed (LoginException csomagolás)
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Hiba az authentikáció során: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(new UserPrincipal(this.login));
        this.userGroups.stream().map(RolePrincipal::new)
                .forEach(this.subject.getPrincipals()::add);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().clear();
        return true;
    }
}