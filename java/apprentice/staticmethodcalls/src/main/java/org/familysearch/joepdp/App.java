package org.familysearch.joepdp;

import java.io.IOException;

public class App {
    public static void main( String[] args ) {
        try{
            XMLFile xmlFile = new XMLFile("xmlfile.xml");
            xmlFile.appendComment("This is a comment");
            xmlFile.close();

            ConfigFile configFile = new ConfigFile("config.cfg");
            configFile.appendComment("This is a comment");
            configFile.close();
        } catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
