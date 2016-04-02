import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	/**
	 * Affiche un menu à l'utilisateur pour qu'il choisisse que faire
	 * @throws InterruptedException
	 */
	public static void Menu() throws InterruptedException{
		 String[] choix = {"Lancer une partie","Guide" ,"Quitter"};
		    JOptionPane jop = new JOptionPane();
		   int rang;
		   do{ 
		   rang = jop.showOptionDialog(null, 
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
		    	p.lancerPartie();
		    		
		    }else{
		    	if(rang==1){Tuto();}	
		    	else{
		    		
		    	}
		    
		    }
		   }while(rang<choix.length-1);
		   
	}
	
	/**
	 * Affiche les règles du jeu sous forme d'images qui défilent (au clique)
	 * @throws InterruptedException
	 */
	public static void Tuto() throws InterruptedException{
		String[] images={"img/tuto1.png","img/tuto2.png","img/tuto3.png","img/tuto4.png","img/tuto5.png","img/tuto6.png","img/tuto7.png",};
		SuperPlateau s=new SuperPlateau(images,1);
		s.setPreferedSize(820, 650);
		int[][] tab=new int[1][1];
		int nb=1;
		int nbimages=4;
		do{
		tab[0][0]=nb;
		s.setJeu(tab);
		s.affichage();
		InputEvent e=s.waitEvent();
		if(e.getModifiers()==4){ nb=nb-1;
		if(nb<1)nb=1;
		}
		else nb=nb+1;
		}while(nb<=nbimages);
		
		
		s.close();
		
		
	}
	public static void main(String[] args) throws InterruptedException {  
		Menu();
	}

}
