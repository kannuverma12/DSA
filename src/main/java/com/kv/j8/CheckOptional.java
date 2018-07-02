package com.kv.j8;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

public class CheckOptional {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Outer outer = new Outer();
		
		Nested nested = new Nested();
		outer.nested = nested;
		
		Inner inner = new Inner();
		nested.inner = inner;
		inner.foo = "Karan";
		
		if(outer != null
				&& outer.nested != null
				&& outer.nested.inner != null){
			System.out.println("Found all Objects");
		}
		
		Optional.of(outer)
				.map(Outer::getNested)
				.map(Nested::getInner)
				.map(Inner::getFoo)
				.ifPresent(System.out::println);
		
		
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
        
        Optional.of(new Outer())
        .map(Outer::getNested)
        .map(Nested::getInner)
        .map(Inner::getFoo)
        .ifPresent(System.out::println);
        
        
        
        Optional.ofNullable(outer)
        .map(Outer::getNested)
        //.map(Nested::getInner)
        .map((outerr) -> outerr.getInner() == null ? null : outerr)
        
        .isPresent();
		
		
		
		
		Outer obj = new Outer();
		resolve(() -> outer.getNested().getInner().getFoo())
		    .ifPresent(System.out::println);
		
		
	}
	
	public static <T> Optional<T> resolve(Supplier<T> resolver) {
	    try {
	        T result = resolver.get();
	        return Optional.ofNullable(result);
	    }
	    catch (NullPointerException e) {
	        return Optional.empty();
	    }
	}

}

class Outer {
    Nested nested;
    Nested getNested() {
        return nested;
    }
}
class Nested {
    Inner inner;
    AnotherInner anotherInner;
    Inner getInner() {
        return inner;
    }
    
    AnotherInner getAnotherInner(){
    	return anotherInner;
    }
}
class Inner {
    String foo;
    String getFoo() {
        return foo;
    }
}

class AnotherInner {
    String foo;
    String getFoo() {
        return foo;
    }
}
