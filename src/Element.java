public class Element {
	int ID;
	
	Element(int n){
		ID=n;
	}
	
	/**
	 * retourne vrai si l'element est identique � celui pass� en parametre.
	 * @param e
	 * @return
	 */
	public boolean compareTo(Element e){ 
		if(e.ID==this.ID)return true;
		else return false;
	}
	
	public String toString(){ //retourne l'Element sous la forme d'une lettre.
		if(ID==0)return "N";//navire equipe1
		if(ID==1)return "n";//navire equipe2
		if(ID==2)return "R";//Rocher
		if(ID==3)return "C";//Coffre
		if(ID==4)return "c";//cl�
		if(ID==5)return "T";//tr�sor
		return " ";
	}
	public static void main(String[] args){
		
	}

}