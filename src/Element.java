public class Element {
	int ID;
	
	Element(int n){
		ID=n;
	}
	
	public boolean compareTo(Element e){
		if(e.ID==this.ID)return true;
		else return false;
	}
	
	public String toString(){
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