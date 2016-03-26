
public class Main {
	
	
	public static void main(String[] args) { //EN PHASE DE CONSTRUCTION 
		Partie p=new Partie(); //POUR LANCER UNE PARTIE :
		p.initialiserPartie();// UTILISER LES PARAMETRES SUIVANTS:
		p.s.i.plateau[6][1].perso=new Explorateur(true); // ajoute un personnage pour les test.
		p.s.i.plateau[6][8].perso=new Explorateur(false); // ajoute un personnage pour les test.
		p.tour(1);// ILE: 10x10 
		p.tour(2);// PROPORTION DE ROCHER: 10%
	}

}
