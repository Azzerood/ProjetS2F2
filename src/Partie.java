import javax.swing.JOptionPane;

public class Partie {
	SuperPlateau s;
	
	private int définirTailleIle(){
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
		t= JOptionPane.showInputDialog("Définissez la taille de l'ile [min:10]");
		if(t.matches("[0-9]+")){
			taille=Integer.parseInt(t);
			if(taille>=10)
			isnombre=true;
		}
		}while(!isnombre);
		return taille;
	}
	private int definirProportionRocher(){
		boolean isnombre=false;
		String t;
		int pourcentage=10;
		do{
			int max=30;
		t= JOptionPane.showInputDialog("Définissez le pourcentage de rochers [0-"+max+"]");
		if(t.matches("[0-9]+")){
			pourcentage=Integer.parseInt(t);
			if(pourcentage<=max)isnombre=true;
			
		}
		}while(!isnombre);
		return pourcentage ;
	}
	private void erreurRocher(){
		JOptionPane Taille=new JOptionPane();
		JOptionPane.showMessageDialog(Taille, "Nous n'avons pas pu configurer l'ile. Recommencez", "Error", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * 
	 */
	public void initialiserPartie(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png"};
		int taille=définirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setIle(i); 
		i.placerLesNavires();
		i.placerEau();
		s.println("***********Plaçons les navires***********");
		i.placerCoffre();
		i.placerClé();
		s.println("*******Plaçons le coffre et sa clé*******");
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			erreurRocher();
			s.test.close();
		}
		}while(!Rochers);
		s.println("***********Plaçons les rochers***********");
		s.setJeu();
		s.affichage();
	}
		
}

