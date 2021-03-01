package com.aj.registerserver;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HeartbeatResponse {

    private Integer code;

    private String message;

}
