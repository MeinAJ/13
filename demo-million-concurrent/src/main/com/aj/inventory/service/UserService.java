package com.aj.inventory.service;

import com.aj.inventory.model.User;

public interface UserService {

    User getCachedUserInfo();

    User getUserInfo();

}
