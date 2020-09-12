package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
