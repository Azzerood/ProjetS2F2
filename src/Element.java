public class Element {
	int ID;
	
	/**
	 * @param n
	 */
	Element(int n){
		ID=n;
	}
	
	/**
	 * retourne vrai si l'element est identique à celui passé en parametre.
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
		if(ID==4)return "c";//clé
		if(ID==5)return "T";//trésor
		if(ID==6)return "E";//Eau
		return " ";
	}
	public static void main(String[] args){
		
	}

}