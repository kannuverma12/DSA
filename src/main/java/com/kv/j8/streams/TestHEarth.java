package com.kv.j8.streams;

public class TestHEarth {

    public  static void main(String... ar){
        B b  = new B();
        b.i = 1;
        b.j = 2;
        b.b();
    }
}

class A{

    public int i;
    protected int j;


}
class B extends A{
    int j;
    void b(){
        super.i = 3;
        super.j = 10;
        System.out.println(i +" "+j);
    }
}
