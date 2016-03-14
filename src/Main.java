public class Main {

	public static void main(String[] args) {
		String[] images={"img/sol.png","img/rocher.png","img/navire1.png","img/navire2.png","img/coffre.png","img/clé.png"};
		SuperPlateau plateau=new SuperPlateau(images, 10);
		Ile i=new Ile();
		plateau.setIle(i);
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
		plateau.setJeu();
		plateau.affichage();
	}

}
