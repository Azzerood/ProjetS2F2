import java.util.Random;

public class Ile {
	Parcelle[][] plateau; //plateau
	
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
	 * place les navires des deux équipes à des positions fixées
	 */
	public void placerLesNavires(){ 
		plateau[plateau.length/2][0].ajouterNaviree1();
		plateau[plateau.length/2][plateau[0].length-1].ajouterNaviree2();
		
	}
	/**
	 * Génère de l'eau  autour de l'ile.
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
	
	/**
	 * Marque comme "accessible" toutes les cases accessibles depuis la case de coordonnées(x,y)
	 * @param x
	 * @param y
	 */
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
		estAccessible(x1,y1);
		if(plateau[x2][y2].accessible && plateau[xco][yco].accessible && plateau[xcl][ycl].accessible)return true;
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
	
	/**
	 * place des rochers sur pourcentage% de la map.
	 * @param pourcentage
	 */
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
	 * Place des rochers sur pourcentage% de la map et réitère l'opération si le coffre et la clé ne sont pas accessible (100généraions max)
	 * Retourne vrai si une génération est correcte en moins de 100 essais
	 * @param pourcentage
	 * @return
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
				if(plateau[l][c].listeelements.isEmpty())resultat[c][l]=1;//sol
				if(!plateau[l][c].listeelements.isEmpty() && plateau[c][l].listeelements!=null){
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(2)))resultat[c][l]=2;//rocher plateau[l][c].listeelements.contains(new Element(2))
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(0)))resultat[c][l]=3;//navire equipe1
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(1)))resultat[c][l]=4;//navire equipe2
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(6)))resultat[c][l]=7;//eau
				//if(plateau[l][c].listeelements.size()>1){
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(3)))resultat[c][l]=5;//coffre
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(4)))resultat[c][l]=6;//clé
				//}
				
				}
			}
		}
		return resultat;
	}
	
	public static void main(String[] args){
		
		
	}

}

