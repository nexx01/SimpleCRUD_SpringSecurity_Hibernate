package web.dao;

import web.model.User;
import web.model.UserAuthority;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    void delete(User User);

    List<User> getAllUsers();

    void cleanUsersTable();

    User findUserbyId(Long Id);

    UserAuthority getUserByLogin(String login);
}
