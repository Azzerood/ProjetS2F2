
public class Noeud {
	private int x;
	private int y;
	private Noeud precedent;
	private Noeud successor;
	private double fcost;
	private double heuristic; //Mathematiquement , devrait être initialise a +l'infini
	private double gcost=Double.MAX_VALUE; //gcost
	public Noeud(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Noeud getPrecedent() {
		return precedent;
	}
	public void setPrecedent(Noeud precedent) {
		gcost+=1;
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

	public double getFcost() {
		return fcost;
	}

	public void setFcost(double cost) {
		this.fcost = cost;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public double getGcost() {
		return gcost;
	}

	public void setGcost(double profondeur) {
		this.gcost = profondeur;
	}
	public boolean equals(Noeud n){
		if(this.getX()==n.getX() && this.getY()== n.getY())return true;
		else return false;
	}
	public String toString(){
		return getX()+" "+getY();
	}

	public Noeud getSuccessor() {
		return successor;
	}

	public void setSuccessor(Noeud successor) {
		this.successor = successor;
	}
	

}
