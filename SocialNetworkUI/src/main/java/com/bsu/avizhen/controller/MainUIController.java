package com.bsu.avizhen.controller;

import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.rest.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser"})
public class MainUIController {

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Avizhen") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/")
    public String index(ModelMap model) {
        initSession(model);
        return "index";
    }

    @RequestMapping("/login")
    public String login(ModelMap model) {
        initSession(model);
        return "login";
    }

    @RequestMapping("/friends")
    public String friends(ModelMap model) {
        initSession(model);
        return "friends";
    }

    @RequestMapping("/colleagues")
    public String colleagues(ModelMap model) {
        initSession(model);
        return "colleagues";
    }

    @RequestMapping("/chats")
    public String chats(ModelMap model) {
        initSession(model);
        return "chats";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(ModelMap model, @RequestParam("username") String login, @RequestParam("password") String password) {
        User loginUser = userRestService.loginUser(login, password);
        System.out.println(loginUser.getId());
        System.out.println(loginUser.getLogin());
        model.addAttribute("currentUser", loginUser);
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutUser(ModelMap model) {
        model.addAttribute("currentUser", new User());
        return "index";
    }

    private void initSession(ModelMap model) {
        if(!model.containsAttribute("currentUser")) {
            model.addAttribute("currentUser", new User());

        }
    }

}
