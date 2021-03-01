package com.aj.registerserver;

public class RegisterClient {

    private final String instanceId;

    public RegisterClient(String instanceId) {
        this.instanceId = instanceId;
    }

    public void start() {
        HttpSender httpSender = new HttpSender();

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setIp("192.168.2.12");
        registerRequest.setPort(8080);
        registerRequest.setServiceName("inventory-service");
        registerRequest.setInstanceId(instanceId);

        RegisterResponse registerResponse = httpSender.register(registerRequest);
        System.out.println("注册结果=" + registerResponse);
        System.out.println();

        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setInstanceId(instanceId);
        while (true) {
            HeartbeatResponse heartbeat = httpSender.heartbeat(heartbeatRequest);
            System.out.println("心跳结果=" + heartbeat);
            System.out.println();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
