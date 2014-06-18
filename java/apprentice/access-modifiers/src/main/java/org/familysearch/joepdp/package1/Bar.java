package org.familysearch.joepdp.package1;

public class Bar extends Foo {
    // this works because this Bar is in the same package as Foo
    @Override
    void defaultAccess() {
        System.out.println("defaultAccess method on an instance of Bar from package 1 called!");
    }
}
