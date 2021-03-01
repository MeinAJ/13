/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.registerserver;

import java.util.Map;

/**
 * ServeAliveMonitor
 *
 * @author An Jun
 * @date 2021-03-01
 */
public class ServiceAliveMonitor {


    /**
     * 检查服务实例是否存活的间隔
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    private final Daemon daemon = new Daemon();

    static class Daemon extends Thread {

        private Registry instance = Registry.getInstance();

        @Override
        public void run() {

            Map<String, Map<String, ServiceInstance>> registry;

            while (true) {
                registry = this.instance.getRegistry();
                for (String serviceName : registry.keySet()) {
                    Map<String, ServiceInstance> stringServiceInstanceMap = registry.get(serviceName);
                    for (ServiceInstance serviceInstance : stringServiceInstanceMap.values()) {
                        if (!serviceInstance.isAlive()) {
                            instance.remove(serviceName, serviceInstance.getServiceInstanceId());
                        }
                    }
                }
                try {
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void start() {
        daemon.start();
    }

}
