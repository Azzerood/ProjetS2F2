
public class Personnage {
int energie=100;
int equipe;
String symbole;
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
