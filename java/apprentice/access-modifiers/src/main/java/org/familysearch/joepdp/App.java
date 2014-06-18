package org.familysearch.joepdp;

import org.familysearch.joepdp.package1.Foo;

public class App {
    public static void main( String[] args ) {
        Foo foo = new Foo();

//        This will fail because foo is  in a different package so methods with default access are not accessable here
//        foo.defaultAccess();

//        This will fail because methods with private access in a different class are not accessable here
//        foo.privateAccess();

//        This will fail because methods with protected access are not accessable unless called from an inheriting class
//        foo.defaultAccess();

        foo.publicAccess();
    }
}
