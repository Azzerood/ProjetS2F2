import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;


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
	
	private boolean selectionnable(int x, int y ,int joueur){
		if(s.i.plateau[x][y].perso!=null  && s.i.plateau[x][y].perso.getEquipe()==joueur )return true;
		if(s.i.plateau[x][y].estNavireDe(joueur))return true;
		return false;
	}
	/**
	 * 
	 */
	private boolean sontAdjacent(int x, int y, int x2, int y2,boolean voleur){
		if(x==x2){
			if(y2==y-1)return true;
			if(y2==y+1)return true;
		}
		if(y==y2){
			if(x2==x-1)return true;
			if(x2==x+1)return true;
		}
		if(voleur){
			if(y2==y-1 && x2==x-1) return true;
			if(y2==y-1 && x2==x+1) return true;
			if(y2==y+1 && x2==x-1) return true;
			if(y2==y+1 && x2==x+1) return true;
			
		}
		return false;
	}
	
	public void composerEquipe(int joueur){
		int tailleEquipe=5;
		for(int cpt=0;cpt<tailleEquipe;cpt++){
			String[] choix = {"Explorateur","Guerrier","Piégeur","Voleur"};
		    JOptionPane jop = new JOptionPane();
		   int rang;
		   do{ 
		   rang = jop.showOptionDialog(null, 
		      "Quel personnage souhaitez vous ajouter à votre équipage ? ("+(tailleEquipe-cpt)+" places restantes)",
		      "Compisition de l'équipage "+joueur,
		      JOptionPane.YES_NO_CANCEL_OPTION,
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      choix,
		      choix[1]);
		    if(rang==0){
		    	if(joueur==1)s.i.e1.equipageAuRepos.add(new Explorateur(true));
		    	else s.i.e2.equipageAuRepos.add(new Explorateur(false));
		    		
		    }else{
		    	if(rang==1){
		    		if(joueur==1)s.i.e1.equipageAuRepos.add(new Guerrier(true));
			    	else s.i.e2.equipageAuRepos.add(new Guerrier(false));
		    	}	
		    	else{
		    		if(rang==2){
		    			if(joueur==1)s.i.e1.equipageAuRepos.add(new Piegeur(true));
				    	else s.i.e2.equipageAuRepos.add(new Piegeur(false));
		    		}
		    		else{
		    			if(rang==3){
		    				if(joueur==1)s.i.e1.equipageAuRepos.add(new Voleur(true));
					    	else s.i.e2.equipageAuRepos.add(new Voleur(false));
		    			}
		    		}
		    	}
		    
		    }
		   }while(rang<0 && rang>choix.length);
		}
	}
	
	/**
	 *   Créer une île selon les paramètre de l'utilisateur et y place les divers élèments nécessaires pour jouer
	 */
	public void initialiserPartie(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png","img/ppiegeur1.png","img/ppiegeur2.png","img/pguerrier1.png","img/pguerrier2.png","img/ptresor.png"};
		int taille=définirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setIle(i); 
		i.placerLesNavires();
		i.placerEau();
		composerEquipe(1); //le joueur 1 compose son equipage
		composerEquipe(2); // le joueur 2 compose son equipage
		i.placerLesEquipages();
		i.placerCoffre();
		i.placerClé();
		Rochers=i.placerRocher(pourcentage);
		if(!Rochers){
			erreurRocher();
			s.test.close();
		}
		}while(!Rochers);
		s.setJeu();
		s.affichage();
	}
	/**
	 * @return retourne les coordonnées du personnage choisi par l'utilisateur
	 */
	public int[] choisirPersonnage(){
		int[] coordonnées=new int[2];
		s.println("Choissisez un personnage");
		InputEvent event=s.waitEvent();
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonnées[0]=x;
		coordonnées[1]=y;
		return coordonnées;
		
	}
	/**
	 * @return retourne les coordonnées de la case choisi par l'utilisateur
	 */
	public int[] choisirCase(){
		int[] coordonnées=new int[2];
		s.println("Choissisez où aller");
		InputEvent event=s.waitEvent();
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonnées[0]=x;
		coordonnées[1]=y;
		return coordonnées;
	}
	/**
	 * Le joueur effectue toutes les actions possibles au cours d'un tour.
	 * @param joueur
	 */
	public void tour(int joueur){
		s.refresh();
		boolean explorateur=false;
		boolean voleur=false;
		boolean guerrier=false;
		int[] persoChoisi;
		int[] caseChoisi;
		do{
			s.println("Joueur "+joueur+" :");
			persoChoisi=choisirPersonnage();
		}while(!selectionnable(persoChoisi[0], persoChoisi[1], joueur));
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Explorateur)explorateur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Voleur)voleur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Guerrier)guerrier=true;
		do{
			s.println("Joueur "+joueur+" :");
			caseChoisi=choisirCase();
		}while(!s.i.deplacementPossible(caseChoisi[0],caseChoisi[1],explorateur,voleur,guerrier,joueur) || !sontAdjacent(persoChoisi[0], persoChoisi[1], caseChoisi[0], caseChoisi[1],voleur));
		s.i.deplacerPersonnage(persoChoisi[0],persoChoisi[1],caseChoisi[0],caseChoisi[1],joueur);
	
	}
	/**
	 * Permet aux personnages dans les navires de récupérer de l'énergie
	 */
	public void recuperationNavire(){
		s.i.e1.recuperationNavire();
		s.i.e2.recuperationNavire();
	}
	
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagné
	 */
	public void lancerPartie(){
		do{
			recuperationNavire();
			tour(1);
			tour(2);
		}while(!s.i.fini());
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Affiche le vainqueur de la partie
	 */
	public void afficherVainqueur(){
		boolean gagnant=false;
		if(s.i.e1.tresor || s.i.e2.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 1 a gagné!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}else
		if(s.i.e2.tresor || s.i.e1.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 2 a gagné!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}
		if(!gagnant)JOptionPane.showMessageDialog (null, "Match nul", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
	}
}

