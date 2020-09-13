package web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;

//Загрузка данных
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final UserDao userDao;
    //private final RoleDao roleDao;

    @Autowired
    public SetupDataLoader(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
       // this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

      /*  roleDao.saveRole(userRole);
        roleDao.saveRole(adminRole);*/
        User user = new User("user", "user", true, "user", "user", "user");
        User admin = new User("admin", "admin", true, "admin", "admin", "admin");
        admin.addRole(adminRole);
        admin.addRole(userRole);
        user.addRole(userRole);
        userDao.saveUser(user);
        userDao.saveUser(admin);
    }
}
