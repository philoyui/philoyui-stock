package io.philoyui.qmier.qmiermanager.entity.enu;

public enum DataType {

    Min_5(5),Min_15(15),Min_30(15),Hour(60),Day(240),Week(1200),Month(7200);

    private int minute;

    DataType(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}
