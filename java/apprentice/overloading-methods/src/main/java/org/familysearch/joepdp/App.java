package org.familysearch.joepdp;

class Foo{
    public void doSomething(int i){
        System.out.println("You sent me an Integer whose value is: [" + i + "]");
    }
    public void doSomething(String s){
        System.out.println("You sent me a String which contains: \"" + s + "\"");
    }
    public void doSomething(Object o){
        System.out.println("You sent me a " + o.getClass().getSimpleName() + " whose string representation is {" + o.toString() + "}");
    }
}

public class App {
    public static void main( String[] args ) {
        Foo foo = new Foo();
        foo.doSomething(42);
        foo.doSomething("Hello World");
        foo.doSomething(Math.PI);
    }
}
