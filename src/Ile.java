import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Ile {
	Parcelle[][] plateau; //plateau
	Equipe e1=new Equipe(1);
	Equipe e2=new Equipe(2);
	/**
	 * @param d
	 */
	Ile(int d){
		plateau=new Parcelle[d][d];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				plateau[l][c]=new Parcelle();
			}
		}
	}
	/**
	 * 
	 */
	Ile(){
		plateau=new Parcelle[10][10];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				plateau[l][c]=new Parcelle();
			}
		}
		
		
	}
	
	/**
	 *  Remplie la liste de personnage des deux équipes
	 */
	public void composerLesEquipe(){ 
		e1.composerEquipe();
		e2.composerEquipe();
	}
	private void placerEquipage1(){
		int x1=0,y1=0;
		
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau.length;c++){
				if(!plateau[l][c].listeelements.isEmpty()){
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(0))){ //si la case contient le navire1 
						x1=l;y1=c;
					}
					
				}
			}
		}
		
		if(plateau[x1-1][y1].listeelements.isEmpty() && plateau[x1-1][y1].perso==null && !e1.equipageAuRepos.isEmpty()){plateau[x1-1][y1].perso=e1.equipageAuRepos.get(0);e1.poserPersonnage(0);}
		if(plateau[x1+1][y1].listeelements.isEmpty() && plateau[x1+1][y1].perso==null && !e1.equipageAuRepos.isEmpty()){plateau[x1+1][y1].perso=e1.equipageAuRepos.get(0);e1.poserPersonnage(0);}
		if(plateau[x1][y1-1].listeelements.isEmpty() && plateau[x1][y1-1].perso==null && !e1.equipageAuRepos.isEmpty()){plateau[x1][y1-1].perso=e1.equipageAuRepos.get(0);e1.poserPersonnage(0);}
		if(plateau[x1][y1+1].listeelements.isEmpty() && plateau[x1][y1+1].perso==null && !e1.equipageAuRepos.isEmpty()){plateau[x1][y1+1].perso=e1.equipageAuRepos.get(0);e1.poserPersonnage(0);}
		
	}
	private void placerEquipage2(){
		int x1=0,y1=0;

		
		
		
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau.length;c++){
				if(!plateau[l][c].listeelements.isEmpty()){
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(1))){ //si la case contient le navire2
						x1=l;y1=c;
					}
					
				}
			}
		}
		
		if(plateau[x1-1][y1].listeelements.isEmpty() && plateau[x1-1][y1].perso==null && !e2.equipageAuRepos.isEmpty()){plateau[x1-1][y1].perso=e2.equipageAuRepos.get(0);e2.poserPersonnage(0);}
		if(plateau[x1+1][y1].listeelements.isEmpty() && plateau[x1+1][y1].perso==null && !e2.equipageAuRepos.isEmpty()){plateau[x1+1][y1].perso=e2.equipageAuRepos.get(0);e2.poserPersonnage(0);}
		if(plateau[x1][y1-1].listeelements.isEmpty() && plateau[x1][y1-1].perso==null && !e2.equipageAuRepos.isEmpty()){plateau[x1][y1-1].perso=e2.equipageAuRepos.get(0);e2.poserPersonnage(0);}
		if(plateau[x1][y1+1].listeelements.isEmpty() && plateau[x1][y1+1].perso==null && !e2.equipageAuRepos.isEmpty()){plateau[x1][y1+1].perso=e2.equipageAuRepos.get(0);e2.poserPersonnage(0);}
		
	}
	
	/**
	 *  Place sur l'île les personnage de l'équipe 1 et 2
	 */
	public void placerLesEquipages(){//à réaliser par Yann et Arthur
		placerEquipage1();
		placerEquipage2();
	}
	/**
	 * place les navires des deux équipes à des positions fixées
	 */
	public void placerLesNavires(){ 
		plateau[plateau.length/2][1].ajouterNaviree1();
		plateau[plateau.length/2][plateau[0].length-2].ajouterNaviree2();
		
	}
	
	/**
	 * @param x
	 * @param y
	 * @return retourne vrai si les cases autours (haut, bas, gauche, droite) des navires sont vides .
	 */
	public boolean accostagepossible(int x,int y){
		boolean bloque=false;
		if(x>1){
		if(!plateau[x-1][y].listeelements.isEmpty())bloque=true;
		}
		if(x<plateau.length-2){
		if(!plateau[x+1][y].listeelements.isEmpty())bloque=true;
		}
		if(y>1){
		if(!plateau[x][y-1].listeelements.isEmpty())bloque=true;
		}
		if(y<plateau[0].length-2){
		if(!plateau[x][y+1].listeelements.isEmpty())bloque=true;
		}
		
		return !bloque;
	}
	/**
	 * Génère de l'eau tout autour de l'ile.
	 */
	public void placerEau(){
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				if(l==0 || l==plateau.length-1 || c==0 || c==plateau[0].length-1){
					if(plateau[l][c].listeelements.isEmpty()) plateau[l][c].listeelements.add(new Element(6));
				}
			}
		}
	}
	
	/**
	 * place aléatoirement sur la carte le coffre sous un rocher 
	 */
	public void placerCoffre(){  
		Random r=new Random();
		int x;
		int y;
		do{
		x=1+r.nextInt(plateau.length-2);  // S'adapte au dimension du plateau
		y=2+r.nextInt(plateau[0].length-3); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer le coffre sur une case deja existente.
		plateau[x][y].ajouterCoffre();
	}
	/**
	 * place aléatoirement sur la carte la clé sous un rocher 
	 */
	public void placerClé(){ 
		Random r=new Random();
		int x;
		int y;
		do{
		x=1+r.nextInt(plateau.length-2);  // S'adapte au dimension du plateau
		y=2+r.nextInt(plateau[0].length-3); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer la clé sur une case deja existente.
		plateau[x][y].ajouterClé();
	}
	
	private void estAccessible(int x,int y){
			plateau[x][y].accessible=true;
		if(x>0){
			if(plateau[x-1][y].listeelements.isEmpty() && !plateau[x-1][y].accessible ) estAccessible(x-1,y);
			else{plateau[x-1][y].accessible=true;}
		}
		
		if(x<plateau.length-1){
			
			if(plateau[x+1][y].listeelements.isEmpty() && !plateau[x+1][y].accessible) estAccessible(x+1,y);
			else{plateau[x+1][y].accessible=true;}
		}
		
		if(y>0){
			
			if(plateau[x][y-1].listeelements.isEmpty() && !plateau[x][y-1].accessible) estAccessible(x,y-1);
			else{plateau[x][y-1].accessible=true;}
		}
		
		if(y<plateau[0].length-1){
			if(plateau[x][y+1].listeelements.isEmpty() && !plateau[x][y+1].accessible) estAccessible(x,y+1);
			else{plateau[x][y+1].accessible=true;}
		}
	}
	/**
	 * Retourne vrai si la clé et le coffre sont accessible à partir des navires
	 * @return
	 */
	public boolean estAccessible(){
		int x1,x2,y1,y2,xcl,ycl,xco,yco;
		x1=0;x2=0;
		y1=0;y2=0;
		xcl=0;ycl=0;
		xco=0;yco=0;
		
		
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau.length;c++){
				if(!plateau[l][c].listeelements.isEmpty()){
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(0))){ //si la case contient le navire1 
						x1=l;y1=c;
					}
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(1))){ //si la case contient le navire2
						x2=l;y2=c;
					}
					if(plateau[l][c].listeelements.size()>1){
						if( plateau[l][c].listeelements.get(1).compareTo(new Element(4))){ //si la case contient la clé
							xcl=l;ycl=c;
						}
						if( plateau[l][c].listeelements.get(1).compareTo(new Element(3))){ //si la case contient le coffre
							xco=l;yco=c;
						}
					}
				}
			}
		}
		boolean accostage1=accostagepossible(x1, y1);
		boolean accostage2=accostagepossible(x2, y2);
		estAccessible(x1,y1);
		if(plateau[x2][y2].accessible && plateau[xco][yco].accessible && plateau[xcl][ycl].accessible && accostage1 && accostage2)return true;
		else return false;
		
	}
	/**
	 * Supprime tous les rochers de la carte (sauf celui de la clé et du coffre)et remet à faux le boolean d'accessibilité de la parcelle
	 */
	private void clearRocher(){ 
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				plateau[l][c].accessible=false;
				if(plateau[l][c].listeelements.size()==1 && plateau[l][c].listeelements.get(0).compareTo(new Element(2))){
					plateau[l][c].listeelements.clear();
				}
			}
		}
	}
	 
	
	private void placeRocher(double pourcentage){ //
		clearRocher();
		Random r = new Random();
		int r1, r2;
		for(int i = 0; i<(plateau.length)*(plateau[0].length)*pourcentage/100; i++){
			do{
				r1 =1+r.nextInt(plateau.length-2);
				r2 =1+r.nextInt(plateau[0].length-2);
			}while(!plateau[r1][r2].listeelements.isEmpty() );
			plateau[r1][r2].ajouterRocher();
			
		}
		
	}
	/**
	 * place des rochers sur x% de la map.
	 * @param pourcentage
	 */
	public boolean placerRocher(double pourcentage){
		int nbtours=0;
		int max=100; 
		do{
			placeRocher(pourcentage);
			nbtours+=1;
		}while(!estAccessible() && nbtours<max);
		if(nbtours==max)return false;
		else return true;
	}
	/**
	 * @param x
	 * @param y
	 * @param explorateur
	 * @param voleur
	 * @param joueur
	 * @return Retourne vrai si le personnage en x,y peut intéragir avec la parcelle aux coordonnées x2,y2
	 */
	public boolean deplacementPossible(int x,int y,boolean explorateur,boolean voleur,int joueur){
		if(!plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(4)))return true;
		if(!plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(5)))return true;
		if(!explorateur){
			if(voleur){
				if(plateau[x][y].perso!=null )return true;
					
				
			}
			
			if(plateau[x][y].listeelements.isEmpty()){
				if(plateau[x][y].perso==null)return true;
			}else{
				if(joueur==1 && plateau[x][y].listeelements.get(0).compareTo(new Element(0)))return true;
				if(joueur==2 && plateau[x][y].listeelements.get(0).compareTo(new Element(1))) return true;
				return false;
			}
		}else{
			if(plateau[x][y].listeelements.isEmpty() || plateau[x][y].listeelements.get(0).compareTo(new Element(2)) || plateau[x][y].listeelements.get(0).compareTo(new Element(3)) && plateau[x][y].perso==null)return true;
			if(joueur==1 && !plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(0)))return true;
			if(joueur==2 && !plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(1))) return true;
		}
		return false;
	}
	
	/**
	 * retourne l'ile sous forme d'un tableau de String.
	 * 
	 **/
	public String toString(){ 
		String ligne="";
		for(int nbcases=0;nbcases<plateau.length;nbcases++){
		ligne+="+---";
		}
		ligne+="+\n";
		String res="";
		for(int l=0;l<plateau.length;l++){
			res+=ligne;
			for(int c=0;c<plateau[0].length;c++){
				res+="| "+plateau[l][c].toString()+" ";
			}
			res+="|\n";
		}
		res+=ligne; //ligne
		return res;
	}
	
	/**
	 * retourne l'ile sous forme d'un tableau de chiffre pour la classe Superplateau.
	 */
	public int[][] getPlateau(){ 
		int[][]resultat=new int[this.plateau.length][this.plateau[0].length];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				if(plateau[l][c].listeelements.isEmpty()){
					if(plateau[l][c].perso!=null){
						if(plateau[l][c].perso.toString().equals("E"))resultat[c][l]=8; //explorateur equipe1
						if(plateau[l][c].perso.toString().equals("e"))resultat[c][l]=9; //explorateur equipe2
						if(plateau[l][c].perso.toString().equals("V"))resultat[c][l]=10; //voleur equipe1
						if(plateau[l][c].perso.toString().equals("v"))resultat[c][l]=11; //voleur equipe2
						if(plateau[l][c].perso.toString().equals("P"))resultat[c][l]=12;
						if(plateau[l][c].perso.toString().equals("p"))resultat[c][l]=13;
						if(plateau[l][c].perso.toString().equals("G"))resultat[c][l]=14;
						if(plateau[l][c].perso.toString().equals("g"))resultat[c][l]=15;
					}
					else resultat[c][l]=1;//sol
				}
				
				if(!plateau[l][c].listeelements.isEmpty() && plateau[c][l].listeelements!=null){
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(2)))resultat[c][l]=2;//rocher plateau[l][c].listeelements.contains(new Element(2))
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(0)))resultat[c][l]=3;//navire equipe1
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(1)))resultat[c][l]=4;//navire equipe2
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(5)))resultat[c][l]=12;//trésor
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(4)))resultat[c][l]=6;//clé
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(3)))resultat[c][l]=5;//coffre
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(6)))resultat[c][l]=7;//eau
					
					if(plateau[l][c].listeelements.size()>1){ //permet de voir ou est situé le coffre et la clé meme s'ils sont recouverts par un rocher (pour vérifier, à retirer dans la version finale)
						if(plateau[l][c].listeelements.get(1).compareTo(new Element(3)))resultat[c][l]=5;//coffre
						if(plateau[l][c].listeelements.get(1).compareTo(new Element(4)))resultat[c][l]=6;//clé
					}
				}
			}
		}
		return resultat;
	}
	/**
	 * @return Retourn vrai si l'un des joueurs a gagné ou perdu
	 */
	public boolean fini(){
		if(e1.tresor || e2.tresor)return true;
		if(e1.plusDePersonnage() || e2.plusDePersonnage())return true;
		else return false;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param joueur
	 *  Permet au joueur de récupérer les coordonnées du coffre
	 */
	public void recupererCoordonneesCoffre(int x,int y,int joueur){
		if(joueur==1){
			e1.positionCoffre[0]=x;
			e1.positionCoffre[1]=y;
		}else{
			e2.positionCoffre[0]=x;
			e2.positionCoffre[1]=y;
		}
	}
	private void personnageMeurt(int x, int y){
		if(plateau[x][y].perso.clé){
			if(plateau[x][y].perso.coffre){
				
				plateau[x][y].listeelements.add(new Element(5));//pose le trésor sur le sol
			}else{
				plateau[x][y].listeelements.add(new Element(4));//pose la clé sur le sol
			}
		}else{
			if(plateau[x][y].perso.coffre){
				plateau[x][y].listeelements.add(new Element(5));//pose le trésor sur le sol
			}
		}
		if(plateau[x][y].perso.getEquipe()==1)e1.setNbpersonnages(e1.getNbpersonnages() - 1);
		else e2.setNbpersonnages(e2.getNbpersonnages() - 1);
		plateau[x][y].perso=null;
		
		
	}
	
	private int choisirPersonnageDansNavire(int joueur){
		String[] choix;
		if(joueur==1){
			choix=new String[e1.equipageAuRepos.size()];
			for(int idx=0;idx<e1.equipageAuRepos.size();idx++){
				choix[idx]=e1.equipageAuRepos.get(idx).description();
			}
			
		}else{
			choix=new String[e2.equipageAuRepos.size()];
			choix=new String[e2.equipageAuRepos.size()];
			for(int idx=0;idx<e2.equipageAuRepos.size();idx++){
				choix[idx]=e2.equipageAuRepos.get(idx).description();
			}
		}
		int idxPerso=0;
		if(choix.length>0){
		   Object Perso = JOptionPane.showInputDialog(null, 
		      "Veuillez choisir quel personnage débarquer",
		      "Débarquer un personnage",
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      choix,
		      choix[choix.length-1]);
		   
		   if(joueur==1){
			   for(int idx=0;idx<e1.equipageAuRepos.size();idx++){
					if(e1.equipageAuRepos.get(idx).description().equals(Perso));idxPerso=idx;
				}
			   
		   }else{
			   for(int idx=0;idx<e2.equipageAuRepos.size();idx++){
					if(e2.equipageAuRepos.get(idx).description().equals(Perso));idxPerso=idx;
				}
		   }
		}
		return idxPerso;
	}

	/**
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param joueur
	 * Fait intéragir le personnage a la case x,y avec la case x2,y2
	 */
	public void deplacerPersonnage(int x, int y , int x2, int y2,int joueur){
		if(!plateau[x][y].listeelements.isEmpty()&& plateau[x][y].estNavireDe(joueur)){ //si le navire est sélectionné
			int idx=choisirPersonnageDansNavire(joueur);
			if(joueur==1 && !e1.equipageAuRepos.isEmpty()){plateau[x2][y2].perso=e1.equipageAuRepos.get(idx);e1.equipageAuRepos.remove(idx);}
			if(joueur==2 && !e2.equipageAuRepos.isEmpty()){plateau[x2][y2].perso=e2.equipageAuRepos.get(idx);e2.equipageAuRepos.remove(idx);}
		}
		else{//si un personnage est selectionné
		 if(plateau[x2][y2].estVide()){ //si l'endroit ciblé est vide
				plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
				plateau[x2][y2].perso=plateau[x][y].perso;
				plateau[x][y].perso=null;
				if(plateau[x2][y2].perso.getEnergie()<=0)personnageMeurt(x2, y2);
				
		 }else{
			 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(4)) || !plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(5)) ){
			 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(4))){ //si contient la clé
				 plateau[x][y].perso.clé=true;
				 plateau[x2][y2].listeelements.remove(0);
			 }
			 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(5))){ //si contient trésor
				 plateau[x][y].perso.coffre=true;
				 plateau[x2][y2].listeelements.remove(0);
			}
		 }
		 else 	if(plateau[x][y].perso instanceof Voleur && plateau[x2][y2].perso!=null){ //si le perso choisi est un voleur
			 			
			 			plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-10);
			 		if(plateau[x2][y2].perso.getEquipe()!=joueur){ //s'il cible un personnage ennemie
			 			if(plateau[x2][y2].perso.coffre){
			 				System.out.println("tentative de vol");
			 				Random Rand=new Random();
			 				int s=Rand.nextInt(10);
			 				if(s>=9){
			 					System.out.println("vol réussi");
			 					plateau[x2][y2].perso.coffre=false;
			 					plateau[x][y].perso.coffre=true;
			 					
			 				}
			 			}
			 			if(plateau[x2][y2].perso.clé){ // si le voleur possède la clé
			 				System.out.println("tentative de vol");
			 				Random Rand=new Random();
			 				int s=Rand.nextInt(10);
			 				if(s>=9){
			 					System.out.println("vol réussi");
			 					plateau[x2][y2].perso.clé=false;
			 					plateau[x][y].perso.clé=true;
			 					
			 				}
		 				
			 			}
			 		}else{ //si le joueur choisi est un allié
			 			if(plateau[x][y].perso.coffre){ // si le voleur possède le trésor
			 					System.out.println("donne le trésor");
			 					plateau[x2][y2].perso.coffre=true;
			 					plateau[x][y].perso.coffre=false;
			 				
			 			}
			 			if(plateau[x][y].perso.clé){ // si le voleur possède la clé
		 					System.out.println("donne la clé");
		 					plateau[x2][y2].perso.clé=true;
		 					plateau[x][y].perso.clé=false;
		 				
			 			}
			 		}
			 			if(plateau[x][y].perso.getEnergie()<=0)personnageMeurt(x, y);
			 	}
			 	else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(0)) || plateau[x2][y2].listeelements.get(0).compareTo(new Element(1))){ //si le navre est ciblé
					plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
					if(joueur==1){
						if(plateau[x][y].perso.coffre)e1.tresor=true; //s'il possède le coffre
							e1.equipageAuRepos.add(plateau[x][y].perso);
						}
					if(joueur==2){
						if(plateau[x][y].perso.coffre)e2.tresor=true;//s'il possède le coffre
							e2.equipageAuRepos.add(plateau[x][y].perso);
						}
					plateau[x][y].perso=null;	
				}
				else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(2))){//si contient un rocher
					plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-5);
					if(plateau[x2][y2].listeelements.size()>1){
						if(plateau[x2][y2].listeelements.get(1).compareTo(new Element(3))){//rocher couvre le coffre
							if(plateau[x][y].perso.clé){//le joueur a la clé
								plateau[x2][y2].listeelements.remove(1);
								plateau[x][y].perso.coffre=true;//le joueur récupère le trésor
								plateau[x][y].perso.clé=false;//il perd donc la clé
							}else{//le joueur n'a pas la clé
								recupererCoordonneesCoffre(x2, y2, joueur);
								plateau[x][y].listeelements.remove(0);
							}
					
					
						}
						else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(1).compareTo(new Element(4))){ //rocher couvre la clé
							plateau[x2][y2].listeelements.remove(1);
							plateau[x][y].perso.clé=true;
						}
						
					}else{ //si le rocher ne couvre rien
						
					}
					if(plateau[x][y].perso.getEnergie()<=0)personnageMeurt(x, y);
					
				}
			 	
			}
		 
		}
		
	}
	

}

