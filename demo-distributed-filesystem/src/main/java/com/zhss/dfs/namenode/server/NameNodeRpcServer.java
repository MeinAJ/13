package com.zhss.dfs.namenode.server;

/**
 * NameNode的rpc服务的接口
 *
 * @author zhonghuashishan
 */
public class NameNodeRpcServer {

    /**
     * 负责管理元数据的核心组件
     */
    private FSNamesystem namesystem;

    /**
     * 负责管理DataNode元数据核心组件
     */
    private DataNodeManager dataNodeManager;

    public NameNodeRpcServer() {
    }

    public NameNodeRpcServer(FSNamesystem namesystem, DataNodeManager dataNodeManager) {
        this.namesystem = namesystem;
        this.dataNodeManager = dataNodeManager;
    }

    /**
     * 创建目录
     *
     * @param path 目录路径
     * @return 是否创建成功
     * @throws Exception
     */
    public Boolean mkdir(String path) throws Exception {
        return this.namesystem.mkdir(path);
    }

    /**
     * 启动这个rpc server
     */
    public void start() {
        System.out.println("开始监听指定的rpc server的端口号，来接收请求");
    }

}
