package com.aj.inventory.controller;

import com.aj.inventory.model.User;
import com.aj.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public User getUserInfo() {
        return userService.getCachedUserInfo();
    }
}
