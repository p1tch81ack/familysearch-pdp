package org.familysearch.joepdp;

class Foo {
    public void doSomething(){
        System.out.println("This is Foo doing something");
    }
}

class Bar extends Foo {
    @Override
    public void doSomething() {
        System.out.println("This is Bar doing something");
    }
}

public class App {
    public static void main( String[] args ) {
        Foo foo = new Foo();
        foo.doSomething();
        Foo bar = new Bar();
        bar.doSomething();
    }
}
