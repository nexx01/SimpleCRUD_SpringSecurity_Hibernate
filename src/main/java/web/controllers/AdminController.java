package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

/*import javax.validation.Valid;*/

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        User user = userService.findUserbyId(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    // Получение и проверка данных введеных в форму
    @GetMapping("/addUser")
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/addUser")
    public String addUserSubmint(/*@Valid*/ @ModelAttribute User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("User", user);
            return "admin/addUser";
        } else {
            userService.saveUser(user);
            return "redirect:/admin";
        }
    }


    // Переход форму для редактирования
    @GetMapping("/editUser")
    public String editUser(@RequestParam long id, Model model) {
        User user = userService.findUserbyId(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        return "admin/editUser";
    }

    // Получение и проверка данных введеных в форму
    @PostMapping("/editUser")
    public String editUserSubmint(/*@Valid*/ @ModelAttribute User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/editUser";
        } else {
            userService.saveUser(user);
            return "redirect:/admin";
        }
    }
}