package com.zhss.seckill.operation.domain;

/**
 * 秒杀场次
 */
public class SeckillSession {

    /**
     * 秒杀场次id
     */
    private Long id;
    /**
     * 秒杀场次日期
     */
    private String sessionDate;
    /**
     * 秒杀场次时间
     */
    private String sessionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

}
