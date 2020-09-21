package web.service;

import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;


public interface RoleService {
    Iterable<Role> getAllRoles();
}
