import java.util.ArrayList;

public class Equipe {
	int numero;
	boolean tresor=false;
	int[] positionCoffre=new int[2];
	public int nbpersonnages;
	ArrayList<Personnage> equipageAuRepos=new ArrayList<Personnage>();// c'est le navire
	
	public Equipe(int num){
		numero=num;
	}
	/**
	 *  
	 *  Remplie la liste de personnage 
	 */
	public void composerEquipe(){ //à réaliser par Yann et Arthur
		if(numero==1){
			equipageAuRepos.add(new Explorateur(true));
			equipageAuRepos.add(new Voleur(true));
			equipageAuRepos.add(new Guerrier(true));
			equipageAuRepos.add(new Piegeur(true));
			
		}else{
			equipageAuRepos.add(new Explorateur(false));
			equipageAuRepos.add(new Voleur(false));
			equipageAuRepos.add(new Guerrier(false));
			equipageAuRepos.add(new Piegeur(false));
		}
		setNbpersonnages(4);
	}
	/**
	 * @param idx
	 * Retire le personnage à l'indice idx de la liste des personnage au repos 
	 */
	public void poserPersonnage(int idx){
		equipageAuRepos.remove(idx);
		
	}
	
	
	/**
	 * @return Retourne vrai s'il n'y as plus de personnage en vie dans l'équipe
	 */
	public boolean plusDePersonnage(){
	if(getNbpersonnages()<=0 && equipageAuRepos.isEmpty())return true;
	return false;
	}



	/**
	 * @return Retourne le nombre de personnages dans l'équipe
	 */
	public int getNbpersonnages() {
		return nbpersonnages;
	}



	/**
	 * @param nbpersonnages
	 * Fixe le nombre de personnages dans l'équipe à nbpersonnages
	 */
	public void setNbpersonnages(int nbpersonnages) {
		this.nbpersonnages = nbpersonnages;
	}
	
	/**
	 *  Rend 10 d'énergie aux personnages aux repos
	 */
	public void recuperationNavire(){
		for(Personnage p : equipageAuRepos){
			p.setEnergie(p.getEnergie()+10);
		}
	}
	
	
}
