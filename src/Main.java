import javax.swing.JOptionPane;

public class Main {
	/**
	 * Retourne le nombre saisie par l'utilisateur pour définir la taille de l'ile
	 * @param min
	 * @return
	 */
	private int définirTailleIle(int min){
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
		t= Taille.showInputDialog("Définissez la taille de l'ile");
		if(t.matches("[0-9]+")){
			taille=Integer.parseInt(t);
			if(taille>min)
			isnombre=true;
		}
		}while(!isnombre);
		return taille;
	}
	/**
	 * Retourne le nombre saisie par l'utilisateur pour définir le pourcentage de rocher sur l'ile
	 * @param max
	 * @return
	 */
	private int definirProportionRocher(int max){
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		int pourcentage=10;
		do{
		t= Taille.showInputDialog("Définissez le pourcentage de rochers [0-"+max+"]");
		if(t.matches("[0-9]+")){
			pourcentage=Integer.parseInt(t);
			if(pourcentage<=max)isnombre=true;
			
		}
		}while(!isnombre);
		return pourcentage ;
	}
	private void erreurRocher(){
		JOptionPane Taille=new JOptionPane();
		Taille.showMessageDialog(Taille, "Désolé capitaine, nous n'avons pas trouvé une telle île dans les environs.", "Oops...", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		Main main=new Main();
		boolean Rochers=false;
		Ile i;
		SuperPlateau plateau;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png"};
		int taille=main.définirTailleIle(6);
		int pourcentage=main.definirProportionRocher(30);
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
			main.erreurRocher();
		}
		}while(!Rochers);
		System.out.println("***********Plaçons les rochers***********");
		System.out.print(i.toString());
		plateau.setJeu();
		plateau.affichage();
	}

}
