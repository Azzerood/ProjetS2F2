
public class Main {

	public static void main(String[] args) {
		
		Ile i=new Ile();
		System.out.print(i.toString());
		i.placerLesNavires();
		System.out.println("***********Pla�ons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerCl�();
		System.out.println("*******Pla�ons le coffre et sa cl�*******");
		System.out.print(i.toString());
		i.placerRocher();
		System.out.println("***********Pla�ons les rochers***********");
		System.out.print(i.toString());
	}

}
