import javax.swing.JOptionPane;




public class Main {
	private int d�finirTailleIle(){
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
		t= Taille.showInputDialog("D�finissez la taille de l'ile");
		if(t.matches("[0-9]+")){
			taille=Integer.parseInt(t);
			if(taille>6)
			isnombre=true;
		}
		}while(!isnombre);
		return taille;
	}
	private int definirProportionRocher(){
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=false;
		String t;
		int pourcentage=10;
		do{
			int max=30;
		t= Taille.showInputDialog("D�finissez le pourcentage de rochers [0-"+max+"]");
		if(t.matches("[0-9]+")){
			pourcentage=Integer.parseInt(t);
			if(pourcentage<=max)isnombre=true;
			
		}
		}while(!isnombre);
		return pourcentage ;
	}
	private void erreurRocher(){
		JOptionPane Taille=new JOptionPane();
		Taille.showMessageDialog(Taille, "Nous n'avons pas pu configurer l'ile. Recommencez", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		Main main=new Main();
		boolean Rochers=false;
		Ile i;
		SuperPlateau plateau;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pcl�.png","img/peau.png"};
		int taille=main.d�finirTailleIle();
		int pourcentage=main.definirProportionRocher();
		i=new Ile(taille);
		plateau=new SuperPlateau(images, taille);
		plateau.setIle(i);
		System.out.print(i.toString());
		i.placerLesNavires();
		i.placerEau();
		System.out.println("***********Pla�ons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerCl�();
		System.out.println("*******Pla�ons le coffre et sa cl�*******");
		System.out.print(i.toString());
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			main.erreurRocher();
		}
		}while(!Rochers);
		System.out.println("***********Pla�ons les rochers***********");
		System.out.print(i.toString());
		plateau.setJeu();
		plateau.affichage();
	}

}
