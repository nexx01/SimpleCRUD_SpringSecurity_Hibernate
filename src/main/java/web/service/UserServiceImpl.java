package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import web.dao.UserDao;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    @Transactional(readOnly = true)
    @Override
    public User findUserbyId(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserDetailsByLogin(String s) {
        return userRepository.findByLogin(s);
    }
}
