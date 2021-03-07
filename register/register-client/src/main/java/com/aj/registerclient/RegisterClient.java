package com.aj.registerclient;

public class RegisterClient {

    HttpSender httpSender = new HttpSender();

    private final String instanceId;

    private final String SERVICE_NAME = "inventory-service";

    private final Integer PORT = 8080;

    private final String IP = "192.168.2.12";

    private final HeartBeatWorker heartBeatWorker;

    public volatile boolean isRunning;

    public RegisterClient(String instanceId) {
        this.instanceId = instanceId;
        this.isRunning = true;
        this.heartBeatWorker = new HeartBeatWorker();
    }

    public void shutdown() {
        this.isRunning = false;
        this.heartBeatWorker.interrupt();
    }

    public void start() throws InterruptedException {
        RegisterWorker registerWorker = new RegisterWorker();
        registerWorker.setName("RegisterWorkerThread");
        registerWorker.start();
        registerWorker.join();
        System.out.println();

        heartBeatWorker.setName("HeartBeatWorkerThread");
        heartBeatWorker.start();
    }

    class RegisterWorker extends Thread {

        @Override
        public void run() {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setIp(IP);
            registerRequest.setPort(PORT);
            registerRequest.setServiceName(SERVICE_NAME);
            registerRequest.setInstanceId(instanceId);

            RegisterResponse registerResponse = httpSender.register(registerRequest);
            System.out.println(Thread.currentThread().getName() + "线程,注册结果=" + registerResponse);
        }

    }

    class HeartBeatWorker extends Thread {

        @Override
        public void run() {
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setInstanceId(instanceId);
            heartbeatRequest.setServiceName(SERVICE_NAME);

            while (isRunning) {
                HeartbeatResponse heartbeat = httpSender.heartbeat(heartbeatRequest);
                System.out.println(Thread.currentThread().getName() + "线程,心跳结果=" + heartbeat);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
