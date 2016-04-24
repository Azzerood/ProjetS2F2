import java.util.Random;

public class Personnage {
		private int energie=100;
		private int equipe;
		String symbole;
		boolean clé=false;
		boolean coffre=false;
		String nom;
		private static int nbPersonnages;
		private int numperso;
		private int piegé;
		private int[] casePrecedente;
		
	
	public Personnage(boolean equipe1){
	if(equipe1){
		this.equipe = 1;
		}else{
		this.equipe=2;
		}
	nbPersonnages++;
	numperso=nbPersonnages;
	String[] noms={"Jean","Pierre","Paul","Bob","Kevin","Julien","Thierry","Sylvain","Caroline","Sylvie","Marie","Lucie","Billy","Jimmy","Thomas","Sarah","Anna","Céline","Fannie","Jacques"};
	Random rand=new Random();
	int x =rand.nextInt(20);
	nom=noms[x];
	casePrecedente=new int[]{0,0};
	}
	
	/**
	 * @return retourne l'énergie actuelle du personnage
	 */
	public int getEnergie(){
		return energie;
	}
	/**
	 * Fixe l'energie du personnage à e.
	 * @param e
	 */
	public void setEnergie(int e){
		energie=e;
	}
	/**
	 * @return le numéro de l'équipe à laquelle le personnage appartient
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
	 * @return Retourne une  brève description du personnage
	 */
	public String description(){
		return numperso+") "+nom+" - "+getClass();
	}

	/**
	 * @return retourne la valeur de piegé
	 */
	public int getPiegé() {
		return piegé;
	}

	/**
	 * @param nbtours
	 * Fixe la  valeur de (piegé) du personnage à nbtours
	 */
	public void setPiegé(int nbtours) {
		this.piegé = nbtours;
	}
	
	/**
	 * @return Retourne une chaîne de caractère qui contient l'inventaire et l'énergie du personnage
	 */
	public String afficherInventaire(){
		String resultat;
		resultat="Inventaire de "+nom+"\n"+"Energie: "+energie+"\n";
		if(piegé>0)resultat+="Piégé pendant "+piegé+" tours \n";
		if(clé)resultat+="Possède la clé \n";
		if(coffre)resultat+="Possède le trésor \n";
		return resultat;
	}

	/**
	 * @return Retourne les coordonnées de la derniere case ou se situait le personnage
	 */
	public int[] getCasePrecedente() {
		return casePrecedente;
	}

	/**
	 * @param casePrecedente
	 * Fixe les coordonnées de la case précedente du personnage à newCasePrecedente
	 */
	public void setCasePrecedente(int[] newCasePrecedente) {
		this.casePrecedente = newCasePrecedente;
	}
	
}
