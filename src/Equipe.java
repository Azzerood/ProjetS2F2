import java.util.ArrayList;

public class Equipe {
	boolean tresor=false;
	int[] positionCoffre=new int[2];
	public int nbpersonnages;
	ArrayList<Personnage> equipage=new ArrayList<Personnage>();
	ArrayList<Personnage> equipageAuRepos=new ArrayList<Personnage>();//aka c'est le navire
	
	
	
	public boolean plusDePersonnage(){
	if(getNbpersonnages()<=0 && equipageAuRepos.isEmpty())return true;
	return false;
	}



	public int getNbpersonnages() {
		return nbpersonnages;
	}



	public void setNbpersonnages(int nbpersonnages) {
		this.nbpersonnages = nbpersonnages;
	}
	
	public void recuperationNavire(){
		for(Personnage p : equipageAuRepos){
			p.energie+=10;
		}
	}
	
}
