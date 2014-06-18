package org.familysearch.joepdp;

interface StuffDoer{
    void doStuff();
}

class Foo implements StuffDoer {
    @Override
    public void doStuff() {
        System.out.println("Doing Foo Stuff!");
    }
}

class Bar implements StuffDoer {
    @Override
    public void doStuff() {
        System.out.println("Doing Bar Stuff!");
    }
}

public class App {
    public static void main( String[] args ) {
        StuffDoer foo = new Foo();
        StuffDoer bar = new Bar();
        foo.doStuff();
        bar.doStuff();
    }
}
