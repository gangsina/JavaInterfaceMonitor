package com.bentengwu.entity;

import java.util.Date;

/**
 * @Author <a href="bentengwu@163.com">thender.xu</a>
 * @Date 2017/11/23 16:20.
 */
public class MonitorEntity extends BaseEntity {
    /**
     * The name of Thread
     */
    protected String threadName;
    /**
     *The millisecond when invoke the method pay
     */
    protected long intervalMillisecond;

    /**
     * Record the invoke datetime
     */
    protected Date invokeStartTime;

    /**
     * Record the datatime when invoke finished
     */
    protected Date invokeEndTime;



    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getIntervalMillisecond() {
        return intervalMillisecond;
    }

    public void setIntervalMillisecond(long intervalMillisecond) {
        this.intervalMillisecond = intervalMillisecond;
    }

    public Date getInvokeStartTime() {
        return invokeStartTime;
    }

    public void setInvokeStartTime(Date invokeStartTime) {
        this.invokeStartTime = invokeStartTime;
    }

    public Date getInvokeEndTime() {
        return invokeEndTime;
    }

    public void setInvokeEndTime(Date invokeEndTime) {
        this.invokeEndTime = invokeEndTime;
    }
}
