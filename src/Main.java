import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		boolean Rochers=false;
		Ile i;
		SuperPlateau plateau;
		do{
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png"};
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
		t= Taille.showInputDialog("Définissez la taille de l'ile");
		if(t.matches("[0-9]+")){
			taille=Integer.parseInt(t);
			if(taille>6)
			isnombre=true;
		}
		}while(!isnombre);
		isnombre=false;
		double pourcentage=10;
		do{
		t= Taille.showInputDialog("Définissez le pourcentage de rochers");
		if(t.matches("[0-9]+")){
			pourcentage=Double.parseDouble(t);
			if(pourcentage<=25)isnombre=true;
			
		}
		}while(!isnombre);
		 i=new Ile(taille);
		plateau=new SuperPlateau(images, taille);
		plateau.setIle(i);
		System.out.print(i.toString());
		i.placerLesNavires();
		i.placerEau();
		System.out.println("***********Plaçons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerClé();
		System.out.println("*******Plaçons le coffre et sa clé*******");
		System.out.print(i.toString());
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			Taille.showMessageDialog(Taille, "Nous n'avons pas pu configurer l'ile. Recommencez", "Error", JOptionPane.ERROR_MESSAGE);
		}
		}while(!Rochers);
		System.out.println("***********Plaçons les rochers***********");
		System.out.print(i.toString());
		plateau.setJeu();
		plateau.affichage();
	}

}
