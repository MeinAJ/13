package com.aj.shardingjdbc.news.controller;

import com.aj.shardingjdbc.news.domain.User;
import com.aj.shardingjdbc.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/add")
    public String addNewsVisit(@RequestParam(value = "time", required = false) Long time) {
        User user = new User();
        user.setAge(1);
        user.setName("aj");
        userService.addUser(user);
        return "success";
    }

    @GetMapping(value = "/get")
    public String addNewsVisit() {
        List<User> list = userService.getUser();
        System.out.println("size=" + list.size());
        System.out.println(list);
        return "success";
    }

}
