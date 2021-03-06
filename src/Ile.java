import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Ile {
	Parcelle[][] plateau; //plateau
	Equipe e1=new Equipe(1);
	Equipe e2=new Equipe(2);
	SuperPlateau s;
	/**
	 * @param d
	 */
	Ile(int d){
		plateau=new Parcelle[d][d];
		e1.casesvisites=new boolean[d][d];
		e2.casesvisites=new boolean[d][d];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				plateau[l][c]=new Parcelle();
				e1.casesvisites[l][c]=false;
				e2.casesvisites[l][c]=false;
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
	public  void addPlateau(SuperPlateau plateau){
		s=plateau;
	}
	
	/**
	 *  Remplie la liste de personnage des deux �quipes
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
	 *  Place sur l'�le les personnage de l'�quipe 1 et 2
	 */
	public void placerLesEquipages(){//� r�aliser par Yann et Arthur
		placerEquipage1();
		placerEquipage2();
	}
	/**
	 * place les navires des deux �quipes � des positions fix�es
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
	 * G�n�re de l'eau tout autour de l'ile.
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
	 * place al�atoirement sur la carte le coffre sous un rocher 
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
	 * place al�atoirement sur la carte la cl� sous un rocher 
	 */
	public void placerCl�(){ 
		Random r=new Random();
		int x;
		int y;
		do{
		x=1+r.nextInt(plateau.length-2);  // S'adapte au dimension du plateau
		y=2+r.nextInt(plateau[0].length-3); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer la cl� sur une case deja existente.
		plateau[x][y].ajouterCl�();
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
	 * Retourne vrai si la cl� et le coffre sont accessible � partir des navires
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
						if( plateau[l][c].listeelements.get(1).compareTo(new Element(4))){ //si la case contient la cl�
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
	 * Supprime tous les rochers de la carte (sauf celui de la cl� et du coffre)et remet � faux le boolean d'accessibilit� de la parcelle
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
	 * @return Retourne vrai si le personnage en x,y peut int�ragir avec la parcelle aux coordonn�es x2,y2
	 */
	public boolean deplacementPossible(int x,int y,boolean explorateur,boolean voleur,boolean guerrier,int joueur){
		if(!plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(4)))return true;
		if(!plateau[x][y].listeelements.isEmpty() && plateau[x][y].listeelements.get(0).compareTo(new Element(5)))return true;
		if(!explorateur){
			if(voleur){
				if(plateau[x][y].perso!=null )return true;
				
			}
			if(guerrier){
				if(plateau[x][y].perso!=null && plateau[x][y].perso.getEquipe()!=joueur )return true;
			}
			if(plateau[x][y].listeelements.isEmpty()){
				if(plateau[x][y].perso==null)return true;
			}else{
				if (contientLeNavire1EtLeJoueurEst1(x, y, joueur))return true;
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
	private boolean contientLeNavire1EtLeJoueurEst1(int x, int y, int joueur) {
		if(joueur==1 && plateau[x][y].listeelements.get(0).compareTo(new Element(0)))return true;
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
	public int[][] getPlateau(boolean godMod){ 
		int[][]resultat=new int[this.plateau.length][this.plateau[0].length];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				if(plateau[l][c].listeelements.isEmpty()){
					if(plateau[l][c].perso!=null){
						if(plateau[l][c].perso.toString().equals("E"))resultat[c][l]=8; //explorateur equipe1
						if(plateau[l][c].perso.toString().equals("e"))resultat[c][l]=9; //explorateur equipe2
						if(plateau[l][c].perso.toString().equals("V"))resultat[c][l]=10; //voleur equipe1
						if(plateau[l][c].perso.toString().equals("v"))resultat[c][l]=11; //voleur equipe2
						if(plateau[l][c].perso.toString().equals("P"))resultat[c][l]=12; //piegeur equipe 1
						if(plateau[l][c].perso.toString().equals("p"))resultat[c][l]=13;//piegeur equipe 2
						if(plateau[l][c].perso.toString().equals("G"))resultat[c][l]=14; //guerrier equipe1
						if(plateau[l][c].perso.toString().equals("g"))resultat[c][l]=15; //guerrier equipe2
					}
					else resultat[c][l]=1;//sol
				}
				
				if(!plateau[l][c].listeelements.isEmpty() && plateau[c][l].listeelements!=null){
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(2)))resultat[c][l]=2;//rocher plateau[l][c].listeelements.contains(new Element(2))
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(0)))resultat[c][l]=3;//navire equipe1
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(1)))resultat[c][l]=4;//navire equipe2
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(5)))resultat[c][l]=16;//tr�sor
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(4)))resultat[c][l]=6;//cl�
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(3)))resultat[c][l]=5;//coffre
					if(plateau[l][c].listeelements.get(0).compareTo(new Element(6)))resultat[c][l]=7;//eau
					
					if(godMod){
						if(plateau[l][c].listeelements.size()>1){ //permet de voir ou est situ� le coffre et la cl� meme s'ils sont recouverts par un rocher (pour v�rifier, � retirer dans la version finale)
							if(plateau[l][c].listeelements.get(1).compareTo(new Element(3)))resultat[c][l]=5;//coffre
							if(plateau[l][c].listeelements.get(1).compareTo(new Element(4)))resultat[c][l]=6;//cl�
						}
					}
				}
				if( plateau[l][c].piegee1)resultat[c][l]=17;
				if(plateau[l][c].piegee2)resultat[c][l]=18;
				if((plateau[l][c].piegee1 || plateau[l][c].piegee2) && plateau[l][c].perso!=null){ //si un personnage est pris au piege dans le piege
					resultat[c][l]=19;
				}
			}
		}
		return resultat;
	}
	
	/**
	 * @param joueur
	 * @return retourne l'ile sous forme d'un tableau de nombres et n'affiche que les �l�ments visibles par le joueur "joueur"
	 */
	public int[][] getPlateau(int joueur){
		int[][]resultat=new int[this.plateau.length][this.plateau[0].length];
		int[][]ile=getPlateau(false);
		for(int l=0;l<plateau.length;l++){  
			for(int c=0;c<plateau[0].length;c++){
				resultat[l][c]=20;  //on initialise toutes les cases du tableau pour qu'elles affichent du sable
			}
		}
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				
				if(plateau[l][c].perso!=null ){ // s'il y a un personnage du joueur sur une case
					if(plateau[l][c].perso.getEquipe()==joueur){
						resultat[c-1][l-1]=ile[c-1][l-1];   //alors on affiche toutes les case autour de lui
						resultat[c-1][l]=ile[c-1][l];
						resultat[c-1][l+1]=ile[c-1][l+1];
						resultat[c][l-1]=ile[c][l-1];
						resultat[c][l]=ile[c][l];
						resultat[c][l+1]=ile[c][l+1];
						resultat[c+1][l-1]=ile[c+1][l-1];
						resultat[c+1][l]=ile[c+1][l];
						resultat[c+1][l+1]=ile[c+1][l+1];
					}
				}else{
					if(!plateau[l][c].listeelements.isEmpty() ){ //si la case n'est pas vide
						if( plateau[l][c].listeelements.get(0).compareTo(new Element(0)) ||  plateau[l][c].listeelements.get(0).compareTo(new Element(1)) || plateau[l][c].listeelements.get(0).compareTo(new Element(6)) ){// et qu'elle contient un navire ou de l'eau
							resultat[c][l]=ile[c][l]; //Alors on l'affiche
						}
						
					}
				}
			}
		}
		for(int l=0;l<plateau.length;l++){  
			for(int c=0;c<plateau[0].length;c++){
				if(joueur==1){//si c'est le joueur1 et que la case comporte un piege du joueur1, on l'affiche
					if( plateau[l][c].piegee1)resultat[c][l]=17;
				}
				if(joueur==2 ){//si c'est le joueur2 et que la case comporte un piege du joueur2, on l'affiche
					if(plateau[l][c].piegee2)resultat[c][l]=18;
				}
				if((plateau[l][c].piegee1 || plateau[l][c].piegee2) && plateau[l][c].perso!=null){ //si un personnage est pris au piege dans le piege
					resultat[c][l]=19;
				}
			}
		}
		
		
		if(joueur==1){
			if(e1.positionCoffre[0]!=0 && e1.positionCoffre[1]!=0){
				resultat[e1.positionCoffre[1]][e1.positionCoffre[0]]=5;
			}
		}else{
			if(e2.positionCoffre[0]!=0 && e2.positionCoffre[1]!=0){
				resultat[e2.positionCoffre[1]][e2.positionCoffre[0]]=5;
			}
		}
		return resultat;
	}
	
	
	
	/**
	 * @return Retourn vrai si l'un des joueurs a gagn� ou perdu
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
	 *  Permet au joueur de r�cup�rer les coordonn�es du coffre
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
	
	/**
	 * @param x
	 * @param y
	 * @return Retourne une chaine de caract�re qui d�crit l'inventaire du personnage en (x,y)
	 */
	public String afficherInventaire(int x,int y){
		return plateau[x][y].perso.afficherInventaire();
	}
	
	private void personnageMeurt(int x, int y){
		if(plateau[x][y].perso.cl�){
			if(plateau[x][y].perso.coffre){
				
				plateau[x][y].listeelements.add(new Element(5));//pose le tr�sor sur le sol
			}else{
				plateau[x][y].listeelements.add(new Element(4));//pose la cl� sur le sol
			}
		}else{
			if(plateau[x][y].perso.coffre){
				plateau[x][y].listeelements.add(new Element(5));//pose le tr�sor sur le sol
			}
		}
		if(plateau[x][y].perso.getEquipe()==1)e1.setNbpersonnages(e1.getNbpersonnages() - 1);
		else e2.setNbpersonnages(e2.getNbpersonnages() - 1);
		plateau[x][y].perso=null;
		
		
	}
	
	/**
	 *  Retire 1 au nombre de tours restants pi�g� � tous les personnages actuellement dans un piege
	 */
	public void recuperationPiege(){
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				if(plateau[l][c].perso!=null){
					if(plateau[l][c].perso.getPieg�()>0){
					 plateau[l][c].perso.setPieg�(plateau[l][c].perso.getPieg�()-1);
					}
				}
			}
		}
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
		      "Veuillez choisir quel personnage d�barquer",
		      "D�barquer un personnage",
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      choix,
		      choix[choix.length-1]);
		   
		   if(joueur==1){
			   for(int idx=0;idx<e1.equipageAuRepos.size();idx++){
					if(e1.equipageAuRepos.get(idx).description().equals(Perso)){
						idxPerso=idx;
						}
				}
			   
		   }else{
			   for(int idx=0;idx<e2.equipageAuRepos.size();idx++){
					if(e2.equipageAuRepos.get(idx).description().equals(Perso)){
						idxPerso=idx;
					}
				}
		   }
		}
		
		return idxPerso;
	}

	/**
	 * @return Retourne vrai si l'utilisateur soutaire se d�placer et faux s'il veut poser un piege
	 */
	public boolean afficherMenuPiegeur(){
		boolean resultat= false;
		int rang;
		String[]choix={"Se d�placer","Poser un pi�ge"};
		JOptionPane jop;
		rang = JOptionPane.showOptionDialog(null, 
			      "Que faire?",
			      "Pi�geur",
			      JOptionPane.YES_NO_CANCEL_OPTION,
			      JOptionPane.QUESTION_MESSAGE,
			      null,
			      choix,
			      choix[1]);
		if(rang!=0)resultat=true;
		return resultat;
		
		
		
	}
	/**
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param joueur
	 * Fait int�ragir le personnage a la case x,y avec la case x2,y2
	 */
	public void deplacerPersonnage(int x, int y , int x2, int y2,int joueur){
		if(!plateau[x][y].listeelements.isEmpty()&& plateau[x][y].estNavireDe(joueur)){ //si le navire est s�lectionn�
			int idx=choisirPersonnageDansNavire(joueur);
			if(joueur==1 && !e1.equipageAuRepos.isEmpty()){plateau[x2][y2].perso=e1.equipageAuRepos.get(idx);e1.equipageAuRepos.remove(idx);}
			if(joueur==2 && !e2.equipageAuRepos.isEmpty()){plateau[x2][y2].perso=e2.equipageAuRepos.get(idx);e2.equipageAuRepos.remove(idx);}
		}
		else{//si un personnage est selectionn�
			if(plateau[x][y].perso.getPieg�()<=0){
			
		 if(plateau[x2][y2].estVide()){ //si l'endroit cibl� est vide
			 if(plateau[x2][y2].piegee1 || plateau[x2][y2].piegee2){//piege un personnage s'il marche sur un piege et ne peut plus rien faire
				 plateau[x][y].perso.setPieg�(5);
				 s.println(plateau[x][y].perso.nom+" a march� sur un piege et est bloqu� pendant 5tours.");
				 plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
				 plateau[x2][y2].perso=plateau[x][y].perso;
				 plateau[x][y].perso=null;
				 
			 }
			 else{
				 if(plateau[x][y].perso instanceof Piegeur){
					 boolean poserPiege=afficherMenuPiegeur();
					 if(poserPiege){
						 s.println(plateau[x][y].perso.nom+" a pos� un pi�ge et perd 10 points d'�nergie");
						 plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-10);
						 if(joueur==1) {plateau[x2][y2].setPiege1(true);}
						 else {plateau[x2][y2].setPiege2(true);}
					
						 if(plateau[x][y].perso.getEnergie()<=0){
							 s.println(plateau[x][y].perso.nom+" n'a plus d'energie et est mort");
							 personnageMeurt(x, y);
							
							 
						 }
						 
					 }else{
						plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
						plateau[x2][y2].perso=plateau[x][y].perso;
						plateau[x][y].perso=null;
						if(plateau[x2][y2].perso.getEnergie()<=0){
							s.println(plateau[x2][y2].perso.nom+" n'a plus d'energie et est mort");
							personnageMeurt(x2, y2);
							
						}
						
					 }
				 
				 
				 }else{
					 plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
					 plateau[x2][y2].perso=plateau[x][y].perso;
					 plateau[x][y].perso=null;
					 if(plateau[x2][y2].perso.getEnergie()<=0){
						 s.println(plateau[x2][y2].perso.nom+" n'a plus d'energie et est mort");
						 personnageMeurt(x2, y2);
						
					 }
				 }
				
			 }	
		 }else{
			 
			 
			 if(plateau[x][y].perso instanceof Guerrier && plateau[x2][y2].perso!=null){
				 s.println(plateau[x][y].perso.nom+" attaque un personnage adversaire est perd 10 points d'�nergie");
				 plateau[x2][y2].perso.setEnergie(plateau[x2][y2].perso.getEnergie()-50);//le personnage cibl� perd de l'�nergie (attaqu� par le guerrier)
				 s.println(plateau[x2][y2].perso.nom+" s'est fait attaqu� et a perdu 50 points d'energie");
				 plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-10); //coute 10 d'energie
				 if(plateau[x][y].perso.getEnergie()<=0){
					 s.println(plateau[x][y].perso.nom+" n'a plus d'energie et est mort");
					 personnageMeurt(x, y);
					 
				 }
				 if(plateau[x2][y2].perso.getEnergie()<=0){
					 s.println(plateau[x2][y2].perso.nom+" n'a plus d'energie et est mort");
					 personnageMeurt(x2, y2);
					
				 }
			 }else{
			
				 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(4)) || !plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(5)) ){
					 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(4))){ //si contient la cl�
						 plateau[x][y].perso.cl�=true;
						 plateau[x2][y2].listeelements.remove(0);
					 }
					 if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(5))){ //si contient tr�sor
						 plateau[x][y].perso.coffre=true;
						 plateau[x2][y2].listeelements.remove(0);
					 }
				 }
			 
			  
		 else 	if(plateau[x][y].perso instanceof Voleur && plateau[x2][y2].perso!=null){ //si le perso choisi est un voleur
			 			
			 			plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-10);
			 		if(plateau[x2][y2].perso.getEquipe()!=joueur){ //s'il cible un personnage ennemie
			 			if(plateau[x2][y2].perso.coffre){
			 				s.println(plateau[x][y]+"tente de voler "+plateau[x2][y2].perso.nom+" et perd 10 points d'energie");
			 				Random Rand=new Random();
			 				int rand=Rand.nextInt(10);
			 				if(rand>=9){
			 					s.println("vol r�ussi");
			 					plateau[x2][y2].perso.coffre=false;
			 					plateau[x][y].perso.coffre=true;
			 					
			 				}
			 			}
			 			if(plateau[x2][y2].perso.cl�){ // si le personnage poss�de la cl�
			 				s.println(plateau[x][y]+"tente de voler "+plateau[x2][y2].perso.nom+" et perd 10 points d'energie");
			 				Random Rand=new Random();
			 				int rand=Rand.nextInt(10);
			 				if(rand>=9){
			 					s.println("vol r�ussi");
			 					plateau[x2][y2].perso.cl�=false;
			 					plateau[x][y].perso.cl�=true;
			 					
			 				}
		 				
			 			}
			 		}else{ //si le joueur choisi est un alli�
			 			
			 			
			 			if(plateau[x][y].perso.coffre){ // si le personnage poss�de le tr�sor
			 					s.println(plateau[x][y]+"donne le tr�sor � "+plateau[x2][y2].perso.nom+" et perd 10 points d'energie");
			 					plateau[x2][y2].perso.coffre=true;
			 					plateau[x][y].perso.coffre=false;
			 				
			 			}
			 			if(plateau[x][y].perso.cl�){ // si le personnage poss�de la cl�
		 					System.out.println(plateau[x][y]+"donne la cl� � "+plateau[x2][y2].perso.nom+" et perd 10 points d'energie");
		 					plateau[x2][y2].perso.cl�=true;
		 					plateau[x][y].perso.cl�=false;
		 				
			 			}
			 		}
			 			if(plateau[x][y].perso.getEnergie()<=0){
			 				s.println(plateau[x][y].perso.nom+" n'a plus d'energie et est mort");
			 				personnageMeurt(x, y);
			 				
			 			}
			 	}
			 	else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(0)) || plateau[x2][y2].listeelements.get(0).compareTo(new Element(1))){ //si le navre est cibl�
					plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-1);
					if(joueur==1){
						if(plateau[x][y].perso.coffre)e1.tresor=true; //s'il poss�de le coffre
							e1.equipageAuRepos.add(plateau[x][y].perso);
						}
					if(joueur==2){
						if(plateau[x][y].perso.coffre)e2.tresor=true;//s'il poss�de le coffre
							e2.equipageAuRepos.add(plateau[x][y].perso);
						}
					plateau[x][y].perso=null;	
				}
				else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(0).compareTo(new Element(2))){//si contient un rocher
					s.println(plateau[x][y].perso.nom+" soul�ve un rocher et perd 5 points d'energie");
					plateau[x][y].perso.setEnergie(plateau[x][y].perso.getEnergie()-5);
					if(plateau[x2][y2].listeelements.size()>1){
						if(plateau[x2][y2].listeelements.get(1).compareTo(new Element(3))){//rocher couvre le coffre
							if(plateau[x][y].perso.cl�){//le joueur a la cl�
								s.println(plateau[x][y].perso.nom+" trouve le coffre et le prend.");
								plateau[x2][y2].listeelements.remove(1);
								plateau[x][y].perso.coffre=true;//le joueur r�cup�re le tr�sor
								plateau[x][y].perso.cl�=false;//il perd donc la cl�
							}else{//le joueur n'a pas la cl�
								recupererCoordonneesCoffre(x2, y2, joueur);
								s.println(plateau[x][y].perso.nom+" trouve le coffre mais ne poss�de pas la cl� pour l'ouvrir.");
								
							}
					
					
						}
						else if(!plateau[x2][y2].listeelements.isEmpty() && plateau[x2][y2].listeelements.get(1).compareTo(new Element(4))){ //rocher couvre la cl�
							s.println("Il trouve la cl� et la prend");
							plateau[x2][y2].listeelements.remove(1);
							plateau[x][y].perso.cl�=true;
						}
						
					}else{ //si le rocher ne couvre rien
						
					}
					if(plateau[x][y].perso.getEnergie()<=0){
						s.println(plateau[x][y].perso.nom+" n'a plus d'energie et est mort");
						personnageMeurt(x, y);
						
					}
					
				}
			 }	
			}
			}
		}
		
	}
	
	
	private double getCost(int x0,int y0,int x1,int y1,int x2,int y2){ 
		double hCost=Math.abs(x1-x2)+Math.abs(y1-y2)*14; //distance entre la case et la destination souhait�e (heuristic)
		double gCost=Math.abs(x1-x0)+Math.abs(y1-y0)*10; // distance entre le point de d�part et la case
		return gCost+hCost;
		//return  Math.min(Math.abs(x0-x1),Math.abs(y0-y1)) * 10 + Math.abs(Math.abs(x1-x2) - Math.abs(y1-y2)) * 14;
	}
	private int[] getProchain(int x0,int y0,int x1,int y1,int x2,int y2,Noeud[][]graphe){
		ArrayList<Noeud> path=new ArrayList<Noeud>();
		Noeud current=graphe[x2][y2];
		while(!current.equals(graphe[x0][y0])){
			current.getPrecedent().setSuccessor(current);
			path.add(current);
			current=current.getPrecedent();
		}
		return new int[]{ path.get(path.size()-1).getX(),path.get(path.size()-1).getY()};
		
		
	}
	private boolean explorateurPeutMarcherdessus(Personnage explo,int x,int y,int joueur){
		if(plateau[x][y].estNavireDe(joueur))return true;
		if(explo.cl�){
			if(plateau[x][y].estAccessiblePourExplorateur(joueur))return true;
		}else{
			if(plateau[x][y].estVide() || plateau[x][y].contientCl�())return true;
		}
		return false;
	}
	
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param joueur
	 * @param casePrecedente
	 * @return retourne les coordonn�es du voisin de la case (x1,y1) qui propose le chemin le plus court vers la case (x2,y2)
	 */
	public int[] getMeilleurVoisin(int x1,int y1,int x2,int y2,int joueur,int[] casePrecedente) {
		Noeud[][] graphe=new Noeud[plateau.length][plateau[0].length];
		for(int x=0;x<graphe.length;x++){
			for(int y=0;y<graphe[0].length;y++){
				graphe[x][y]=new Noeud(x,y);
			}
		}

	
		ArrayList<Noeud> open=new ArrayList<Noeud>();
		ArrayList<Noeud> closed=new ArrayList<Noeud>();
		open.add(graphe[x1][y1]);
		
		do{	
			
			Noeud current = open.get(0);
			
			for(Noeud n : open){
				
				if(n.getFcost()<current.getFcost()){
					current=n;
				}
			}
			open.remove(current);
			closed.add(current);
			if(current.equals(graphe[x2][y2])){ // si la case actuelle est la case recherch�
				//return new int[]{closed.get(1).getX(),closed.get(1).getY()}; //on retourne la premiere case que l'on trouv� sur le chemin
				return getProchain(x1, y1, x2, y2, x2, y2, graphe);
			}
			for(int x=-1;x<2;x++){ //on parcours les cases de x-1 a x+1 autour du noued actuel
				for(int y=-1;y<2;y++){ //on parcours les cases de y-1 a y+1 autour du noued actuel
					
					if((x==0)&& (y==0)){ //n'est pas un voisin mais la case actuelle
						continue; //on passe un tour de boucle
					}
					if ((x != 0) && (y != 0)) { // ne pas aller voir en diagonal
						continue;
					}
					int xp=x+current.getX();
					int yp=y+current.getY();
					if(xp>0 && xp<graphe.length && yp>0 && yp<graphe[0].length ){ //limite aux bornes du plateau
						Noeud voisin=graphe[xp][yp]; //Le noeud voisin devient celui aux coordonn�es xp et yp
					
						if(!explorateurPeutMarcherdessus(plateau[x1][y1].perso, xp, yp, joueur) || closed.contains(voisin) ){ // ne pas �valuer les case qui ne sont pas accessible ou celles dont l'�valuation a �t� effectu� de mani�re d�finitive
							continue;
						}
						//else{ 
							if(voisin.getFcost()<current.getFcost() || !open.contains(voisin)){ //si le chemin est plus court ou que le voisin n'est pas dans la liste ouverte
							//voisin.setCost(current.getCost()+1);
							voisin.setFcost(current.getFcost()+getCost(current.getX(),current.getY(), xp, yp, x2, y2)); //on calcule le cout du voisin
							voisin.setPrecedent(current); //on fixe comme pr�decesseur du voisin la case actuelle
							if(!open.contains(voisin)){ //si le voisin n'est pas dans la liste ouverte
								open.add(voisin); //on le rajoute
							}
						//}
						}
					}
				}
			}
		//}while(!open.isEmpty());
		}while(!closed.contains(graphe[x2][y2]));
		return new int[]{closed.get(1).getX(),closed.get(1).getY()};
		//return getProchain(x1, y1, x1, y1, x2, y2, graphe);
		
	}
	/* public int[] getBetterVoisin(int x1,int y1,int x2,int y2,int joueur,int[] casePrecedente){
		int[] meilleurVoisin=new int[2];
		float cost1=10000;
		float cost2=10000;
		float cost3=10000;
		float cost4=10000;
		float costmin=10000;
		int xp=casePrecedente[0];
		int yp=casePrecedente[1];
		
		if( (plateau[x1-1][y1].estVide() || (x1-1==x2 && y1==y2) )  ){ //&& (xp!=x1-1 && yp!=y1)
			cost1=getCost(xp,yp,x1-1, y1, x2, y2);
			costmin=cost1;
			meilleurVoisin[0]=x1-1;
			meilleurVoisin[1]=y1;
			
		}
		if( (plateau[x1+1][y1].estVide() || (x1+1==x2 && y1==y2) )  ){ //&& (xp!=x1+1 && yp!=y1)
			cost2=getCost(xp,yp,x1+1, y1, x2, y2);
			if(costmin>cost2){
				costmin=cost2;
				meilleurVoisin[0]=x1+1;
				meilleurVoisin[1]=y1;
			}
		}
		if( (plateau[x1][y1-1].estVide() || (x1==x2 && y1-1==y2))  ){ //&& (xp!=x1 && yp!=y1-1)
			cost3=getCost(xp,yp,x1, y1-1, x2, y2);
			if(costmin>cost3){
				costmin=cost3;
				meilleurVoisin[0]=x1;
				meilleurVoisin[1]=y1-1;
			}
		}
		if( (plateau[x1][y1+1].estVide() || (x1==x2 && y1+1==y2) )  ){ //&& (xp!=x1 && yp!=y1+1)
			cost4=getCost(xp,yp,x1, y1+1, x2, y2);
			if(costmin>cost4){
				costmin=3;
				meilleurVoisin[0]=x1;
				meilleurVoisin[1]=y1+1;
			}
		}
		
		
		
		return meilleurVoisin;
	}*/
 
}

