package ru.oksana.l41;

import com.sun.management.GcInfo;

public class GCInfoAggregator {

    private String gcName;
    private long runCount;
    private long workTime;

    private long startTime;

    public GCInfoAggregator(String gcName) {
        this.gcName = gcName;
        startTime = System.currentTimeMillis();
    }

    public GCInfoAggregator(String gcName, long startTime) {
        this(gcName);
        this.startTime = startTime;
    }

    synchronized public void collect(GcInfo gcInfo) {
        runCount = gcInfo.getId();
        workTime += gcInfo.getDuration();
    }

    @Override
    public String toString() {
        return "gcName = " + gcName +
                ", runCount = " + runCount +
                ", workTime = " + workTime +
                ", waitTime = " + (System.currentTimeMillis() - startTime - workTime);
    }

}