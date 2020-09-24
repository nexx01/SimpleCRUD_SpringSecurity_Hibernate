package web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//Загрузка данных
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    /*в зависимости от того, сколько контекстов вы настроили в своем приложении,
    ContextRefreshedEvent может запускаться несколько раз. И мы хотим, чтобы
     установка была выполнена только один раз.*/
    boolean alreadySetup = false;


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }


        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role userRole = createRoleIfNotFound("ROLE_USER");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        createUserIfNotFound("user@user.com", "user", true, "user", "user",
                new ArrayList<>(Arrays.asList(userRole)));
        createUserIfNotFound("user2@user.com", "user", true, "user2", "user",
                new ArrayList<>(Arrays.asList(userRole)));

        createUserIfNotFound("admin@admin.com", "admin", true, "admin", "admin",
                new ArrayList<>(Arrays.asList(adminRole, userRole)));

    }


    @Transactional
    Role createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }
        return role;
    }


    @Transactional
    User createUserIfNotFound(final String email, final String password, boolean enable,
                              final String firstName, final String lastName, final Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User(email,password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEnabled(enable);
            // user.setPassword(passwordEncoder.encode(password));
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

}
