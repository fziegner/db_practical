package main;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Hallo");
		
		HibernateUtil hibernate = new HibernateUtil();
		
		hibernate.setup();
		hibernate.read();
		hibernate.exit();
	}
}