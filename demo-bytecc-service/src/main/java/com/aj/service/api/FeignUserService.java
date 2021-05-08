package com.aj.service.api;

import com.aj.user.api.api.UserApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "demo-user")
public interface FeignUserService extends UserApi {

}
