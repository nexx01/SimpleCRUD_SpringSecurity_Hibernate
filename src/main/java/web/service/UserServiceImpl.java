package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }


    @Transactional(readOnly = true)
    @Override
    public User findUserbyId(Long id) {
        return userDao.findUserbyId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserDetailsByLogin(String s) {
        return userDao.getUserByLogin(s);
    }
}
