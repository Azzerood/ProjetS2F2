

	public class Personnage {
		int energie=10;
		int equipe;
		String symbole;
		boolean clé=false;
		boolean coffre=false;
		
	public Personnage(boolean equipe1){
	if(equipe1){
		this.equipe = 1;
		}else{
		this.equipe=2;
		}
	}
	public String toString(){
		if (equipe==1){
			return symbole.toUpperCase();
		}else return symbole.toLowerCase();
	
	}

	
}
