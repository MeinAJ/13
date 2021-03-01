/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.registerserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry
 *
 * @author An Jun
 * @date 2021-03-01
 */
public class Registry {

    private static Registry instance = new Registry();

    private Registry() {

    }

    private final Map<String, Map<String, ServiceInstance>> registry =
            new HashMap<String, Map<String, ServiceInstance>>();

    public static Registry getInstance() {
        return instance;
    }

    public ServiceInstance getServiceInstance(String serviceName, String serviceInstanceId) {
        return registry.get(serviceName).get(serviceInstanceId);
    }

    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    public void remove(String serviceName, String serviceInstanceId) {
        System.out.println("服务实例【" + serviceInstanceId + "】，从注册表中进行摘除");
        Map<String, ServiceInstance> stringServiceInstanceMap = registry.get(serviceName);
        stringServiceInstanceMap.remove(serviceInstanceId);
    }

    public void register(ServiceInstance serviceInstance) {
        Map<String, ServiceInstance> stringServiceInstanceMap = registry.computeIfAbsent(serviceInstance.getServiceName(), k -> new HashMap<String, ServiceInstance>());
        stringServiceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);
        System.out.println("服务实例【" + serviceInstance + "】，完成注册......");
        System.out.println("注册表：" + registry);
    }


}
