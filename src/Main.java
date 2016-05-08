import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	static String[] imagesjeu={"psol.png","procher.png","pnavire1.png","pnavire2.png","pcoffre.png","pclé.png","peau.png","pexplo1.png","pexplo2.png","pvoleur1.png","pvoleur2.png","ppiegeur1.png","ppiegeur2.png","pguerrier1.png","pguerrier2.png","ptresor.png","ppiege1.png","ppiege2.png","ppiegeactif.png","pbrouillard.png"};
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
		String[] choix = {"Déplacement","Placement des personnages","Composition des équipes","IA","Quitter"};
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
	    			if(rang==3){
	    				IA();
	    			}else{
	    				
	    			}
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
			if(p.passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			p.passerTour=false;
			Thread.sleep(1000);
			//p.tour(2);
			//Thread.sleep(2000);
		}while(!p.s.i.fini() && !p.abandon);
		p.afficherVainqueur();
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
			Thread.sleep(1000);
			if(p.passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			p.passerTour=false;
			
		}while(!s.i.e1.equipageAuRepos.isEmpty() && !s.i.e2.equipageAuRepos.isEmpty() && !p.abandon);
		p.s.refresh();
		p.s.close();
	}
	
	/**
	 * Affiche les règles du jeu sous forme d'images qui défilent (au clique)
	 * @throws InterruptedException
	 */
	public static void tuto() throws InterruptedException{
		String[] images={"tuto1.png","tuto2.png","tuto3.png","tuto4.png","tuto5.png","tuto6.png","tuto7.png",};
		SuperPlateau s=new SuperPlateau(images,1);
		s.setPreferedSize(820, 650);
		int[][] tab=new int[1][1];
		int nb=1;
		int nbimages=5;
		do{
		tab[0][0]=nb;
		Partie p=new Partie();
		s.setP(p);
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
	/**
	 * Affiche un plateau de jeu pour tester le déplacement des personnages réalisé par l'IA
	 * @throws InterruptedException 
	 */
	public static void IA() throws InterruptedException{
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
		p.composerEquipeIA(1); //le joueur 1 compose son equipage
		p.composerEquipeIA(2); // le joueur 2 compose son equipage
		i.placerLesEquipages();
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			s.test.close();
		}
		}while(!Rochers);
		s.setJeu();
		s.affichage();
		
		do{
			p.recuperationPiege();
			p.recuperationNavire();
			p.tourIA(1,true);
			if(p.passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			p.passerTour=false;
			//Thread.sleep(2000);
			//p.tour(2);
			//Thread.sleep(2000);
		}while(!p.s.i.fini() && !p.abandon);
		p.s.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException {  
		menu();
	}

}
