import java.awt.GraphicsEnvironment; 

import javax.swing.JFrame;

public class Main {
	
	
	public static void main(String[] args) {
		Partie p=new Partie();//
		p.initialiserPartie();
		JFrame j=new JFrame();
		System.out.println(j.getGraphicsConfiguration().getBounds());
	}

}
