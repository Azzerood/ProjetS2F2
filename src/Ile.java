import java.util.Random;

public class Ile {
	Parcelle[][] plateau; //plateau
	
	Ile(int d){
		plateau=new Parcelle[d][d];
		for(int l=0;l<plateau.length;l++){
			for(int c=0;c<plateau[0].length;c++){
				plateau[l][c]=new Parcelle();
			}
		}
	}
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
		plateau[0][plateau[0].length/2].ajouterNaviree1();
		plateau[plateau.length-1][plateau[0].length/2].ajouterNaviree2();
	}
	/**
	 * place aléatoirement sur la carte le coffre sous un rocher (dissimulé par un rocher)
	 */
	public void placerCoffre(){  
		Random r=new Random();
		int x;
		int y;
		do{
		x=r.nextInt(plateau.length);  // S'adapte au dimension du plateau
		y=r.nextInt(plateau[0].length); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer le coffre sur une case deja existente.
		plateau[x][y].ajouterCoffre();
	}
	/**
	 * place aléatoirement sur la carte le coffre sous un rocher (dissimulé par un rocher)
	 */
	public void placerClé(){ 
		Random r=new Random();
		int x;
		int y;
		do{
		x=r.nextInt(plateau.length);  // S'adapte au dimension du plateau
		y=r.nextInt(plateau[0].length); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer la clé sur une case deja existente.
		plateau[x][y].ajouterClé();
	}
	/**
	 * Permet de savoir si un rocher bloque une parcelle de l'ile
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Bloque(int x,int y){ 
		if(x+1<plateau.length && x>0 && y+1<plateau[0].length && y>0  ){ //Vérifie le carré intérieur
			if(!plateau[x-1][y-1].listeelements.isEmpty()) return true;
			if(!plateau[x][y-1].listeelements.isEmpty()) return true;
			if(!plateau[x+1][y-1].listeelements.isEmpty()) return true;
			if(!plateau[x-1][y].listeelements.isEmpty()) return true;
			if(!plateau[x+1][y].listeelements.isEmpty()) return true;
			if(!plateau[x-1][y+1].listeelements.isEmpty()) return true;
			if(!plateau[x][y+1].listeelements.isEmpty()) return true;
			if(!plateau[x+1][y+1].listeelements.isEmpty()) return true;
		}
		
		return false;
	}
	/**
	 * place des rochers sur x% de la map.
	 * @param pourcentage
	 */
	public void placerRocher(double pourcentage){ //
		Random r = new Random();
		int r1, r2;
		for(int i = 0; i<(plateau.length)*(plateau[0].length)*pourcentage; i++){
			r1 = 1+r.nextInt(plateau.length-2);
			r2 = 1+r.nextInt(plateau[0].length-2);
			while(!plateau[r1][r2].listeelements.isEmpty() || Bloque(r1,r2) ) {
				r1 =1+ r.nextInt(plateau.length-2);
				r2 =1+ r.nextInt(plateau[0].length-2);
			} 
			plateau[r1][r2].ajouterRocher();
			
		}
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
				if(plateau[l][c].listeelements.isEmpty())resultat[l][c]=1;//sol
				if(!plateau[l][c].listeelements.isEmpty() && plateau[l][c].listeelements!=null){
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(2)))resultat[l][c]=2;//rocher plateau[l][c].listeelements.contains(new Element(2))
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(0)))resultat[l][c]=3;//navire equipe1
				if(plateau[l][c].listeelements.get(0).compareTo(new Element(1)))resultat[l][c]=4;//navire equipe2
				//else if(plateau[l][c].listeelements.get(0).equals(new Element(3)))resultat[l][c]=1;//cofre
				//else if(plateau[l][c].listeelements.get(0).equals(new Element(4)))resultat[l][c]=1;//clé
				}
			}
		}
		return resultat;
	}
	
	public static void main(String[] args){
		Ile i=new Ile();
		i.placerLesNavires();
		i.placerClé();
		i.placerCoffre();
		i.placerRocher(0.1);
		System.out.println(i.toString());
		System.out.println("-------------------------------------------------");
		for(int lignes=0;lignes<i.plateau.length;lignes++){
			for(int colonnes=0;colonnes<i.plateau[0].length;colonnes++){
				System.out.print(i.plateau[lignes][colonnes].toString()+" ");
			}
			System.out.println();
		}
		
	}

}

