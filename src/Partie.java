import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Partie {
	SuperPlateau s;
	public boolean abandon=false;
	public boolean passerTour=false;
	String[]  imagesjeu={"psol.png","procher.png","pnavire1.png","pnavire2.png","pcoffre.png","pcl�.png","peau.png","pexplo1.png","pexplo2.png","pvoleur1.png","pvoleur2.png","ppiegeur1.png","ppiegeur2.png","pguerrier1.png","pguerrier2.png","ptresor.png","ppiege1.png","ppiege2.png","ppiegeactif.png","pbrouillard.png"};
	private int d�finirTailleIle(){
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
			String[] options = {"OK"};
			JPanel panel = new JPanel();
			JLabel lbl = new JLabel("D�finissez la taille de l'ile [min: 10]");
			JTextField txt = new JTextField(10);
			panel.add(lbl);
			panel.add(txt);
			int selectedOption = JOptionPane.showOptionDialog(null, panel, "Configuration des rochers", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
			if(selectedOption == 0){
		    String text = txt.getText();
		    if(text.matches("[0-9]+")){
		    	taille=Integer.parseInt(text);
		    	if(taille>=10){
		    		isnombre=true;
		    	}
			}
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
			String[] options = {"OK"};
			JPanel panel = new JPanel();
			JLabel lbl = new JLabel("D�finissez le pourcentage de rochers [0-"+max+"]");
			JTextField txt = new JTextField(10);
			panel.add(lbl);
			panel.add(txt);
			int selectedOption = JOptionPane.showOptionDialog(null, panel, "Configuration des rochers", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
		//t= JOptionPane.showOptionDialog("D�finissez le pourcentage de rochers [0-"+max+"]");
		if(selectedOption == 0)
		{
		    String text = txt.getText();
		    if(text.matches("[0-9]+")){
		    	pourcentage=Integer.parseInt(text);
		    	if(pourcentage<=max){isnombre=true;}
		    }
		}
		//if(t.matches("[0-9]+")){
			//pourcentage=Integer.parseInt(t);
		//	if(pourcentage<=max)isnombre=true;
			
		//}
		}while(!isnombre);
		return pourcentage ;
	}
	private void erreurRocher(){
		JOptionPane Taille=new JOptionPane();
		JOptionPane.showMessageDialog(Taille, "D�sol� Capitaine, nous n'avons pas rep�r� une telle �le sur nos cartes. Nous pouvons chercher � nouveau.", "Nous avons un probl�me Capitaine !", JOptionPane.ERROR_MESSAGE);
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
	 * Affiche un menu pour que l'utilisateur compose son �quipage
	 * @param joueur
	 */
	public void composerEquipe(int joueur){
		int tailleEquipe=5;
		for(int cpt=0;cpt<tailleEquipe;cpt++){
			String[] choix = {"Explorateur","Guerrier","Pi�geur","Voleur"};
		    JOptionPane jop = new JOptionPane();
		   int rang;
		   do{ 
		   rang = jop.showOptionDialog(null, 
		      "Quel personnage souhaitez vous ajouter � votre �quipage ? ("+(tailleEquipe-cpt)+" places restantes)",
		      "Compisition de l'�quipage "+joueur,
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
	 * Compose une �quipe automatiquement (IA)
	 */
	public void composerEquipeIA(int joueur){
		int tailleEquipe=5;
		Random rand=new Random();
		int x=rand.nextInt(10);
		if(x>=0){//premiere strat�gie (full explorateur)
			
			for(int cpt=0;cpt<tailleEquipe;cpt++){
				if(joueur==1){
					s.i.e1.strat�gie=1;
					s.i.e1.equipageAuRepos.add(new Explorateur(true));
				}else{
					s.i.e2.strat�gie=1;
					s.i.e2.equipageAuRepos.add(new Explorateur(false));
				}
			}
		}else{//deuxieme strat�gie (full guerrier)
			for(int cpt=0;cpt<tailleEquipe;cpt++){
				if(joueur==1){
					s.i.e1.strat�gie=2;
					s.i.e1.equipageAuRepos.add(new Guerrier(true));
				}else{
					s.i.e2.strat�gie=2;
					s.i.e2.equipageAuRepos.add(new Guerrier(false));
				}
			}
		}
		
	}
	
	/**
	 *   Cr�er une �le selon les param�tre de l'utilisateur et y place les divers �l�ments n�cessaires pour jouer (J vs J)
	 */
	public void initialiserPartieJvsJ(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images=imagesjeu;
		int taille=d�finirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setP(this);
		s.setIle(i); 
		s.close(); // ferme la fenetre du plateau le temps de composer les equipes
		i.addPlateau(s);
		i.placerLesNavires();
		i.placerEau();
		composerEquipe(1); //le joueur 1 compose son equipage
		composerEquipe(2); // le joueur 2 compose son equipage
		i.placerLesEquipages();
		i.placerCoffre();
		i.placerCl�();
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
	 *   Cr�er une �le selon les param�tre de l'utilisateur et y place les divers �l�ments n�cessaires pour jouer (J vs IA)
	 */
	public void initialiserPartieJvsIA(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images=imagesjeu;
		int taille=d�finirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.close();
		s.setP(this);
		s.setIle(i); 
		i.addPlateau(s);
		i.placerLesNavires();
		i.placerEau();
		composerEquipe(1); //le joueur 1 compose son equipage
		composerEquipeIA(2); // le joueur 2 compose son equipage
		i.placerLesEquipages();
		i.placerCoffre();
		i.placerCl�();
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
	 *   Cr�er une �le selon les param�tre de l'utilisateur et y place les divers �l�ments n�cessaires pour jouer (IA vs IA)
	 */
	public void initialiserPartieIAvsIA(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images=imagesjeu;
		int taille=d�finirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setP(this);
		s.setIle(i); 
		i.addPlateau(s);
		i.placerLesNavires();
		i.placerEau();
		composerEquipeIA(1); //le joueur 1 compose son equipage
		composerEquipeIA(2); // le joueur 2 compose son equipage
		i.placerLesEquipages();
		i.placerCoffre();
		i.placerCl�();
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
	 * @return retourne les coordonn�es du personnage choisi par l'utilisateur
	 */
	public int[] choisirPersonnage(){
		int[] coordonn�es=new int[2];
		s.println("Choissisez un personnage");
		InputEvent event=s.waitEvent();
		if(!abandon && !passerTour){
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonn�es[0]=x;
		coordonn�es[1]=y;
		}
		return coordonn�es;
		
	}
	/**
	 * @return retourne les coordonn�es de la case choisi par l'utilisateur
	 */
	public int[] choisirCase(){
		int[] coordonn�es=new int[2];
		s.println("Choissisez o� aller");
		
		InputEvent event=s.waitEvent();
		if(!abandon && !passerTour){
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonn�es[0]=x;
		coordonn�es[1]=y;
		}
		return coordonn�es;
	}
	/**
	 * @param joueur
	 * @return Retourne les coordon�es du personnage choisi par l'IA s'il adopte la strat�gie 1
	 */
	private int[] ChoisirPersoIaS1(int joueur){
		int[] caseChoisi =new int[2];
		boolean trouve=false;
		int indiceCl�=0;
		int indiceCoffre=0;
		int meilleurIndice=1000;//permet d'instaurer un indice qui sera forc�ment d�passer
		int indiceCase;
		for(int l=0;l<s.i.plateau.length;l++){
			for(int c=0;c<s.i.plateau[0].length;c++){
				if(!s.i.plateau[l][c].listeelements.isEmpty()){
					if(s.i.plateau[l][c].listeelements.size()>1){
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(4))){//contient la cl�
							indiceCl�=l*s.i.plateau.length+c;
							}
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(3))){	//contient le coffre
							indiceCoffre=l*s.i.plateau.length+c;
						}
					}else{
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(3))){//contient le coffre
							indiceCoffre=l*s.i.plateau.length+c;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(4))){//contient la cl�
							indiceCl�=l*s.i.plateau.length+c;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(5))){//contient le tr�sor
							indiceCoffre=l*s.i.plateau.length+c;
						}
					}
				}
			}
		}//fin deuxieme boucle for
		if(indiceCl�!=0){//si la cl� est sur le terrain on la cherche
			for(int l=0;l<s.i.plateau.length;l++){
				for(int c=0;c<s.i.plateau[0].length;c++){
					if(s.i.plateau[l][c].perso!=null && s.i.plateau[l][c].perso.getEquipe()==joueur){
						int indiceActuel=s.i.plateau.length*l+c;
							
								if(((Math.abs(indiceCl�%s.i.plateau.length-indiceActuel%s.i.plateau.length))+(Math.abs(indiceCl�/s.i.plateau.length-indiceActuel/s.i.plateau.length)))<((Math.abs(indiceCl�%s.i.plateau.length-meilleurIndice%s.i.plateau.length))+(Math.abs(indiceCl�/s.i.plateau.length-meilleurIndice/s.i.plateau.length)))){
									meilleurIndice=indiceActuel;
								}
					
					}
				}
			}
		}else{//si la cl� n'est pas sur la carte => on cherche le coffre
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
							if(s.i.plateau[l][c].perso.cl�){
								
								meilleurIndice=indiceActuel;
								trouve=true;
							}
					
						}
					}
				}
			}
			if(!trouve){
				passerTour=true;
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
		
		if(meilleurIndice==1000)passerTour=true;
		caseChoisi[0]=meilleurIndice/s.i.plateau.length;
		caseChoisi[1]=meilleurIndice%s.i.plateau.length;
		return caseChoisi;
	}
	/**
	 * @param PersoChoisi
	 * @param joueur
	 * @return Retourne les coordon�es  de la case choisi par l'IA s'il utilise la strat�gie 1
	 */
	private int[] ChoisirCaseIaS1(int[] PersoChoisi,int joueur){
		int[] caseChoisi =new int[2];
		boolean choisi=false;
		int[]cl�=new int[2];
		int[]coffre=new int[2];
		int[]navire=new int[2];
		int[]casePrecedente=s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.getCasePrecedente();
		boolean pcl�=false;
		boolean pcoffre=false;
		int indiceCase;
		for(int l=0;l<s.i.plateau.length;l++){
			for(int c=0;c<s.i.plateau[0].length;c++){
				if(!s.i.plateau[l][c].listeelements.isEmpty()){
					if(s.i.plateau[l][c].listeelements.size()>1){
						if(s.i.plateau[l][c].listeelements.get(1).compareTo(new Element(4))){//contient la cl�
							cl�[0]=l;
							cl�[1]=c;
							pcl�=true;
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
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(4))){//contient la cl�
							cl�[0]=l;
							cl�[1]=c;
							pcl�=true;
						}
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(5))){//contient le tr�sor
							coffre[0]=l;
							coffre[1]=c;
							pcoffre=true;
						}
					}
					if(joueur==1){
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(0))){//contient le navire de l'�quipe 1
							navire[0]=l;
							navire[1]=c;
							
						}
					}else{
						if(s.i.plateau[l][c].listeelements.get(0).compareTo(new Element(1))){//contient le navire de l'�quipe 1
							navire[0]=l;
							navire[1]=c;
							
						}
					}
					
				}
			}
		}//fin deuxieme boucle for
		if(pcl�){//si la cl� est sur le terrain on va la chercher

			caseChoisi=s.i.getMeilleurVoisin(PersoChoisi[0],PersoChoisi[1],cl�[0], cl�[1],joueur,casePrecedente);
			
			
		}else{//si la cl� n'est pas sur la carte => on va chercher le coffre
			if(pcoffre){

			caseChoisi=s.i.getMeilleurVoisin(PersoChoisi[0],PersoChoisi[1],coffre[0], coffre[1],joueur,casePrecedente);
			
			}else{//s'il n'y a ni la cl� ni le coffre
				if(s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.coffre){//si le personnage a le tr�sor 
				caseChoisi=s.i.getMeilleurVoisin(PersoChoisi[0],PersoChoisi[1],navire[0], navire[1],joueur,casePrecedente); //il va au navire
				}else{//sinon
					passerTour=true;
					caseChoisi[0]=0;
					caseChoisi[1]=1;
				}
			}
		}
		s.i.plateau[PersoChoisi[0]][PersoChoisi[1]].perso.setCasePrecedente(PersoChoisi);
		return caseChoisi;
	}
	
	private int[] ChoisirPersoIaS2(int joueur){ //� d�finir
		return new int[]{10,10};
	}
	/**
	 * @param x
	 * @param y
	 * affiche dans la console l'inventaire du personnage aux coordonn�es(x,y)
	 */
	public void afficherInventaire(int x,int y){
		s.println("-----------------------");
		s.println(s.i.afficherInventaire(x, y));
		s.println("-----------------------");
	}
	
	/**
	 * @return retourne vrai si l'utilisateur souhaite confirmer son choix de d�placement
	
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
	public void tour(int joueur,boolean godMod){
		int[][] vision;
		if(godMod){vision=s.i.getPlateau(godMod);}
		else{vision=s.i.getPlateau(joueur);}
		s.refresh(vision);
		//boolean valide=false;
		int nbessai;
		int[] persoChoisi;
		int[] caseChoisi = null;
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
			
		}while(!selectionnable(persoChoisi[0], persoChoisi[1], joueur) && !abandon && !passerTour);
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Explorateur)explorateur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Voleur)voleur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Guerrier)guerrier=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Personnage)personnage=true;
		if(personnage)afficherInventaire(persoChoisi[0], persoChoisi[1]);
		if(!abandon && !passerTour){
		do{
			s.println("Joueur "+joueur+" :");
			caseChoisi=choisirCase();
			nbessai+=1;
			
		}while((!s.i.deplacementPossible(caseChoisi[0],caseChoisi[1],explorateur,voleur,guerrier,joueur) || !sontAdjacent(persoChoisi[0], persoChoisi[1], caseChoisi[0], caseChoisi[1],voleur,guerrier))&& nbessai<5 && !abandon && !passerTour);
		}
		//if(nbessai<5)valide=valider();
		}while(nbessai>=5 );
		if(!abandon && !passerTour){
		s.i.deplacerPersonnage(persoChoisi[0],persoChoisi[1],caseChoisi[0],caseChoisi[1],joueur);
		}
		if(godMod){vision=s.i.getPlateau(godMod);}
		else{vision=s.i.getPlateau(joueur);}
		s.refresh(vision);
	}
	/**
	 * @param joueur
	 * Effectue un tour pour l'IA 
	 * @throws InterruptedException 
	 */
	public void tourIA(int joueur,boolean godMod) throws InterruptedException{
		int[][]vision;
		if(godMod){vision=s.i.getPlateau(godMod);}
		else{vision=s.i.getPlateau(joueur);}
		s.refresh(vision);
		Thread.sleep(500);
		int[] persoChoisi={0,0};
		int[] caseChoisi={0,0};
		if(joueur==1){
			if(s.i.e1.strat�gie==1){//si l'�quipe est compos� d'explorateur seulement
				persoChoisi=ChoisirPersoIaS1(joueur);
				if(!abandon && !passerTour){
					caseChoisi=ChoisirCaseIaS1(persoChoisi, joueur);
				}
			}else{
				if(s.i.e1.strat�gie==2){//si l'�quipe est compos� de guerriers seulement
					persoChoisi=ChoisirPersoIaS2(joueur);
					
				}else{
					
				}
			}
			
		}else{//si c'est le joueur 2
			if(s.i.e2.strat�gie==1){//si l'�quipe est compos� d'explorateur seulement
				persoChoisi=ChoisirPersoIaS1(joueur);
				if(!abandon && !passerTour){
					caseChoisi=ChoisirCaseIaS1(persoChoisi, joueur);
				}
				
			}else{
				if(s.i.e2.strat�gie==2){//si l'�quipe est compos� de guerriers seulement
					persoChoisi=ChoisirPersoIaS2(joueur);
				}else{
					
				}
			}
			
		}
		
		if(!abandon && !passerTour){
			s.i.deplacerPersonnage(persoChoisi[0],persoChoisi[1],caseChoisi[0],caseChoisi[1],joueur);
			}
		if(godMod){vision=s.i.getPlateau(godMod);}
		else{vision=s.i.getPlateau(joueur);}
		s.refresh(vision);
	}
	/**
	 * Permet aux personnages dans les navires de r�cup�rer de l'�nergie
	 */
	public void recuperationNavire(){
		s.i.e1.recuperationNavire();
		s.i.e2.recuperationNavire();
	}
	/**
	 * D�cr�mente de 1 le compteur de tours pi�g� pour les personnages actuellement pris dans un pi�ge
	 */
	public void recuperationPiege(){
		s.i.recuperationPiege();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagn�
	 * @throws InterruptedException 
	 */
	public void lancerPartieJvsJ() throws InterruptedException{
		do{
			passerTour=false;
			recuperationPiege();
			recuperationNavire();
			tour(1,false);
			if(passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			passerTour=false;
			if(!abandon){
				Thread.sleep(2000);
				if(!s.i.fini()){
					tour(2,false);
					if(passerTour){
						s.println("Le joueur 2 passe son tour");
					}
					Thread.sleep(2000);
					
				}
			}
		}while(!s.i.fini() && !abandon);
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagn�
	 * @throws InterruptedException 
	 */
	public void lancerPartieJvsIA() throws InterruptedException{
		do{
			passerTour=false;
			recuperationPiege();
			recuperationNavire();
			tour(1,false);
			if(passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			passerTour=false;
			Thread.sleep(2000);
				if(!s.i.fini()){
					tourIA(2,false);
					if(passerTour){
						s.println("Le joueur 2 passe son tour");
					}
					Thread.sleep(2000);
					
			}
		}while(!s.i.fini()  && !abandon);
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagn�
	 * @throws InterruptedException 
	 */
	public void lancerPartieIAvsIA() throws InterruptedException{
		int nbtours=0;
		do{
			passerTour=false;
			recuperationPiege();
			recuperationNavire();
			tourIA(1,false);
			if(passerTour){
				s.println("Le joueur 1 passe son tour");
			}
			passerTour=false;
			Thread.sleep(2000);
			if(!s.i.fini()){
				tourIA(2,false);
				if(passerTour){
					s.println("Le joueur 2 passe son tour");
				}
				Thread.sleep(2000);
				
			}
		}while(!s.i.fini() && nbtours<100  && !abandon); //limite � 100 coups le temps d'une partie
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Affiche le vainqueur de la partie
	 */
	public void afficherVainqueur(){
		boolean gagnant=false;
		if(s.i.e1.tresor || s.i.e2.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 1 a gagn�!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}else
		if(s.i.e2.tresor || s.i.e1.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 2 a gagn�!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}
		if(abandon)JOptionPane.showMessageDialog (null, "Abandon", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		else	if(!gagnant)JOptionPane.showMessageDialog (null, "Match nul", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
	}
}

