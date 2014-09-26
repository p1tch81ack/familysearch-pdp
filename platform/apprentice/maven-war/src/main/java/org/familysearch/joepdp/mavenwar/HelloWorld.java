package org.familysearch.joepdp.mavenwar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HelloWorld {
    private static final Logger slf4jLogger = LoggerFactory.getLogger(HelloWorld.class);

    public static String bodyText(){
        slf4jLogger.info("timestamp=\"{}\" message=\"{}\"", new Date(), "bodyText called");
        return h1() + h2() + h3();
    }

    public static String h3() {
        return "<h3>Glad to be alive!<h3>";
    }

    public static String h2() {
        return "<h2>Hello World!</h2>";
    }

    public static String h1() {
        return "<h1>Tomcat:</h1>";
    }

    public static void main(String[]args){
        System.out.println(bodyText());
    }
}
