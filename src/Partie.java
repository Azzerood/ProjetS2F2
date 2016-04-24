import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

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
	private boolean sontAdjacent(int x, int y, int x2, int y2,boolean voleur,boolean guerrier){
		if(x==x2){
			if(y2==y-1)return true;
			if(y2==y+1)return true;
		}
		if(y==y2){
			if(x2==x-1)return true;
			if(x2==x+1)return true;
		}
		if(voleur || guerrier){
			if(y2==y-1 && x2==x-1) return true;
			if(y2==y-1 && x2==x+1) return true;
			if(y2==y+1 && x2==x-1) return true;
			if(y2==y+1 && x2==x+1) return true;
			
		}
		return false;
	}
	
	/**
	 * Affiche un menu pour que l'utilisateur compose son équipage
	 * @param joueur
	 */
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
		   if(joueur==1)s.i.e1.setNbpersonnages(s.i.e1.getNbpersonnages()+1);
		   if(joueur==2)s.i.e2.setNbpersonnages(s.i.e2.getNbpersonnages()+1);
		}
	}
	
	/**
	 * @param joueur
	 * Compose une équipe automatiquement (IA)
	 */
	public void composerEquipeIA(int joueur){
		int tailleEquipe=5;
		Random rand=new Random();
		int x=rand.nextInt(10);
		if(x>=0){//premiere stratégie (full explorateur)
			
			for(int cpt=0;cpt<tailleEquipe;cpt++){
				if(joueur==1){
					s.i.e1.stratégie=1;
					s.i.e1.equipageAuRepos.add(new Explorateur(true));
				}else{
					s.i.e2.stratégie=1;
					s.i.e2.equipageAuRepos.add(new Explorateur(false));
				}
			}
		}else{//deuxieme stratégie (full guerrier)
			for(int cpt=0;cpt<tailleEquipe;cpt++){
				if(joueur==1){
					s.i.e1.stratégie=2;
					s.i.e1.equipageAuRepos.add(new Guerrier(true));
				}else{
					s.i.e2.stratégie=2;
					s.i.e2.equipageAuRepos.add(new Guerrier(false));
				}
			}
		}
		
	}
	
	/**
	 *   Créer une île selon les paramètre de l'utilisateur et y place les divers élèments nécessaires pour jouer (J vs J)
	 */
	public void initialiserPartieJvsJ(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png","img/ppiegeur1.png","img/ppiegeur2.png","img/pguerrier1.png","img/pguerrier2.png","img/ptresor.png","img/ppiege1.png","img/ppiege2.png","img/ppiegeactif.png"};
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
	 *   Créer une île selon les paramètre de l'utilisateur et y place les divers élèments nécessaires pour jouer (J vs IA)
	 */
	public void initialiserPartieJvsIA(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png","img/ppiegeur1.png","img/ppiegeur2.png","img/pguerrier1.png","img/pguerrier2.png","img/ptresor.png","img/ppiege1.png","img/ppiege2.png","img/ppiegeactif.png"};
		int taille=définirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setIle(i); 
		i.placerLesNavires();
		i.placerEau();
		composerEquipe(1); //le joueur 1 compose son equipage
		composerEquipeIA(2); // le joueur 2 compose son equipage
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
	 *   Créer une île selon les paramètre de l'utilisateur et y place les divers élèments nécessaires pour jouer (IA vs IA)
	 */
	public void initialiserPartieIAvsIA(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png","img/ppiegeur1.png","img/ppiegeur2.png","img/pguerrier1.png","img/pguerrier2.png","img/ptresor.png","img/ppiege1.png","img/ppiege2.png","img/ppiegeactif.png"};
		int taille=définirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setIle(i); 
		i.placerLesNavires();
		i.placerEau();
		composerEquipeIA(1); //le joueur 1 compose son equipage
		composerEquipeIA(2); // le joueur 2 compose son equipage
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
	 * @param joueur
	 * @return Retourne les coordonées du personnage choisi par l'IA s'il adopte la stratégie 1
	 */
	public int[] ChoisirPersoIaS1(int joueur){
		int[] caseChoisi =new int[2];
		boolean trouve=false;
		int indiceClé=0;
		int indiceCoffre=0;
		int meilleurIndice=1000;//permet d'instaurer un indice qui sera forcément dépasser
		int indiceCase;
		for(int l=0;l<s.i.plateau.length;l++){
			for(int c=0;c<s.i.plateau[0].length;c++){
				if(!s.i.plateau[l][c].listeelements.isEmpty()){
					if(s.i.plateau[l][c].listeelements.size()>1){
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(4))){//contient la clé
							indiceClé=l*s.i.plateau.length+c;
							}
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(3))){	//contient le coffre
							indiceCoffre=l*s.i.plateau.length+c;
						}
					}else{
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(3))){//contient le coffre
							indiceCoffre=l*s.i.plateau.length+c;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(4))){//contient la clé
							indiceClé=l*s.i.plateau.length+c;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(5))){//contient le trésor
							indiceCoffre=l*s.i.plateau.length+c;
						}
					}
				}
			}
		}//fin deuxieme boucle for
		if(indiceClé!=0){//si la clé est sur le terrain on la cherche
			for(int l=0;l<s.i.plateau.length;l++){
				for(int c=0;c<s.i.plateau[0].length;c++){
					if(s.i.plateau[l][c].perso!=null && s.i.plateau[l][c].perso.getEquipe()==joueur){
						int indiceActuel=s.i.plateau.length*l+c;
							
								if(((Math.abs(indiceClé%s.i.plateau.length-indiceActuel%s.i.plateau.length))+(Math.abs(indiceClé/s.i.plateau.length-indiceActuel/s.i.plateau.length)))<((Math.abs(indiceClé%s.i.plateau.length-meilleurIndice%s.i.plateau.length))+(Math.abs(indiceClé/s.i.plateau.length-meilleurIndice/s.i.plateau.length)))){
									meilleurIndice=indiceActuel;
								}
					
					}
				}
			}
		}else{//si la clé n'est pas sur la carte => on cherche le coffre
			if(indiceCoffre!=0){
				trouve=false;
			for(int l=0;l<s.i.plateau.length;l++){
				for(int c=0;c<s.i.plateau[0].length;c++){
					if(s.i.plateau[l][c].perso!=null && s.i.plateau[l][c].perso.getEquipe()==joueur ){
						int indiceActuel=s.i.plateau.length*l+c;
						if(!trouve){
							if(((Math.abs(indiceCoffre%s.i.plateau.length-indiceActuel%s.i.plateau.length))+(Math.abs(indiceCoffre/s.i.plateau.length-indiceActuel/s.i.plateau.length)))<((Math.abs(indiceCoffre%s.i.plateau.length-meilleurIndice%s.i.plateau.length))+(Math.abs(indiceCoffre/s.i.plateau.length-meilleurIndice/s.i.plateau.length)))){
								meilleurIndice=indiceActuel;
								
							}
							if(s.i.plateau[l][c].perso.clé){
								
								meilleurIndice=indiceActuel;
								trouve=true;
							}
					
						}
					}
				}
			}
			
			}else{
				
				for(int l=0;l<s.i.plateau.length;l++){
					for(int c=0;c<s.i.plateau[0].length;c++){
						int indiceActuel=s.i.plateau.length*l+c;
						if(s.i.plateau[l][c].perso!=null && s.i.plateau[l][c].perso.getEquipe()==joueur){
							if(!trouve){
								meilleurIndice=indiceActuel;
								if(s.i.plateau[l][c].perso.coffre){
									
									meilleurIndice=indiceActuel;
									trouve=true;
								}
							}
							
						}
					}
				}
				
			}
		}
		caseChoisi[0]=meilleurIndice/s.i.plateau.length;
		caseChoisi[1]=meilleurIndice%s.i.plateau.length;
		return caseChoisi;
	}
	/**
	 * @param PersoChoisi
	 * @param joueur
	 * @return Retourne les coordonées  de la case choisi par l'IA s'il utilise la stratégie 1
	 */
	public int[] ChoisirCaseIaS1(int[] PersoChoisi,int joueur){
		int[] caseChoisi =new int[2];
		boolean choisi=false;
		int[]clé=new int[2];
		int[]coffre=new int[2];
		int[]navire=new int[2];
		int[]casePrecedente=s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.getCasePrecedente();
		boolean pclé=false;
		boolean pcoffre=false;
		int indiceCase;
		for(int l=0;l<s.i.plateau.length;l++){
			for(int c=0;c<s.i.plateau[0].length;c++){
				if(!s.i.plateau[l][c].listeelements.isEmpty()){
					if(s.i.plateau[l][c].listeelements.size()>1){
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(4))){//contient la clé
							clé[0]=l;
							clé[1]=c;
							pclé=true;
							}
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(3))){	//contient le coffre
							coffre[0]=l;
							coffre[1]=c;
							pcoffre=true;
						}
					}else{
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(3))){//contient le coffre
							coffre[0]=l;
							coffre[1]=c;
							pcoffre=true;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(4))){//contient la clé
							clé[0]=l;
							clé[1]=c;
							pclé=true;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(5))){//contient le trésor
							coffre[0]=l;
							coffre[1]=c;
							pcoffre=true;
						}
					}
					if(joueur==1){
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(0))){//contient le navire de l'équipe 1
							navire[0]=l;
							navire[1]=c;
							
						}
					}else{
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(1))){//contient le navire de l'équipe 1
							navire[0]=l;
							navire[1]=c;
							
						}
					}
					
				}
			}
		}//fin deuxieme boucle for
		if(pclé){//si la clé est sur le terrain on va la chercher

			caseChoisi=s.i.getBetterVoisin(PersoChoisi[0],PersoChoisi[1],clé[0], clé[1],joueur,casePrecedente);
			
			
		}else{//si la clé n'est pas sur la carte => on va chercher le coffre
			if(pcoffre){

			caseChoisi=s.i.getBetterVoisin(PersoChoisi[0],PersoChoisi[1],coffre[0], coffre[1],joueur,casePrecedente);
			
			}else{//s'il n'y a ni la clé ni le coffre
				if(s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.coffre){//si le personnage a le trésor 
				caseChoisi=s.i.getBetterVoisin(PersoChoisi[0],PersoChoisi[1],navire[0], navire[1],joueur,casePrecedente); //il va au navire
				}else{//sinon
					caseChoisi=s.i.getBetterVoisin(PersoChoisi[0],PersoChoisi[1],PersoChoisi[0],PersoChoisi[1],joueur,casePrecedente);	//il reste sur place car il sait qu'il a perdu.
				}
			}
		}
		s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.setCasePrecedente(PersoChoisi);
		return caseChoisi;
	}
	
	public int[] ChoisirPersoIaS2(int joueur){ //à définir
		return new int[]{10,10};
	}
	/**
	 * @param x
	 * @param y
	 * affiche dans la console l'inventaire du personnage aux coordonnées(x,y)
	 */
	public void afficherInventaire(int x,int y){
		s.println("-----------------------");
		s.println(s.i.afficherInventaire(x, y));
		s.println("-----------------------");
	}
	
	/**
	 * @return retourne vrai si l'utilisateur souhaite confirmer son choix de déplacement
	
	 */
	public boolean valider(){
		boolean resultat= false;
		int rang;
		String[]choix={"valider","recommencer"};
		JOptionPane jop;
		rang = JOptionPane.showOptionDialog(null, 
			      "Souhaitez vous confirmer cette action ?",
			      "Confirmation de l'action",
			      JOptionPane.YES_NO_CANCEL_OPTION,
			      JOptionPane.QUESTION_MESSAGE,
			      null,
			      choix,
			      choix[1]);
		if(rang==0)resultat=true;
		return resultat;
	}
	/**
	 * Le joueur effectue toutes les actions possibles au cours d'un tour.
	 * @param joueur
	 */
	public void tour(int joueur){
		int[][] vision=s.i.getPlateau(joueur);
		s.refresh(vision);
		boolean valide=false;
		int nbessai;
		int[] persoChoisi;
		int[] caseChoisi;
		do{
			nbessai=0;
			boolean explorateur=false;
			boolean voleur=false;
			boolean guerrier=false;
			boolean personnage=false;
		
		do{
			s.println("Joueur "+joueur+" :");
			persoChoisi=choisirPersonnage();
			//persoChoisi=ChoisirPersoIaS1(joueur);
		}while(!selectionnable(persoChoisi[0], persoChoisi[1], joueur));
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Explorateur)explorateur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Voleur)voleur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Guerrier)guerrier=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Personnage)personnage=true;
		if(personnage)afficherInventaire(persoChoisi[0], persoChoisi[1]);
		do{
			s.println("Joueur "+joueur+" :");
			caseChoisi=choisirCase();
			nbessai+=1;
		}while((!s.i.deplacementPossible(caseChoisi[0],caseChoisi[1],explorateur,voleur,guerrier,joueur) || !sontAdjacent(persoChoisi[0], persoChoisi[1], caseChoisi[0], caseChoisi[1],voleur,guerrier))&& nbessai<5);
		if(nbessai<5)valide=valider();
		}while(!valide);
		s.i.deplacerPersonnage(persoChoisi[0],persoChoisi[1],caseChoisi[0],caseChoisi[1],joueur);
		vision=s.i.getPlateau(joueur);
		s.refresh(vision);
	}
	/**
	 * @param joueur
	 * Effectue un tour pour l'IA 
	 * @throws InterruptedException 
	 */
	public void tourIA(int joueur) throws InterruptedException{
		int[][] vision=s.i.getPlateau(joueur);
		s.refresh(vision);
		Thread.sleep(1000);
		int[] persoChoisi={0,0};
		int[] caseChoisi={0,0};
		if(joueur==1){
			if(s.i.e1.stratégie==1){//si l'équipe est composé d'explorateur seulement
				persoChoisi=ChoisirPersoIaS1(joueur);
				caseChoisi=ChoisirCaseIaS1(persoChoisi, joueur);
			}else{
				if(s.i.e1.stratégie==2){//si l'équipe est composé de guerriers seulement
					persoChoisi=ChoisirPersoIaS2(joueur);
					
				}else{
					
				}
			}
			
		}else{//si c'est le joueur 2
			if(s.i.e2.stratégie==1){//si l'équipe est composé d'explorateur seulement
				persoChoisi=ChoisirPersoIaS1(joueur);
				caseChoisi=ChoisirCaseIaS1(persoChoisi, joueur);
				
			}else{
				if(s.i.e2.stratégie==2){//si l'équipe est composé de guerriers seulement
					persoChoisi=ChoisirPersoIaS2(joueur);
				}else{
					
				}
			}
			
		}
		s.i.deplacerPersonnage(persoChoisi[0], persoChoisi[1], caseChoisi[0], caseChoisi[1], joueur);
		vision=s.i.getPlateau(joueur);
		s.refresh(vision);
	}
	/**
	 * Permet aux personnages dans les navires de récupérer de l'énergie
	 */
	public void recuperationNavire(){
		s.i.e1.recuperationNavire();
		s.i.e2.recuperationNavire();
	}
	public void recuperationPiege(){
		s.i.recuperationPiege();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagné
	 * @throws InterruptedException 
	 */
	public void lancerPartieJvsJ() throws InterruptedException{
		do{
			recuperationPiege();
			recuperationNavire();
			tour(1);
			Thread.sleep(2000);
			tour(2);
			Thread.sleep(2000);
		}while(!s.i.fini());
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagné
	 * @throws InterruptedException 
	 */
	public void lancerPartieJvsIA() throws InterruptedException{
		do{
			recuperationPiege();
			recuperationNavire();
			tour(1);
			Thread.sleep(2000);
			tourIA(2);
			Thread.sleep(2000);
		}while(!s.i.fini());
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagné
	 * @throws InterruptedException 
	 */
	public void lancerPartieIAvsIA() throws InterruptedException{
		int nbtours=0;
		do{
			recuperationPiege();
			recuperationNavire();
			tourIA(1);
			Thread.sleep(2000);
			tourIA(2);
			Thread.sleep(2000);
		}while(!s.i.fini() && nbtours<100); //limite à 100 coups le temps d'une partie
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

