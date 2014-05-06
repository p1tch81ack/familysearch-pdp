package org.familysearch.joepdp.enums;

public class ZonedTime {
    private int hour;
    private int minute;
    private int second;
    private USTimeZones timeZone;

    public ZonedTime(int hour, int minute, int second, USTimeZones timeZone) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.timeZone = timeZone;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public USTimeZones getTimeZone() {
        return timeZone;
    }

    public ZonedTime convertTimeZone(USTimeZones newTimeZone, boolean inDst){
        return new ZonedTime(newTimeZone.getTZHour(timeZone.getUtcHour(getHour(), inDst), inDst), minute, second, newTimeZone);
    }

    public String toString(){
        return "" + getHour() + ":" + getMinute() + ":" + getSecond();
    }

}
