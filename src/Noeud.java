
public class Noeud {
	private int x;
	private int y;
	private Noeud precedent;
	private float cost;
	private float heuristic=10000; //Mathematiquement , devrait être initialise a +l'infini
	private float profondeur;
	public Noeud(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Noeud getPrecedent() {
		return precedent;
	}
	public void setPrecedent(Noeud precedent) {
		profondeur+=1;
		this.precedent = precedent;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(float heuristic) {
		this.heuristic = heuristic;
	}

	public float getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(float profondeur) {
		this.profondeur = profondeur;
	}
	public boolean equals(Noeud n){
		if(this.getX()==n.getX() && this.getY()== n.getY())return true;
		else return false;
	}
	public String toString(){
		return getX()+" "+getY();
	}
	

}
