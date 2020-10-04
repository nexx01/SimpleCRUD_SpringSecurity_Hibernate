package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
