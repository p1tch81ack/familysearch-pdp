package org.familysearch.javaapprenticepdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 */
public class App {
    private static final Logger slf4jLogger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            MDC.put("hostname", InetAddress.getLocalHost().getHostName());
        } catch(UnknownHostException e){
            slf4jLogger.warn("message=\"{}\"", "Unknown Host");
        }
//        System.out.println("Hello World!");
        slf4jLogger.debug("Message=\"{} {}\"", "Hello", "World!");
        slf4jLogger.info("Message=\"{} {}\"", "Hello", "Info!");
        slf4jLogger.warn("Message=\"{} {}\"", "Hello", "Warn!");
        try {
            throw new Exception("Hello Error!");
        } catch (Exception e){
            slf4jLogger.error("Message=\"{}\"", e.getMessage(), e);
        }
    }
}
