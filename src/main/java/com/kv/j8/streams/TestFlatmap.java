package com.kv.j8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestFlatmap {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        ClassB firstClass = new ClassB("A");
        ClassB secondClass = new ClassB("B");
        ClassB thirdClass = new ClassB("C");
        ClassB fourthClass = new ClassB("D");
        ArrayList<ClassB> firstClassList = new ArrayList<>();
        ArrayList<ClassB> secondClassList = new ArrayList<>();

        firstClassList.add(firstClass);
        firstClassList.add(secondClass);
        secondClassList.add(thirdClass);
        secondClassList.add(fourthClass);

        ArrayList<ClassA> classes = new ArrayList<>();
        classes.add(new ClassA(firstClassList));
        classes.add(new ClassA(secondClassList));

        List<List<String>> collect = classes.stream().map(c -> c.getListOfClassB().stream().map(ClassB::getItem).collect(Collectors.toList())).collect(Collectors.toList());
    
        System.out.println("collect : "+collect);

        //List<String> collect2 = (List<String>) classes.stream().flatMap(aclass -> aclass.getListOfClassB().stream());
        List<String> collect2 = classes.stream().flatMap(aclass -> aclass.getListOfClassB().stream())
        .map(b -> b.getItem())
        .collect(Collectors.toList());
        //.forEach(b -> System.out.println("Class B Item Name : "+b.getItem()));
        
        System.out.println("collect2 : "+collect2);
    }

}

class ClassA {
    private List<ClassB> listOfClassB;

    public ClassA(List<ClassB> listOfClassB) {
        this.listOfClassB = listOfClassB;
    }

    public List<ClassB> getListOfClassB() {
        return this.listOfClassB;
    }
}

 class ClassB {
    private String item;

    public ClassB(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
