import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;


import javax.swing.JOptionPane;

public class Partie {
	SuperPlateau s;
	
	private int définirTailleIle(){
		boolean isnombre=false;
		String t;
		int taille=10;
		do{
		t= JOptionPane.showInputDialog("Définissez la taille de l'ile [min:10]");
		if(t.matches("[0-9]+")){
			taille=Integer.parseInt(t);
			if(taille>=10)
			isnombre=true;
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
		t= JOptionPane.showInputDialog("Définissez le pourcentage de rochers [0-"+max+"]");
		if(t.matches("[0-9]+")){
			pourcentage=Integer.parseInt(t);
			if(pourcentage<=max)isnombre=true;
			
		}
		}while(!isnombre);
		return pourcentage ;
	}
	private void erreurRocher(){
		JOptionPane Taille=new JOptionPane();
		JOptionPane.showMessageDialog(Taille, "Nous n'avons pas pu configurer l'ile. Recommencez", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private boolean selectionnable(int x, int y ,int joueur){
		if(s.i.plateau[x][y].perso!=null  && s.i.plateau[x][y].perso.getEquipe()==joueur )return true;
		if(s.i.plateau[x][y].estNavireDe(joueur))return true;
		return false;
	}
	/**
	 * 
	 */
	private boolean sontAdjacent(int x, int y, int x2, int y2,boolean voleur){
		if(x==x2){
			if(y2==y-1)return true;
			if(y2==y+1)return true;
		}
		if(y==y2){
			if(x2==x-1)return true;
			if(x2==x+1)return true;
		}
		if(voleur){
			if(y2==y-1 && x2==x-1) return true;
			if(y2==y-1 && x2==x+1) return true;
			if(y2==y+1 && x2==x-1) return true;
			if(y2==y+1 && x2==x+1) return true;
			
		}
		return false;
	}
	/**
	 *   Créer une île selon les paramètre de l'utilisateur et y place les divers élèments nécessaires pour jouer
	 */
	public void initialiserPartie(){
		boolean Rochers=false;
		Ile i;
		do{ 
		String[] images={"img/psol.png","img/procher.png","img/pnavire1.png","img/pnavire2.png","img/pcoffre.png","img/pclé.png","img/peau.png","img/pexplo1.png","img/pexplo2.png","img/pvoleur1.png","img/pvoleur2.png"};
		int taille=définirTailleIle();
		int pourcentage=definirProportionRocher();
		i=new Ile(taille);
		s=new SuperPlateau(images, taille,true);
		s.setIle(i); 
		i.placerLesNavires();
		i.placerEau();
		i.composerLesEquipe();
		i.placerLesEquipages();
		i.placerCoffre();
		i.placerClé();
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
	 * @return retourne les coordonnées du personnage choisi par l'utilisateur
	 */
	public int[] choisirPersonnage(){
		int[] coordonnées=new int[2];
		s.println("Choissisez un personnage");
		InputEvent event=s.waitEvent();
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonnées[0]=x;
		coordonnées[1]=y;
		return coordonnées;
		
	}
	/**
	 * @return retourne les coordonnées de la case choisi par l'utilisateur
	 */
	public int[] choisirCase(){
		int[] coordonnées=new int[2];
		s.println("Choissisez où aller");
		InputEvent event=s.waitEvent();
		int x=s.getX((MouseEvent) event);
		int y=s.getY((MouseEvent) event);
		coordonnées[0]=x;
		coordonnées[1]=y;
		return coordonnées;
	}
	/**
	 * Le joueur effectue toutes les actions possibles au cours d'un tour.
	 * @param joueur
	 */
	public void tour(int joueur){
		s.refresh();
		boolean explorateur=false;
		boolean voleur=false;
		int[] persoChoisi;
		int[] caseChoisi;
		do{
			s.println("Joueur "+joueur+" :");
			persoChoisi=choisirPersonnage();
		}while(!selectionnable(persoChoisi[0], persoChoisi[1], joueur));
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Explorateur)explorateur=true;
		if(s.i.plateau[persoChoisi[0]][persoChoisi[1]].perso instanceof Voleur)voleur=true;
		do{
			s.println("Joueur "+joueur+" :");
			caseChoisi=choisirCase();
		}while(!s.i.deplacementPossible(caseChoisi[0],caseChoisi[1],explorateur,voleur,joueur) || !sontAdjacent(persoChoisi[0], persoChoisi[1], caseChoisi[0], caseChoisi[1],voleur));
		s.i.deplacerPersonnage(persoChoisi[0],persoChoisi[1],caseChoisi[0],caseChoisi[1],joueur);
	
	}
	/**
	 * Permet aux personnages dans les navires de récupérer de l'énergie
	 */
	public void recuperationNavire(){
		s.i.e1.recuperationNavire();
		s.i.e2.recuperationNavire();
	}
	
	/**
	 *  Chaque joueur joue son tour tant qu'aucun des deux n'a gagné
	 */
	public void lancerPartie(){
		do{
			recuperationNavire();
			tour(1);
			tour(2);
		}while(!s.i.fini());
		s.close();
		afficherVainqueur();
	}
	/**
	 *  Affiche le vainqueur de la partie
	 */
	public void afficherVainqueur(){
		boolean gagnant=false;
		if(s.i.e1.tresor || s.i.e2.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 1 a gagné!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}else
		if(s.i.e2.tresor || s.i.e1.plusDePersonnage()){
			JOptionPane.showMessageDialog (null, "Joueur 2 a gagné!", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			gagnant=true;
		}
		if(!gagnant)JOptionPane.showMessageDialog (null, "Match nul", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
	}
}

