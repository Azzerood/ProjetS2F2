import java.util.ArrayList;

public class Parcelle {
	ArrayList<Element> listeelements=new ArrayList<Element>();
	boolean accessible;
	Parcelle(){
		accessible=false;
	}
	Parcelle(Element e){
		listeelements.add(e);
		accessible=false;
	}

	/**
	 * retourne le premier �l�ment de la liste d'�l�ment (pour ne pas r�v�ler la pr�sence du coffre ou de la cl� (par convention deuxieme �l�ment de la liste).
	 **/
	public String toString(){ 
		if(listeelements!=null && !listeelements.isEmpty()) return listeelements.get(0).toString(); //l'alternative permet d'�viter toute erreur caus�e par l'�ventuel manque d'un element.
		else return " ";
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
	public void estAccessible(){
		
	}
	
	public static void main(String[]args){
		Parcelle p=new Parcelle();
		p.listeelements.add(new Element(1));
		System.out.println(p.toString());
	}
}

