package com.kv.j8.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestExceptionInInheritence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}

class Base{
	void display() throws IOException {
		throw new IOException();
	}
}

class Derived extends Base{
	void display() throws FileNotFoundException {
		throw new FileNotFoundException();
	}
}
