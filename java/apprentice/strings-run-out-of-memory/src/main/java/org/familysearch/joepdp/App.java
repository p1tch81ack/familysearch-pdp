package org.familysearch.joepdp;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;



public class App {
    private static final int STRING_LENGTH = 100;
//                                            0000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999
//                                            0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
    private static final String PAD_STRING = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    private static String generateEntry(int index){
        StringBuilder out = new StringBuilder(STRING_LENGTH);
        out.append(index);
        out.insert(0, PAD_STRING,0,PAD_STRING.length()- out.length());
        return out.toString();
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
            listOfStrings = null; // allow the garbage collector to reclaim the space
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (Exception e) {
            listOfStrings = null; //  allow the garbage collector to reclaim the space
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        // Minimum String memory usage (bytes) = 8 * (int) ((((no chars) * 2) + 45) / 8)
        // from: http://www.javamex.com/tutorials/memory/string_memory_usage.shtml
        System.out.println("Ran out of memory after " + i + " strings were created.");
        int bytes = i * (8 * (((STRING_LENGTH * 2) + 45) / 8));
        System.out.println("That corresponds to " + NumberFormat.getNumberInstance(Locale.US).format(bytes) + " bytes of memory.");
    }
}
