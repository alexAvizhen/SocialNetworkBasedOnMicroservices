package com.bsu.avizhen.controller;

import com.bsu.avizhen.rest.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private final UserRestService userRestService;

    @Autowired
    public TestController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    @RequestMapping(value = {"/messages/{login}"}, method = RequestMethod.GET)
    public Object getUserById(@PathVariable("login") String login) {
        return userRestService.getUserByLogin(login);
    }
}
