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
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthenticationModule implements LoginModule {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private Subject subject;
    private CallbackHandler callbackHandler;
    private String login;
    private List<String> usergroups;

    public AuthenticationModule(){
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        try {
            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("login");
            callbacks[1] = new PasswordCallback("password", false);

            this.callbackHandler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            if (name != null) {
                UserEntity userEntity = this.userRepository.findByUsername(name);
                if (userEntity != null) {
                    BCrypt.Result verify = BCrypt.verifyer().verify(password, userEntity.getPassword());
                    if (verify.verified) {
                        this.login = name;

                        this.usergroups = this.roleRepository.findRolesByUser(userEntity)
                                .stream()
                                .map(RoleEntity::getCode)
                                .collect(Collectors.toList());
                        return true;
                    }
                }
            }
            throw new LoginException("Hibás felhasználónév vagy jelszó!");
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(new UserPrincipal(this.login));
        this.usergroups.stream().map(RolePrincipal::new)
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
