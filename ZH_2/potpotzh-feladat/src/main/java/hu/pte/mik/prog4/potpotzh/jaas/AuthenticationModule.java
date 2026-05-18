package hu.pte.mik.prog4.potpotzh.jaas;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.pte.mik.prog4.potpotzh.entity.RoleEntity;
import hu.pte.mik.prog4.potpotzh.entity.UserEntity;
import hu.pte.mik.prog4.potpotzh.repository.RoleRepository;
import hu.pte.mik.prog4.potpotzh.repository.UserRepository;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.List;
import java.util.Map;

public class AuthenticationModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private UserPrincipal userPrincipal;
    private List<RoleEntity> roles;
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
            String username = ((NameCallback) callbacks[0]).getName();
            char[] passwordArray = ((PasswordCallback) callbacks[1]).getPassword();
            String password = new String(passwordArray);

            UserEntity user = userRepository.findByUsername(username);

            // Jelszó ellenőrzése Bcrypt-tel
            if (user != null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                userPrincipal = new UserPrincipal(username);
                roles = roleRepository.findRolesByUserId(user.getId());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (userPrincipal != null) {
            subject.getPrincipals().add(userPrincipal);
            for (RoleEntity role : roles) {
                subject.getPrincipals().add(new RolePrincipal(role.getCode()));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException { return false; }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().clear();
        return true;
    }
}