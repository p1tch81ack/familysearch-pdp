package org.familysearch.joepdp;

public class App {
    public static void main( String[] args ) {
        doSomethingWithThisObject(42);
        doSomethingWithThisObject("Hello World");
        doSomethingWithThisObject(Math.PI);
    }

    public static void doSomethingWithThisObject(Object o){
        if(o instanceof Integer){
            Integer integer = (Integer) o;
            System.out.println("You sent me an Integer whose value is: [" + integer + "]");
        } else if (o instanceof String){
            String string = (String) o;
            System.out.println("You sent me a String which contains: \"" + string + "\"");
        } else {
            System.out.println("You sent me a " + o.getClass().getSimpleName() + " whose string representation is {" + o.toString() + "}");
        }
    }
}
