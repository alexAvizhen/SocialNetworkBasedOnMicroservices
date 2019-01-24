package com.bsu.avizhen.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommunityController {

    @RequestMapping(value = {"/community/{id}"}, method = RequestMethod.GET)
    public Object getCommunityById(@PathVariable("id") Integer id) {
        return "Test community with id " + id;
    }
}
