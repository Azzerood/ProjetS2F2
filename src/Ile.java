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
	public void placerLesNavires(){
		plateau[0][0].ajouterNaviree1();
		plateau[plateau.length-1][plateau[0].length-1].ajouterNaviree2();
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
		if(x==0){ //vérifie le bord gauche
			if(!plateau[x+1][y].listeelements.isEmpty()) return true;
			if(y+1<plateau[0].length){
				if(!plateau[x][y+1].listeelements.isEmpty()) return true;
			}
			if(y>0){
			if(!plateau[x][y-1].listeelements.isEmpty()) return true;
			
			if(!plateau[x+1][y-1].listeelements.isEmpty()) return true;
			}
		}
		if(x==plateau.length-1){ //Vérifie le bord droit
			if(!plateau[x-1][y].listeelements.isEmpty()) return true;
			if(y>0){
				if(!plateau[x-1][y-1].listeelements.isEmpty()) return true;
				if(!plateau[x][y-1].listeelements.isEmpty()) return true;
			}
			if(y+1<plateau[0].length){
			if(!plateau[x-1][y+1].listeelements.isEmpty()) return true;
			if(!plateau[x][y+1].listeelements.isEmpty()) return true;
			}
		}
		
		if(y==0){ //vérifie le bord supérieur
			if(!plateau[x][y+1].listeelements.isEmpty()) return true;
			if(x>0){
				if(!plateau[x-1][y].listeelements.isEmpty()) return true;
				if(!plateau[x-1][y+1].listeelements.isEmpty()) return true;
			}
			if(x+1<plateau.length){
				if(!plateau[x+1][y].listeelements.isEmpty()) return true;
				if(!plateau[x+1][y+1].listeelements.isEmpty()) return true;
			}
			
		}
		if(y==plateau[0].length-1){ //Vérifie le bord inférieur
			
		}
		
		if(x+1<plateau.length && x>0 && y+1<plateau[0].length && y>0  ){ //Vérifie le bord intérieur
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
	public void placerRocher(){
		Random r = new Random();
		int r1, r2;
		for(int i = 0; i<(plateau.length)*(plateau[0].length)*0.1; i++){
			r1 = r.nextInt(plateau.length);
			r2 = r.nextInt(plateau[0].length);
				
			while(!plateau[r1][r2].listeelements.isEmpty() || Bloque(r1,r2)) {
				r1 = r.nextInt(plateau.length);
				r2 = r.nextInt(plateau[0].length);
			} 
			plateau[r1][r2].ajouterRocher();
			
		}
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
		
		
		
	}

}

