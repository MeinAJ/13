package com.zhss.dfs.namenode.server;

public class DataNodeInfo {
    private String ip;
    private String port;
    private long latestHeartbeatTimStamp;

    public DataNodeInfo() {
    }

    public DataNodeInfo(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public long getLatestHeartbeatTimStamp() {
        return latestHeartbeatTimStamp;
    }

    public void setLatestHeartbeatTimStamp(long latestHeartbeatTimStamp) {
        this.latestHeartbeatTimStamp = latestHeartbeatTimStamp;
    }
}
