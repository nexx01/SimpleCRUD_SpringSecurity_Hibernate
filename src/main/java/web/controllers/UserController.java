package web.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/users")

public class UserController {


    UserService userService;

    @Autowired
    public void setSingerService( UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String list(ModelMap model){
        List<User> users=userService.getAllUsers();
        model.addAttribute("users",users);
        return "users/list";
    }

/*   @RequestMapping(value = "{/id}",method=RequestMethod.GET)
    public String show (@PathVariable("id") Long id,ModelMap model){
        User user=userService.findUserbyId(id);
        model.addAttribute("user",user);
        return "users/show";
   }*/

/*    @RequestMapping(value = "/delete?id={id}",method=RequestMethod.GET)
    public String deleteCustomerForm(@PathVariable("id") Long id,ModelMap model) {
        User user = userService.findUserbyId(id);
        if(user==null){
           *//* "User with id"+id+"is not exist";*//*
        }
        userService.delete(user);
        return "/users/list";
    }*/

/*   @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       User user= userService.findUserbyId(id);
        userService.delete(user);
    }*/

    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        User user= userService.findUserbyId(id);
        userService.delete(user);
        return "redirect:/users";
    }

/*    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam long id, Model model){
        User user= userService.findUserbyId(id);
        model.addAttribute("user",user);
        return "redirect:/users";
}*/

/*    @RequestMapping("/newUser")
    public String newCustomerForm(ModelMap model) {
        User user = new User();
        model.put("user", user);
        return "users/newUser";
    }*/
/*
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }*/


    @GetMapping("/addUser")
    public String addUserView(Model model) {
        model.addAttribute("newUser", new User());
        return "users/addUser";
    }

    @PostMapping("/addUser")
    public String addUserSubmint(@ModelAttribute @Valid User user,BindingResult bindingResult, Model model) {
        userService.saveUser(user);
        model.addAttribute("newUser", user);
        return "redirect:/users";
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam long id, Model model){
        User user= userService.findUserbyId(id);
        model.addAttribute("editUser",user);
        return "users/editUser";
    }

    @PostMapping("/editUser")
    public String editUserSubmint(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("editUser", user);
        return "redirect:/users";
    }


}
