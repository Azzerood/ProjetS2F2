import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String[] images={"img/sol.png","img/rocher.png","img/navire1.png","img/navire2.png","img/coffre.png","img/cl�.png"};
		JOptionPane Taille=new JOptionPane();
		boolean isnombre=true;
		String t;
		do{
		t= Taille.showInputDialog("D�finissez la taille de l'ile");
		for(int cpt=0;cpt<t.length() && isnombre;cpt++){
			if(t.charAt(cpt)<'0' && t.charAt(cpt)>'9')isnombre=false;
		}
		}while(!isnombre);
		int taille=Integer.parseInt(t);
		Ile i=new Ile(taille);
		SuperPlateau plateau=new SuperPlateau(images, taille);
		plateau.setIle(i);
		System.out.print(i.toString());
		i.placerLesNavires();
		System.out.println("***********Pla�ons les navires***********");
		System.out.print(i.toString());
		i.placerCoffre();
		i.placerCl�();
		System.out.println("*******Pla�ons le coffre et sa cl�*******");
		System.out.print(i.toString());
		i.placerRocher(0.1);
		System.out.println("***********Pla�ons les rochers***********");
		System.out.print(i.toString());
		plateau.setJeu();
		plateau.affichage();
	}

}
