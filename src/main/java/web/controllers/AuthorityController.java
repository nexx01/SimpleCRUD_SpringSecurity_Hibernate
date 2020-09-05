package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorityController {

    @GetMapping("/")
    public String home(){return "login";}

 /*   @GetMapping("/user")
    public String user(){return "/users";}*/

    @GetMapping("/admin")
    public String c(){return "/admin";}

    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/supervisor")
    public String supervisor(){return "/supervisor";}
}
