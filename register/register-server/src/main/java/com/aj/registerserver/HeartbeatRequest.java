package com.aj.registerserver;

import lombok.Data;

@Data
public class HeartbeatRequest {

    private String serviceInstanceId;

    private String serviceName;

}
