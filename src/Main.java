
public class Main {

	public static void main(String[] args) {
		
		Ile i=new Ile();
		System.out.print(i.toString());
		i.placerLesNavires();
		System.out.println("***********Plaçons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerClé();
		System.out.println("*******Plaçons le coffre et sa clé*******");
		System.out.print(i.toString());
		i.placerRocher();
		System.out.println("***********Plaçons les rochers***********");
		System.out.print(i.toString());
	}

}
