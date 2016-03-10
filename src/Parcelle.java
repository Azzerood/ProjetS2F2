import java.util.ArrayList;

public class Parcelle {
	ArrayList<Element> listeelements=new ArrayList<Element>();
	
	Parcelle(){
		
	}
	Parcelle(Element e){
		listeelements.add(e);
	}

	public String toString(){ // retourne le premier élèment de la liste d'élèment (pour ne pas révéler la présence du coffre ou de la clé (par convention deuxieme élèment de la liste).
		if(listeelements!=null && !listeelements.isEmpty()) return listeelements.get(0).toString(); //l'alternative permet d'éviter toute erreur causée par l'éventuel manque d'un element.
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
	
	public void ajouterClé(){
		ajouterRocher(); //ajouter d'abord un rocher pour ne pas toString() la clé
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

