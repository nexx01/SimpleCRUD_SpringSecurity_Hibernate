package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*import javax.validation.Valid;*/

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    //@Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @RequestMapping(method = RequestMethod.GET)
    public String list(@AuthenticationPrincipal User authuser,
                       Model model) {


        model.addAttribute("users",  userService.getAllUsers());
        model.addAttribute("allRoles",  roleService.getAllRoles());
        model.addAttribute("authuser",authuser);
        return "admin/list";
    }

    @GetMapping("/list")
    public String list2(/*@AuthenticationPrincipal User user,
                        ModelMap model*/) {
//        Iterable<User> users = userService.getAllUsers();
//
//        model.addAttribute("users", users);
//        model.addAttribute(user);
        return "redirect:/admin";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        User user = userService.findUserbyId(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    // Получение и проверка данных введеных в форму
    @GetMapping("/addUser")
    public String addUserView(@AuthenticationPrincipal User authuser, Model model) {
        model.addAttribute("authuser", authuser);
//        model.addAttribute("user",new User());
        Iterable<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin/addUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/addUser")
    public String addUserSubmint( @ModelAttribute User user,@RequestParam("role[]") String[] newUserRoles, BindingResult errors, Model model) {
        User userFromDb = userService.findByEmail(user.getEmail());
        if (userFromDb != null) {
            model.addAttribute("message", "UserExist!");
            return "admin/addUser";
        }
        if (errors.hasErrors()) {
            model.addAttribute("User", user);
            return "admin/addUser";
        }
        user.setEnabled(true);
        Collection userRoles=Arrays.stream(newUserRoles)
                .map(roleService::findByRoleName)
                .collect(Collectors.toList());
        user.setRoles(userRoles);
//    new ArrayList<>(Arrays.asList(roleService.findByRoleName(newUserRoles[0])))));

        userService.saveUser(user);
        return "redirect:/admin";

    }


    // Переход форму для редактирования
    @GetMapping("/editUser")
    public String editUser(@AuthenticationPrincipal User authuser,@RequestParam long id, Model model) {
        User user = userService.findUserbyId(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("authuser",authuser);
        return "admin/editUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/editUser")
    public String editUserSubmint(User user,@RequestParam("role[]") String[] newUserRoles, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/editUser";
        } else {


            Collection userRoles=Arrays.stream(newUserRoles)
                    .map(roleService::findByRoleName)
                    .collect(Collectors.toList());
            user.setRoles(userRoles);
            userService.saveUser(user);
            return "redirect:/admin";
        }
    }
}