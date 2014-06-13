package org.familysearch.joepdp;

public class Foo {
    private Boolean a;
    private Integer b;
    private Double c;
    private String d;

    public Foo(Boolean a, Integer b, Double c, String d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }

    public Foo(Boolean a){
        this(a, null, null, null);
    }

    public Foo(Integer b){
        this(null, b, null, null);
    }

    public Foo(Double c){
        this(null, null, c, null);
    }

    public Foo(String d){
        this(null, null, null, d);
    }

    public Boolean getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "a=" + getA() +
                ", b=" + getB() +
                ", c=" + getC() +
                ", d='" + getD() + '\'' +
                '}';
    }
}
