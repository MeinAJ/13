/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.registerserver;

import lombok.Data;

/**
 * ServiceInstance
 *
 * @author An Jun
 * @date 2021-03-01
 */
@Data
public class ServiceInstance {

    private final long ALICE_TIME_PERIOD = 90 * 1000L;

    private String serviceInstanceId;

    private String ip;

    private String hostname;

    private Integer port;

    private String serviceName;

    private Lease lease;

    public ServiceInstance() {
        this.lease = new Lease();
    }

    class Lease {

        private long lastRenewTime = System.currentTimeMillis();

        public Boolean isAlive() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRenewTime > ALICE_TIME_PERIOD) {
                System.out.println("服务实例【" + serviceInstanceId + "】，不再存活");
                return false;
            }
            System.out.println("服务实例【" + serviceInstanceId + "】，保持存活");
            return true;
        }

        public void renew() {
            this.lastRenewTime = System.currentTimeMillis();
            System.out.println("服务实例【" + serviceInstanceId + "】，进行续约：" + lastRenewTime);
        }

    }

    public void renew() {
        this.lease.renew();
    }

    public boolean isAlive() {
        return this.lease.isAlive();
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "ALICE_TIME_PERIOD=" + ALICE_TIME_PERIOD +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                ", ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", serviceName='" + serviceName + '\'' +
                ", lease=" + lease +
                '}';
    }

}
