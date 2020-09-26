package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    //@Autowired
    public AdminRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> read() {
        final Iterable<User> users = userService.getAllUsers();

        return users != null && !users.isEmpty()

    }


}
