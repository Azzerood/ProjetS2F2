import javax.swing.JOptionPane;

public class Main {
	
	public static void Menu() throws InterruptedException{
		 String[] choix = {"Lancer une partie","Guide" ,"Quitter"};
		    JOptionPane jop = new JOptionPane();
		    int rang = jop.showOptionDialog(null, 
		      "Que souhaitez vous faire?",
		      "Menu Principal",
		      JOptionPane.YES_NO_CANCEL_OPTION,
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      choix,
		      choix[1]);
		    if(rang==0){
		    	Partie p=new Partie(); 
		    	p.initialiserPartie();
		    	p.s.i.plateau[6][1].perso=new Explorateur(true); // ajoute un personnage pour les test.
		    	p.s.i.plateau[6][8].perso=new Explorateur(false); // ajoute un personnage pour les test.
		    	p.lancerPartie();
		    	Menu();	
		    }else{
		    	if(rang==1){Tuto();Menu();}	
		    	else{
		    		
		    	}
		    
		    }
	}
	public static void Tuto() throws InterruptedException{
		String[] images={"img/tuto1.png","img/tuto2.png","img/tuto3.png","img/tuto4.png","img/tuto5.png","img/tuto6.png","img/tuto7.png",};
		SuperPlateau s=new SuperPlateau(images,1);
		int[][] tab=new int[1][1];
		tab[0][0]=1;
		s.setJeu(tab);
		s.affichage();
		Thread.sleep(6000) ;
		tab[0][0]=2;
		s.setJeu(tab);
		s.affichage();
		Thread.sleep(10000) ;
		tab[0][0]=3;
		s.setJeu(tab);
		s.affichage();
		
		
		s.close();
		
		
	}
	public static void main(String[] args) throws InterruptedException { //EN PHASE DE CONSTRUCTION 
		Menu();
	}

}
