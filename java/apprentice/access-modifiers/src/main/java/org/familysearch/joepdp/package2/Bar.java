package org.familysearch.joepdp.package2;

import org.familysearch.joepdp.package1.Foo;

public class Bar extends Foo {
//    This will not compile because Foo is in package1 so Bar in package2 can not overide methods with default access in a different package.
    /*
    @Override
    void defaultAccess() {
        System.out.println("defaultAccess method on an instance of Bar from package 2 called!");
    }
    */

    @Override
    protected void protecteedAccess() {
        System.out.println("protectedAccess method on an instance of Bar from package 2 called!");
    }
}
