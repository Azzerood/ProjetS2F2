import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	static String[] imagesjeu={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png","img/ppiegeur1.png","img/ppiegeur2.png","img/pguerrier1.png","img/pguerrier2.png","img/ptresor.png","img/ppiege1.png","img/ppiege2.png","img/ppiegeactif.png","img/pbrouillard.PNG"};
	/**
	 * Affiche un menu à l'utilisateur pour qu'il choisisse que faire
	 * @throws InterruptedException
	 */
	public static void menu() throws InterruptedException{
		 String[] choix = {"Lancer une partie","Guide" ,"Sandbox" ,"Quitter"};
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
		      choix[2]);
		    if(rang==0){
		    	MenuLancerPartie();
		    		
		    }else{
		    	if(rang==1){tuto();}	
		    	else{
		    		if(rang==2){menuTest();}
		    	}
		    
		    }
		   }while(rang<choix.length-1);
		   
	}
	private static void MenuLancerPartie() throws InterruptedException{
		String[] choix = {"joueur contre joueur" ,"joueur contre IA" ,"IA contre IA"};
	    JOptionPane jop = new JOptionPane();
	   int rang;
	   rang = jop.showOptionDialog(null, 
	      "Choissisez des joueurs",
	      "Lancer une partie",
	      JOptionPane.YES_NO_CANCEL_OPTION,
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      choix,
	      choix[2]);
	    if(rang==0){
	    	Partie p=new Partie(); 
	    	p.initialiserPartieJvsJ();
	    	p.lancerPartieJvsJ();
	    		
	    }else{
	    	if(rang==1){
	    		Partie p=new Partie(); 
	        	p.initialiserPartieJvsIA();
	        	p.lancerPartieJvsIA();}	
	    	else{
	    		if(rang==2){
	    			Partie p=new Partie(); 
		        	p.initialiserPartieIAvsIA();
		        	p.lancerPartieIAvsIA()	;}
	    	}
	    
	    }
	   
		
	}
	
	/**
	 * @throws InterruptedException
	 * Affiche un menu pour que l'utilisateur choissise quelle fonctionnalité il shouhaite tester
	 */
	public static void menuTest()throws InterruptedException{
		String[] choix = {"Déplacement","Placement des personnages","Composition des équipes","Quitter"};
	    JOptionPane jop = new JOptionPane();
	   int rang;
	   do{ 
	   rang = jop.showOptionDialog(null, 
	      "Quelle fonctionnalité souhaitez vous tester ?",
	      "Menu des tests",
	      JOptionPane.YES_NO_CANCEL_OPTION,
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      choix,
	      choix[1]);
	    if(rang==0){
	    	testerDeplacement();
	    		
	    }else{
	    	if(rang==1){testerPlacement();}	
	    	else{
	    		if(rang==2){testerCompositionDesEquipes();}
	    		else{
	    			
	    		}
	    	}
	    
	    }
	   }while(rang<choix.length-1);
	   
	}
	
	
	/**
	 *  Affiche un menu pour que l'utilisateur puisse tester la composition des équipages
	 */
	public static void testerCompositionDesEquipes(){
		Partie p = new Partie();
		boolean Rochers=false;
		Ile i;
		String[] images=imagesjeu;
		i=new Ile(10);
		p.s=new SuperPlateau(images, 10,true);
		p.s.setIle(i); 
		p.s.setP(p);
		p.s.i.addPlateau(p.s);
		p.s.close();
		p.composerEquipe(1); //le joueur 1 compose son equipage
		System.out.println(p.s.i.e1.equipageAuRepos.toString());
	}
	
	/**
	 * Affiche un plateau pour tester le déplacement des personnages
	 * @throws InterruptedException 
	 */
	public static void testerDeplacement() throws InterruptedException{ 
		boolean Rochers;
		Partie p=new Partie();
		Ile i;
		SuperPlateau s;
		do{
		String[] images=imagesjeu;
		int taille=8;
		int pourcentage=2;
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setP(p);
		s.setIle(i);
		p.s=s;
		p.s.i.addPlateau(s);
		i.placerLesNavires();
		i.placerEau();
		i.placerCoffre();
		i.placerClé();
		i.composerLesEquipe();
		i.placerLesEquipages();
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			s.test.close();
		}
		}while(!Rochers);
		s.setJeu();
		s.affichage();
		JOptionPane.showMessageDialog (null, "Cliquez sur un personnage allié puis une parcelle pour vous déplacer/intéragir avec la parcelle", "Déplacement des personnages", JOptionPane.INFORMATION_MESSAGE);
		do{
			p.recuperationPiege();
			p.recuperationNavire();
			p.tour(1,true);
			//Thread.sleep(2000);
			//p.tour(2);
			//Thread.sleep(2000);
		}while(!p.s.i.fini());
		p.s.close();
	}
	/**
	 * Affiche un plateau de jeu pour tester le Placement des personnages depuis le navire
	 * @throws InterruptedException 
	 */
	public static void testerPlacement() throws InterruptedException{
		boolean Rochers;
		Partie p=new Partie();
		Ile i;
		SuperPlateau s;
		do{
		String[] images=imagesjeu;
		int taille=10;
		int pourcentage=2;
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setP(p);
		s.setIle(i);
		p.s=s;
		p.s.i.addPlateau(s);
		i.placerLesNavires();
		i.placerEau();
		i.placerCoffre();
		i.placerClé();
		i.composerLesEquipe();
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			s.test.close();
		}
		}while(!Rochers);
		s.setJeu();
		s.affichage();
		JOptionPane.showMessageDialog (null, "Cliquez sur le navire puis une parcelle vide pour placer votre équipage", "Placement des personnages", JOptionPane.INFORMATION_MESSAGE);
		do{
			p.tour(1,true);
			Thread.sleep(2000);
			
		}while(!s.i.e1.equipageAuRepos.isEmpty() && !s.i.e2.equipageAuRepos.isEmpty());
		p.s.refresh();
		p.s.close();
	}
	
	/**
	 * Affiche les règles du jeu sous forme d'images qui défilent (au clique)
	 * @throws InterruptedException
	 */
	public static void tuto() throws InterruptedException{
		String[] images={"img/tuto1.png","img/tuto2.png","img/tuto3.png","img/tuto4.png","img/tuto5.png","img/tuto6.png","img/tuto7.png",};
		SuperPlateau s=new SuperPlateau(images,1);
		s.setPreferedSize(820, 650);
		int[][] tab=new int[1][1];
		int nb=1;
		int nbimages=5;
		do{
		tab[0][0]=nb;
		s.setJeu(tab);
		s.affichage();
		InputEvent e=s.waitEvent();
		if(e.getModifiers()==nbimages){ nb=nb-1;
		if(nb<1)nb=1;
		}
		else nb=nb+1;
		}while(nb<=nbimages);
		
		
		s.close();
		
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {  
		menu();
	}

}
