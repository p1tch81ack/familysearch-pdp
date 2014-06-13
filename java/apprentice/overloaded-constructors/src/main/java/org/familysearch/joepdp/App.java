package org.familysearch.joepdp;

public class App {
    public static void main( String[] args ) {
        Foo foo = new Foo(false, 0, 0.0, "zero");
        System.out.println(foo);
        Foo fooA = new Foo(true);
        System.out.println(fooA);
        Foo fooB = new Foo(1);
        System.out.println(fooB);
        Foo fooC = new Foo(1.0);
        System.out.println(fooC);
        Foo fooD = new Foo("One");
        System.out.println(fooD);
    }
}
