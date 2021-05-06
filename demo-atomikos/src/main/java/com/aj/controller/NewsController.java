package com.aj.controller;

import com.aj.domain.User;
import com.aj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class NewsController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/addNews")
    public String addNews() {
        userService.addUser(new User());
        return "success";
    }

}
