package web.dao;

import web.model.Role;

public interface  RoleDao {
    Role findByName(String name);
    void delete(Role role);
    Role saveRole(Role role);
    Role findById(Long Id);
}
