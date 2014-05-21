package org.familysearch.joepdp.enums;

public class ZonedTime {
    private int hour;
    private int minute;
    private int second;
    private USTimeZones timeZone;
    private boolean inDst;

    public ZonedTime(int hour, int minute, int second, USTimeZones timeZone, boolean inDst) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.timeZone = timeZone;
        this.inDst = inDst;
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

    public boolean isInDst(){
        return inDst;
    }

    public ZonedTime convertTimeZone(USTimeZones newTimeZone, boolean inDst){
        return new ZonedTime(newTimeZone.getTZHour(timeZone.getUtcHour(getHour(), isInDst()), inDst), minute, second, newTimeZone, inDst);
    }

    public String toString(){
        return "" + getHour() + ":" + getMinute() + ":" + getSecond() + "(" + getTimeZone().getAbbreviation(isInDst()) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZonedTime zonedTime = (ZonedTime) o;

        return hour == zonedTime.hour && inDst == zonedTime.inDst && minute == zonedTime.minute && second == zonedTime.second && timeZone == zonedTime.timeZone;

    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + minute;
        result = 31 * result + second;
        result = 31 * result + timeZone.hashCode();
        result = 31 * result + (inDst ? 1 : 0);
        return result;
    }
}
