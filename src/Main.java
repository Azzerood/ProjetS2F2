import javax.swing.JOptionPane;

public class Main {
	
	public static void Menu(){
		 String[] choix = {"Lancer une partie", "Quitter"};
		    JOptionPane jop = new JOptionPane();
		    int rang = jop.showOptionDialog(null, 
		      "Que souhaitez vous faire?",
		      "Menu Principal",
		      JOptionPane.YES_NO_CANCEL_OPTION,
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      choix,
		      choix[1]);
		    if(rang==0){	
		    Partie p=new Partie(); 
			p.initialiserPartie();
			p.s.i.plateau[6][1].perso=new Explorateur(true); // ajoute un personnage pour les test.
			p.s.i.plateau[6][8].perso=new Explorateur(false); // ajoute un personnage pour les test.
			p.lancerPartie();
		    Menu();	
		    }else{
		    
		    }
	}
	
	public static void main(String[] args) { //EN PHASE DE CONSTRUCTION 
		Menu();
	}

}
