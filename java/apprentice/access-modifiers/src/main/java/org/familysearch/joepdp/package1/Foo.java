package org.familysearch.joepdp.package1;

public class Foo {
    void defaultAccess(){
        System.out.println("defaultAccess method on an instance of Foo called!");
    }
    private void privateAccess(){
        System.out.println("privateAccess method on an instance of Foo called!");
    }
    protected void protecteedAccess(){
        System.out.println("protectedAccess method on an instance of Foo called!");
    }
    public void publicAccess(){
        System.out.println("publicAccess method on an instance of Foo called!");
    }
}
