package org.familysearch.joepdp;

import java.util.LinkedList;
import java.util.List;


public class App {
    private static String padTo(String original, int length, char padCharacter){
        String out = original;
        for(int i=0; i<length-original.length(); i++) {
            out = "" + padCharacter + out;
        }
        return out;
    }
    private static String generateEntry(int index){
        String numString = "" + index;
        return padTo(numString, 100, '0');
    }

    public static void main(String[] args){
        List<String> listOfStrings = new LinkedList<String>();
        int i=0;
        try {
            while(true){
                listOfStrings.add(generateEntry(i));
                i++;
            }
        } catch (OutOfMemoryError e) {
            listOfStrings = null; // give the garbage collector a chance to run if necessary
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (Exception e) {
            listOfStrings = null; // give the garbage collector a chance to run if necessary
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        if(listOfStrings!=null){
            System.out.println("Quitting in a completely unanticiped way...");
        } else {
            System.out.println("Ran out of memory after " + i + " strings were created.");
        }
    }
}
