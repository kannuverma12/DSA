package com.kv.j8;

public class NameCol {
    String name;
    char   ch;
    int    count = 0;

    public NameCol(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        //here i try to send the String file
        NameCol n = new NameCol("karan");
        //here i try to call the method
        n.get();
    }


    public void get() {
        for (char i = 'a'; i < 'z'; i++) {
            count = 0;
            for (int j = 0; j < this.name.length(); j++) {
                ch = this.name.toLowerCase().charAt(j);
                if (ch == i)
                    count++;
            }
            if (count != 0) {
                System.out.println(i + "\t\t" + count);
            }

        }
    }
}
