public class Element {
	int ID;
	
	Element(int n){
		ID=n;
	}
	
	/**
	 * retourne vrai si l'element est identique � celui pass� en parametre .
	 * @param e
	 * @return
	 */
	public boolean compareTo(Element e){ 
		if(e.ID==this.ID)return true;
		else return false;
	}
	
	/** 
	 * Retourne l'Element sous la forme d'une lettre.
	 **/
	public String toString(){ 
		if(ID==0)return "N";//navire equipe1
		if(ID==1)return "n";//navire equipe2
		if(ID==2)return "R";//Rocher
		if(ID==3)return "C";//Coffre
		if(ID==4)return "c";//cl�
		if(ID==5)return "T";//tr�sor
		if(ID==6)return "E";//Eau
		return " ";
	}
/**
 * @param joueur
 * @return Retourne vrai si l'�l�ment est le navire du joueur
 */
public boolean estNavireDe(int joueur){
		if(ID==0 && joueur==1)return true;
		if(ID==1 && joueur==2)return true;
		return false;
	}
	public static void main(String[] args){
		
	}

}