package main;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Hallo");
		
		Hibernate hibernate = new Hibernate();
		
		hibernate.setup();
		hibernate.read();
		hibernate.exit();
	}
}