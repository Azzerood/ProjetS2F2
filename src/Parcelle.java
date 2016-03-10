import java.util.ArrayList;

public class Parcelle {
	ArrayList<Element> listeelements=new ArrayList<Element>();
	
	Parcelle(){
		
	}
	Parcelle(Element e){
		listeelements.add(e);
	}

	public String toString(){ // retourne le premier �l�ment de la liste d'�l�ment (pour ne pas r�v�ler la pr�sence du coffre ou de la cl� (par convention deuxieme �l�ment de la liste).
		if(listeelements!=null && !listeelements.isEmpty()) return listeelements.get(0).toString(); //l'alternative permet d'�viter toute erreur caus�e par l'�ventuel manque d'un element.
		else return " ";
	}
	
	
	public void ajouterNaviree1(){
		listeelements.add(new Element(0));
	}
	public void ajouterNaviree2(){
		listeelements.add(new Element(1));
	}
	public void ajouterRocher(){
		listeelements.add(new Element(2));
	}
	
	public void ajouterCl�(){
		ajouterRocher(); //ajouter d'abord un rocher pour ne pas toString() la cl�
		listeelements.add(new Element(4));
	}
	
	public void ajouterCoffre(){
		ajouterRocher();//ajouter d'abord un rocher pour ne pas toString() le coffre
		listeelements.add(new Element(3));
	}

	
	public static void main(String[]args){
		Parcelle p=new Parcelle();
		p.listeelements.add(new Element(1));
		System.out.println(p.toString());
	}
}

