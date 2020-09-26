package web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;

//@Controller
//@RequestMapping("/user")
public class UserController {

    // Получаем Авторизованного Юзера и передаем в Модель
    @RequestMapping(method = RequestMethod.GET)
    public String index(@AuthenticationPrincipal User authuser, Model model) {
        model.addAttribute("authuser",authuser);
        return "user/personalPage";
    }
}