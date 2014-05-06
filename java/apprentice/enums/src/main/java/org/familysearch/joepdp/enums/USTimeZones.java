package org.familysearch.joepdp.enums;

public enum USTimeZones {
    Eastern(-5, -4),
    Central(-6, -5),
    Mountain(-7, -6),
    Pacific(-8, -7),
    Alaska(-9, -8),
    Hawaii(-10, -9),
    Arizona(-8, -8);

    private int standardOffset;
    private int daylightOffset;

    USTimeZones(int standardOffset, int daylightOffset){
        this.standardOffset=standardOffset;
        this.daylightOffset=daylightOffset;
    }

    public int getStandardOffset() {
        return standardOffset;
    }

    public int getDaylightOffset() {
        return daylightOffset;
    }

    public int getOffset(boolean inDst){
        if(inDst){
            return getDaylightOffset();
        } else {
            return getStandardOffset();
        }
    }

    public int getUtcHour(int tzHour, boolean inDst){
        int offset = getOffset(inDst);
        int uTCHour = tzHour - offset;
        return uTCHour%24;
    }

    public int getTZHour(int utcHour, boolean inDst){
        int offset = getOffset(inDst);
        int tzHour = utcHour + offset;
        return tzHour%24;
    }
}
