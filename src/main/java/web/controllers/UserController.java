package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    UserService userService;

    @Autowired
    public void setSingerService(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }



    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        User user = userService.findUserbyId(id);
        userService.delete(user);
        return "redirect:/users";
    }

    // Получение и проверка данных введеных в форму
    @GetMapping("/addUser")
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "users/addUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/addUser")
    public String addUserSubmint(@Valid @ModelAttribute User user, BindingResult errors, Model model) {
        if (errors.hasErrors() | user.getFirstName().equals("") | user.getLastName().equals("") | user.getEmail().equals("")) {
            model.addAttribute("User", user);
            model.addAttribute("errorMessage", "FILL ALL FIELD");
            return "users/addUser";
        } else {
            userService.saveUser(user);
            return "redirect:/users";
        }
    }


    // Переход форму для редактирования
    @GetMapping("/editUser")
    public String editUser(@RequestParam long id, Model model) {
        User user = userService.findUserbyId(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "users/editUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/editUser")
    public String editUserSubmint(@Valid @ModelAttribute User user, BindingResult errors, Model model) {
        if (errors.hasErrors() | user.getFirstName().equals("") | user.getLastName().equals("") | user.getEmail().equals("")) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", "FILL ALL FIELD");
            return "users/editUser";
        } else {
            userService.saveUser(user);
            return "redirect:/users";
        }
    }
}