package com.aj.user.api.api;

import com.aj.user.api.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/")
public interface UserApi {

    @PostMapping(value = "/addUser")
    void addUser(@RequestBody User news);

}
