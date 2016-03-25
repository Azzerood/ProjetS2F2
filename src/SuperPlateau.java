 
public class SuperPlateau {
	Plateau test;
	Ile i;
	SuperPlateau(String[] gifs, int taille){
		this.test = new Plateau(gifs, taille) ;
	}
	SuperPlateau(String[] gifs, int taille,boolean textArea){
		this.test = new Plateau(gifs, taille,textArea) ;
	}
	public void setIle(Ile ile){
		this.i=ile;
	}
	public void setJeu() {
		this.test.setJeu(i.getPlateau());
	}
	
	public void affichage(){
		this.test.affichage();
		
	}
	public void println(String message){
		this.test.println(message);
	}
	public int[][] getJeu(){
		return this.test.getJeu();
	}
	//
	
}
	

