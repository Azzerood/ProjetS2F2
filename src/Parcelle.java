import java.util.ArrayList;

public class Parcelle {
	ArrayList<Element> listeelements=new ArrayList<Element>();
	boolean accessible;
	Personnage perso;
	boolean piegee1;
	boolean piegee2;
	/**
	 * 
	 */
	Parcelle(){
		accessible=false;
	}
	/**
	 * @param e
	 */
	Parcelle(Element e){
		listeelements.add(e);
		accessible=false;
	}

	/**
	 * retourne le premier �l�ment de la liste d'�l�ment (pour ne pas r�v�ler la pr�sence du coffre ou de la cl� (par convention deuxieme �l�ment de la liste).
	 **/
	public String toString(){ 
		if(listeelements!=null && !listeelements.isEmpty()) return listeelements.get(0).toString(); //l'alternative permet d'�viter toute erreur caus�e par l'�ventuel manque d'un element.
		if(perso!=null) return perso.toString();
		else return " ";
	}
	public void setPiege1(boolean actif){
		piegee1=actif;
	}
	public void setPiege2(boolean actif){
		piegee2=actif;
	}
	
	
	/**
	 * ajoute le navire de l'�quipe 1 dans la liste d'�l�ment de la parcelle
	 **/
	public void ajouterNaviree1(){ 
		listeelements.add(new Element(0));
	}
	/**
	 * ajoute le navire de l'�quipe 2 dans la liste d'�l�ment de la parcelle
	 */
	public void ajouterNaviree2(){ 
		listeelements.add(new Element(1));
	}
	/**
	 * ajoute un rocher dans la liste d'�l�ment de la parcelle
	 **/
	public void ajouterRocher(){ 
		listeelements.add(new Element(2));
	}
	/**
	 * ajoute la cl� dans la liste d'�l�lement de la parcelle + un rocher en premier 
	 **/
	public void ajouterCl�(){ 
		ajouterRocher(); //ajouter d'abord un rocher pour ne pas toString() la cl�
		listeelements.add(new Element(4));
	}
	/**
	 *  ajoute le coffre dans la liste d'�l�lement de la parcelle + un rocher en premier
	 **/
	public void ajouterCoffre(){ 
		ajouterRocher();//ajouter d'abord un rocher pour ne pas toString() le coffre
		listeelements.add(new Element(3));
	}
	
	/**
	 * @return Retoune vrai si la parcelle est vide
	 */
	public boolean estVide(){
		return(listeelements.isEmpty() && perso== null);
	}
	
	/**
	 * @param joueur
	 * @return Retourne vrai si la parcelle contient le navire du joueur
	 */
	public boolean estNavireDe(int joueur){
		if(!listeelements.isEmpty()){
			if(listeelements.get(0).estNavireDe(joueur))return true;
			
		}
		return false;
	}
	
	
	public static void main(String[]args){
		
	}
}

