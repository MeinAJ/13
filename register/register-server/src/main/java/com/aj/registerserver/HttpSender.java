package com.aj.registerserver;

public class HttpSender {

    public RegisterResponse register(RegisterRequest registerRequest) {
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
