import java.util.Random;

public class Personnage {
		private int energie=100;
		private int equipe;
		String symbole;
		boolean cl�=false;
		boolean coffre=false;
		String nom;
		private static int nbPersonnages;
		private int numperso;
		private int pieg�;
		private int[] casePrecedente;
		
	
	public Personnage(boolean equipe1){
	if(equipe1){
		this.equipe = 1;
		}else{
		this.equipe=2;
		}
	nbPersonnages++;
	numperso=nbPersonnages;
	String[] noms={"Jean","Pierre","Paul","Bob","Kevin","Julien","Thierry","Sylvain","Caroline","Sylvie","Marie","Lucie","Billy","Jimmy","Thomas","Sarah","Anna","C�line","Fannie","Jacques"};
	Random rand=new Random();
	int x =rand.nextInt(20);
	nom=noms[x];
	casePrecedente=new int[]{0,0};
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

	/**
	 * @return retourne la valeur de pieg�
	 */
	public int getPieg�() {
		return pieg�;
	}

	/**
	 * @param nbtours
	 * Fixe la  valeur de (pieg�) du personnage � nbtours
	 */
	public void setPieg�(int nbtours) {
		this.pieg� = nbtours;
	}
	
	/**
	 * @return Retourne une cha�ne de caract�re qui contient l'inventaire et l'�nergie du personnage
	 */
	public String afficherInventaire(){
		String resultat;
		resultat="Inventaire de "+nom+"\n"+"Energie: "+energie+"\n";
		if(pieg�>0)resultat+="Pi�g� pendant "+pieg�+" tours \n";
		if(cl�)resultat+="Poss�de la cl� \n";
		if(coffre)resultat+="Poss�de le tr�sor \n";
		return resultat;
	}

	/**
	 * @return Retourne les coordonn�es de la derniere case ou se situait le personnage
	 */
	public int[] getCasePrecedente() {
		return casePrecedente;
	}

	/**
	 * @param casePrecedente
	 * Fixe les coordonn�es de la case pr�cedente du personnage � newCasePrecedente
	 */
	public void setCasePrecedente(int[] newCasePrecedente) {
		this.casePrecedente = newCasePrecedente;
	}
	
}
