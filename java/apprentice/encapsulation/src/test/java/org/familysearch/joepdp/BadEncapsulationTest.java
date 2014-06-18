package org.familysearch.joepdp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class BadEncapsulationTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BadEncapsulationTest(String testName) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( BadEncapsulationTest.class );
    }

    public void testConstructorAndGetter(){
        Date testDate = new Date(0);
        BadEncapsulation encapsulation = new BadEncapsulation(testDate);
        assertEquals(testDate, encapsulation.getDate());
    }

    public void testImmutableBasedOnConstructorParameter(){
        Date constructorDate = new Date(0);
        BadEncapsulation encapsulation = new BadEncapsulation(constructorDate);
        constructorDate.setTime(1);
        assertNotSame(constructorDate, encapsulation.getDate());
    }

    public void testImmutableBasedOnGetterReturn(){
        Date testDate = new Date(0);
        BadEncapsulation encapsulation = new BadEncapsulation(testDate);
        assertEquals(testDate, encapsulation.getDate());
        Date getterDate = encapsulation.getDate();
        getterDate.setTime(1);
        assertNotSame(getterDate, encapsulation.getDate());
    }

}
