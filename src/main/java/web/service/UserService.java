package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void delete(User user);

    void saveUser(User user);

    List<User> getAllUsers();

    User findUserbyId(Long Id);
}
