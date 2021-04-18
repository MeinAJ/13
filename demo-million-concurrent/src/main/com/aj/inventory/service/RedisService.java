package com.aj.inventory.service;

public interface RedisService {

    void set(String key, String value);

    String get(String key);

}
