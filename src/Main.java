import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png"};
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		do{
		t= Taille.showInputDialog("Définissez la taille de l'ile");
		if(t.matches("[0-9]+")){
			isnombre=true;
		}
		}while(!isnombre);
		
		int taille=Integer.parseInt(t);
		isnombre=false;
		double pourcentage=10;
		do{
		t= Taille.showInputDialog("Définissez le pourcentage de rochers");
		if(t.matches("[0-9]+")){
			pourcentage=Double.parseDouble(t);
			if(pourcentage<=25)isnombre=true;
			
		}
		}while(!isnombre);
		Ile i=new Ile(taille);
		SuperPlateau plateau=new SuperPlateau(images, taille);
		plateau.setIle(i);
		System.out.print(i.toString());
		i.placerLesNavires();
		System.out.println("***********Plaçons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerClé();
		System.out.println("*******Plaçons le coffre et sa clé*******");
		System.out.print(i.toString());
		i.placerRocher(pourcentage);
		System.out.println("***********Plaçons les rochers***********");
		System.out.print(i.toString());
		plateau.setJeu();
		plateau.affichage();
	}

}
