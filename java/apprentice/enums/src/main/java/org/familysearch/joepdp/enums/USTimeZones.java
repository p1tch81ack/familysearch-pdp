package org.familysearch.joepdp.enums;

public enum USTimeZones {
    Eastern("E", -5, -4),
    Central("C", -6, -5),
    Mountain("M", -7, -6),
    Pacific("P", -8, -7),
    Alaska("AK", -9, -8),
    Hawaii("HA", -10, -9);

    private String abbreviation;
    private int standardOffset;
    private int daylightOffset;

    USTimeZones(String abbreviation, int standardOffset, int daylightOffset){
        this.abbreviation=abbreviation;
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

    public String getAbbreviation(boolean inDST){
        if(inDST){
            return abbreviation + "DT";
        } else {
            return abbreviation + "ST";
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
        while(tzHour<0){
            tzHour=tzHour+24;
        }
        return tzHour%24;
    }
}
