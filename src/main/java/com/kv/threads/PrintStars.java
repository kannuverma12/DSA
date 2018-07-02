package com.kv.threads;

public class PrintStars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printTriangleStars();
	}
	
	public static void printTriangleStars(){
		for(int i=0;i<5;i++){
			for(int j=0;j<3;j++){
				if(i==j)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println("");
		}
	}

}
