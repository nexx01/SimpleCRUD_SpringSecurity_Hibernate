package web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;

//Загрузка данных
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    //private final RoleDao roleDao;

    @Autowired
    public SetupDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
       // this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

/*
            roleDao.saveRole(userRole);
            roleDao.saveRole(adminRole);
*/

        User user = new User("user", "user", true, "user", "user", "user");
        User admin = new User("admin", "admin", true, "admin", "admin", "admin");
        admin.addRole(adminRole);
        admin.addRole(userRole);
        user.addRole(userRole);
        userRepository.save(user);
        userRepository.save(admin);
    }
}
