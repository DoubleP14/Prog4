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

public class AuthenticationModule implements LoginModule {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private CallbackHandler handler;
    private Subject subject;
    private String login;
    private List<RoleEntity> userGroups;

    public AuthenticationModule() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }
    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        try {
            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("login");
            callbacks[1] = new PasswordCallback("password", false);

            this.handler.handle(callbacks);
            String username = ((NameCallback) callbacks[0]).getName();
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            if (username != null) {
                UserEntity user = this.userRepository.findByUsername(username);

                // JAVÍTÁS: Ellenőrizzük, hogy létezik-e a felhasználó, mielőtt lekérjük a jelszavát!
                if (user != null && user.getPass() != null) {
                    BCrypt.Result result = BCrypt.verifyer()
                            .verify(password, user.getPass());
                    if (result.verified) {
                        this.login = username;
                        this.userGroups = this.roleRepository.findRolesByUser(user);
                        return true;
                    }
                }
            }
            throw new LoginException("Invalid username or password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals()
                .add(new UserPrincipal(this.login));
        this.userGroups.stream()
//                       .map(roleEntity -> new RolePrincipal(roleEntity.getCode()))
                .map(RoleEntity::getCode)
                .map(RolePrincipal::new)
                .forEach(this.subject.getPrincipals()::add);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals()
                .clear();
        return true;
    }

}
