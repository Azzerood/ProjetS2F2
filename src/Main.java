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
		int[][] x=plateau.i.getPlateau();
		for(int l=0;l<plateau.i.plateau.length;l++){
			for(int c=0;c<plateau.i.plateau[0].length;c++){
				System.out.print(x[l][c]);
			}
			System.out.println();
		}
		plateau.setJeu();
		plateau.affichage();
	}

}
