package web.service;

import web.model.User;

import java.util.List;


public interface UserService {
    public void delete(long id);

    void saveUser(User user);

    Iterable<User> getAllUsers();

    User findUserbyId(Long Id);

    User findByEmail(String s);
}
