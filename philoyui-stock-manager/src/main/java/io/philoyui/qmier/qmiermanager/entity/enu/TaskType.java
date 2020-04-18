package io.philoyui.qmier.qmiermanager.entity.enu;

public enum TaskType {

    Min_5("5分钟K线数据抓取（From 新浪）",5),
    Min_15("15分钟K线数据抓取（From 新浪）",15),
    Min_30("半个小时K线数据抓取（From 新浪）",30),
    Hour("小时K线数据抓取（From 新浪）",60),
    Day("每天K线数据抓取（From 新浪）",240),
    Week("每周K线数据抓取（From 新浪）",1200),
    Month("每月K线数据抓取（From 新浪）",7200),
    Month_STRATEGY("月策略",7200),
    Day_STRATEGY("日策略",7200),
    Week_STRATEGY("周策略",7200),

    ;


    private String taskName;

    private int minute;

    TaskType(String taskName,int minute) {
        this.taskName = taskName;
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
