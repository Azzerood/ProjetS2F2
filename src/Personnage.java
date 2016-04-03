

	public class Personnage {
		private int energie=100;
		private int equipe;
		String symbole;
		boolean cl�=false;
		boolean coffre=false;
		String nom;
		private static int nbPersonnages;
		private int numperso;
		
	
	public Personnage(boolean equipe1){
	if(equipe1){
		this.equipe = 1;
		}else{
		this.equipe=2;
		}
	nbPersonnages++;
	numperso=nbPersonnages;
	}
	
	/**
	 * @return retourne l'�nergie actuelle du personnage
	 */
	public int getEnergie(){
		return energie;
	}
	/**
	 * Fixe l'energie du personnage � e.
	 * @param e
	 */
	public void setEnergie(int e){
		energie=e;
	}
	/**
	 * @return le num�ro de l'�quipe � laquelle le personnage appartient
	 */
	public int getEquipe(){
		return equipe;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if (equipe==1){
			return symbole.toUpperCase();
		}else return symbole.toLowerCase();
	
	}
	/**
	 * @return Retourne une  br�ve description du personnage
	 */
	public String description(){
		return numperso+") "+nom+" - "+getClass();
	}

	
}
