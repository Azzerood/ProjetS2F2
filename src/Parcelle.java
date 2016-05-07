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
	 * retourne le premier élèment de la liste d'élèment (pour ne pas révéler la présence du coffre ou de la clé (par convention deuxieme élèment de la liste).
	 **/
	public String toString(){ 
		if(listeelements!=null && !listeelements.isEmpty()) return listeelements.get(0).toString(); //l'alternative permet d'éviter toute erreur causée par l'éventuel manque d'un element.
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
	 * ajoute le navire de l'équipe 1 dans la liste d'élèment de la parcelle
	 **/
	public void ajouterNaviree1(){ 
		listeelements.add(new Element(0));
	}
	/**
	 * ajoute le navire de l'équipe 2 dans la liste d'élèment de la parcelle
	 */
	public void ajouterNaviree2(){ 
		listeelements.add(new Element(1));
	}
	/**
	 * ajoute un rocher dans la liste d'élèment de la parcelle
	 **/
	public void ajouterRocher(){ 
		listeelements.add(new Element(2));
	}
	/**
	 * ajoute la clé dans la liste d'élèlement de la parcelle + un rocher en premier 
	 **/
	public void ajouterClé(){ 
		ajouterRocher(); //ajouter d'abord un rocher pour ne pas toString() la clé
		listeelements.add(new Element(4));
	}
	/**
	 *  ajoute le coffre dans la liste d'élèlement de la parcelle + un rocher en premier
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
	/**
	 * @param joueur
	 * @return Retourne vrai si la parcelle contient un personnage du joueur de numéro (joueur)
	 */
	public boolean contientAllié(int joueur){
		if(perso!=null && perso.getEquipe()==joueur)return true;
		return false;
	}
	
	
	/**
	 * @return Retourne vrai si la parcelle contient la clé
	 */
	public boolean contientClé(){
		if(!listeelements.isEmpty()){
			if(listeelements.size()>1){
				if(listeelements.get(1).compareTo(new Element(4))){
					return true;
				}
			}else{
				if(listeelements.get(0).compareTo(new Element(4))){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * @return Retourne vrai si la case contient le coffre ou le trésor
	 */
	public boolean contientTrésor(){
		if(!listeelements.isEmpty()){
			if(listeelements.size()>1){
				if(listeelements.get(1).compareTo(new Element(3))){
					return true;
				}
			}else{
				if(listeelements.get(0).compareTo(new Element(3)) || listeelements.get(0).compareTo(new Element(5))){
					return true;
				}
			}
		}
		return false;
	}
	public boolean estAccessiblePourExplorateur(){
		if(contientClé() || contientTrésor() || estVide())return true;
		return false;
	}
	
	
	public static void main(String[]args){
		
	}
}

