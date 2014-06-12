package org.familysearch.joepdp;

public class App {
    public static void main( String[] args ) {
        System.out.println("Going to throw a checked exception...");
        try{
            throw(new Exception("This is a checked exception"));
        } catch (Exception e){
            System.out.println("Caught " + e.getClass() + " with message: \"" + e.getMessage() + "\"");
        }

        System.out.println("Going to throw an unchecked exception...");
        try{
            throw(new RuntimeException("This is an unchecked exception"));
        } catch (Exception e){
            System.out.println("Caught " + e.getClass() + " with message: \"" + e.getMessage() + "\"");
        }

        System.out.println("Going to throw an error exception...");
        try{
            throw(new Error("This is an error exception"));
        } catch (Throwable t){
            System.out.println("Caught " + t.getClass() + " with message: \"" + t.getMessage() + "\"");
        }
    }
}
