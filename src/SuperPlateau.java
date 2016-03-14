
public class SuperPlateau {
	Plateau test;
	Ile i;
	SuperPlateau(String[] gifs, int taille){
		this.test = new Plateau(gifs, taille) ;
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
	
	public int[][] getJeu(){
		return this.test.getJeu();
	}
}
