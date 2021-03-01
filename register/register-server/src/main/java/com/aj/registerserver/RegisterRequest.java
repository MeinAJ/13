package com.aj.registerserver;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {

    private String ip;

    private Integer port;

    private String serviceName;

    private String serviceInstanceId;

    private String hostname;

}
