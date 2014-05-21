package org.familysearch.joepdp.enums;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ZonedTimeTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ZonedTimeTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ZonedTimeTest.class );
    }

    public void testPacificToEastern() {
        ZonedTime pacificEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Pacific, false);
        ZonedTime easternThree42 = pacificEleven42.convertTimeZone(USTimeZones.Eastern, true);
        assertEquals(new ZonedTime(3, 42, 0, USTimeZones.Eastern, true), easternThree42);
    }

    public void testCentralToMountain() {
        ZonedTime centralEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Central, true);
        ZonedTime mountainTen42 = centralEleven42.convertTimeZone(USTimeZones.Mountain, true);
        assertEquals(new ZonedTime(22, 42, 0, USTimeZones.Mountain, true), mountainTen42);
    }

    public void testHawaiiToAlaskan() {
        ZonedTime hawaiiEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Hawaii, false);
        ZonedTime alaskanTwelve42 = hawaiiEleven42.convertTimeZone(USTimeZones.Alaska, false);
        assertEquals(new ZonedTime(0, 42, 0, USTimeZones.Alaska, false), alaskanTwelve42);
    }

    public void testHashCodeEquals() {
        ZonedTime hawaiiEleven42A = new ZonedTime(23, 42, 0, USTimeZones.Hawaii, false);
        ZonedTime hawaiiEleven42B = new ZonedTime(23, 42, 0, USTimeZones.Hawaii, false);
        assertTrue(hawaiiEleven42A.hashCode() == hawaiiEleven42B.hashCode());
    }

    public void testHashCodeNotEquals() {
        ZonedTime hawaiiEleven42A = new ZonedTime(23, 42, 0, USTimeZones.Hawaii, false);
        ZonedTime hawaiiEleven42B = new ZonedTime(22, 42, 0, USTimeZones.Hawaii, false);
        assertFalse(hawaiiEleven42A.hashCode() == hawaiiEleven42B.hashCode());
    }

    public void testStandardToString() {
        ZonedTime hawaiiEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Hawaii, false);
        assertEquals("23:42:0(HAST)", hawaiiEleven42.toString());
    }

    public void testDaylightToString() {
        ZonedTime alaskaEleven42 = new ZonedTime(23, 42, 0, USTimeZones.Alaska, true);
        assertEquals("23:42:0(AKDT)", alaskaEleven42.toString());
    }
}
