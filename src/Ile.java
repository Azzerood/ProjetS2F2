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
	
	public void placerCoffre(){  //place aléatoirement sur la carte le coffre sous un rocher (dissimulé par un rocher)
		Random r=new Random();
		int x;
		int y;
		do{
		x=r.nextInt(plateau.length);  // S'adapte au dimension du plateau
		y=r.nextInt(plateau[0].length); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer le coffre sur une case deja existente.
		plateau[x][y].ajouterCoffre();
	}
	public void placerClé(){ //place aléatoirement sur la carte le coffre sous un rocher (dissimulé par un rocher)
		Random r=new Random();
		int x;
		int y;
		do{
		x=r.nextInt(plateau.length);  // S'adapte au dimension du plateau
		y=r.nextInt(plateau[0].length); //S'adapte au dimension du plateau
		}while(!plateau[x][y].listeelements.isEmpty()); // Empeche de placer la clé sur une case deja existente.
		plateau[x][y].ajouterClé();
	}
	public boolean Bloque(int x,int y){ //(En cours) Permet de savoir si un rocher bloque une parcelle de l'ile
		return false;
	}
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
	
	
	public static void main(String[] args){
		Ile i=new Ile();
		System.out.print(i.toString());
		i.plateau[0][0].ajouterNaviree1();
		i.plateau[i.plateau.length-1][i.plateau[0].length-1].ajouterNaviree2();
		i.placerCoffre();
		i.placerCoffre();
		System.out.println("*******************************************");
		System.out.print(i.toString());
		
		
	}

}

