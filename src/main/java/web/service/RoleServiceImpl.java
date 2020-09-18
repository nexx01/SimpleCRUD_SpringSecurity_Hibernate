package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.RoleRepository;
import web.model.Role;
import web.model.User;

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
