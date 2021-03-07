package com.aj.registerclient;

import java.util.concurrent.atomic.AtomicInteger;

public class HttpSender {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public RegisterResponse register(RegisterRequest registerRequest) {
        int i = atomicInteger.incrementAndGet();
        System.out.println("正在发送注册=" + registerRequest);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setCode(200);
        registerResponse.setMessage("success");
        return registerResponse;
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) {
        System.out.println("正在发送心跳=" + heartbeatRequest.getInstanceId());
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        heartbeatResponse.setCode(200);
        heartbeatResponse.setMessage("success");
        return heartbeatResponse;
    }
}
