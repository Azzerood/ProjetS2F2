

	public class Personnage {
		private int energie=100;
		private int equipe;
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
	
	public int getEnergie(){
		return energie;
	}
	public void setEnergie(int e){
		energie=e;
	}
	public int getEquipe(){
		return equipe;
	}
	public String toString(){
		if (equipe==1){
			return symbole.toUpperCase();
		}else return symbole.toLowerCase();
	
	}

	
}
