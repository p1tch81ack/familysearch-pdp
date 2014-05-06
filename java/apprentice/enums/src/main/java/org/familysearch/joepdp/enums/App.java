package org.familysearch.joepdp.enums;

/**
 * Hello world!
 *
 */
public class App  {
    public static void main(String[] args){
        ZonedTime pacificEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Pacific);
        ZonedTime easternTwo42 = pacificEleven42.convertTimeZone(USTimeZones.Eastern, false);
        System.out.println("When it is " + pacificEleven42 + " in the pacific time zone, it is " + easternTwo42 + " in the eastern time zone.");
    }
}
