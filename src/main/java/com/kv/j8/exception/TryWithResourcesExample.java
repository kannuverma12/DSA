package com.kv.j8.exception;

public class TryWithResourcesExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Lion lion = new Lion(); Tiger tiger = new Tiger()) {

			lion.hunt();
			tiger.hunt();

		} catch (Exception e) {
			System.out.println("Got Simple Exception = "+e);
			for(Throwable t: e.getSuppressed())
	        {
				System.out.println("Got Suppressed Exception = "+t);
	            t.printStackTrace();
	        }
		} finally {
			System.out.println("Finally.");
		}
	}

}

class Tiger implements AutoCloseable {
	public Tiger() {
		System.out.println("TIGER is OPEN in the wild.");
	};

	public void hunt() throws Exception {
		//throw new Exception("DeerNotFound says Tiger!");
	}

	public void close() throws Exception {
		System.out.println("Tiger is CLOSED in the cage.");
		
	}
}

class Lion implements AutoCloseable {
	public Lion() {
		System.out.println("LION is OPEN in the wild.");
	}

	public void hunt() throws Exception {
		//throw new Exception("DeerNotFound says Lion!");
	}

	public void close() throws Exception {
		System.out.println("LION is CLOSED in the cage.");
		throw new Exception("Lion Unable to close the cage!");
	}
}
