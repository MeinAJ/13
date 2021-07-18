package com.zhss.dfs.namenode.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataNodeManager {

    private final ConcurrentHashMap<String, DataNodeInfo> dataNodeInfoList = new ConcurrentHashMap<>();

    public void register(DataNodeInfo dataNodeInfo) {
        String key = dataNodeInfo.getIp() + ":" + dataNodeInfo.getPort();
        dataNodeInfoList.put(key, dataNodeInfo);
    }

    public void heartBeat(String ip, String port) {
        DataNodeInfo dataNodeInfo = dataNodeInfoList.get(ip + ":" + port);
        if (dataNodeInfo != null) {
            dataNodeInfo.setLatestHeartbeatTimStamp(System.currentTimeMillis());
        }
    }

    public void removePossibleDataNode() {
        while (true) {
            try {
                Iterator<DataNodeInfo> iterator = dataNodeInfoList.values().iterator();
                DataNodeInfo next;
                List<DataNodeInfo> needRemovedDataNode = new ArrayList<>();
                while (iterator.hasNext()) {
                    next = iterator.next();
                    if (System.currentTimeMillis() - next.getLatestHeartbeatTimStamp() >= 60 * 1000) {
                        needRemovedDataNode.add(next);
                    }
                }
                if (!needRemovedDataNode.isEmpty()) {
                    for (DataNodeInfo dataNodeInfo : needRemovedDataNode) {
                        dataNodeInfoList.remove(dataNodeInfo.getIp() + ":" + dataNodeInfo.getPort());
                    }
                }

                Thread.sleep(30 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
