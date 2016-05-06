import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Fenetre extends JFrame {
	int explo=0;
	int guerrier=0;
	int voleur=0;
	int piegeur=0;
	JPanel zone1=new JPanel();
	JPanel zone2=new JPanel();
	JPanel zoneNord=new JPanel();
	public Fenetre(){
		this.setLayout(new BorderLayout());
		this.add(zone1,BorderLayout.WEST);
		this.add(zone2,BorderLayout.CENTER);
		this.add(zoneNord,BorderLayout.NORTH);
		zone1.setLayout(new GridLayout(2,2));
		zone2.setLayout(new GridLayout(2,3));
		zone1.setPreferredSize(new Dimension(300,600));
		zone2.setPreferredSize(new Dimension(450,600));
		zoneNord.setPreferredSize(new Dimension(750, 100));
		zone1.setBackground(Color.BLACK);
		zone2.setBackground(Color.BLUE);
		JLabel explorateur=createExplorateur(true);
		JLabel guerrier=createGuerrier(true);
		JLabel voleur=createVoleur(true);
		JLabel piegeur=createPiegeur(true);
		JLabel barre=new JLabel(new ImageIcon("img/barreMenu.png"));
		//JLabel vide=new JLabel(new ImageIcon("img/champvide.png"));
		//explorateur.setPreferredSize(new Dimension(300,300));
		//guerrier.setPreferredSize(new Dimension(300,300));
		//voleur.setPreferredSize(new Dimension(300,300));
		//piegeur.setPreferredSize(new Dimension(300,300));
		zone1.add(explorateur);
		zone1.add(guerrier);
		zone1.add(voleur);
		zone1.add(piegeur);
		for(int x=0;x<6;x++){
			JLabel vide=new JLabel(new ImageIcon("img/champvide.png"));
			zone2.add(vide);
			
		}
		zoneNord.add(barre);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Selection de personnages");
		this.setPreferredSize(new Dimension(750, 700));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
	}
	public JLabel createExplorateur(boolean gauche){
		JLabel jlabel=new JLabel(new ImageIcon("img/explo.png"));
		jlabel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  if(gauche){
	        	  explo+=1;
	        	  }else{
	        		  explo-=1;
	        	  }
	          }
	      });
		return jlabel;
	}
	public JLabel createGuerrier(boolean gauche){
		JLabel jlabel=new JLabel(new ImageIcon("img/guerrier.png"));
		jlabel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  if(gauche){
	        	  guerrier+=1;
	        	  }else{
	        		  guerrier-=1;
	        	  }
	          }
	      });
		return jlabel;
	}
	public JLabel createVoleur(boolean gauche){
		JLabel jlabel=new JLabel(new ImageIcon("img/voleur.png"));
		jlabel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  if(gauche){
	        	  voleur+=1;
	        	  }else{
	        		  voleur-=1;
	        	  }
	          }
	      });
		return jlabel;
	}
	public JLabel createPiegeur(boolean gauche){
		JLabel jlabel=new JLabel(new ImageIcon("img/piegeur.png"));
		jlabel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  if(gauche){
	        	  piegeur+=1;
	        	  }else{
	        		  piegeur-=1;
	        	  }
	          }
	      });
		return jlabel;
	}
	public boolean leCompteEstBon(){
		if(piegeur+guerrier+voleur+explo<6)return false;
		return true;
	}
	
	public void poserImages(){
		zone2.removeAll();
		for(int explo=0;explo<this.explo;explo++){
			JLabel explorateur=createExplorateur(false);
			zone2.add(explorateur);
			
		}
		for(int guerr=0;guerr<guerrier;guerr++){
			JLabel guerrier=createGuerrier(false);
			zone2.add(guerrier);
		}
		for(int vol=0;vol<voleur;vol++){
			JLabel voleur=createVoleur(false);
			zone2.add(voleur);
		}
		for(int pieg=0;pieg<this.piegeur;pieg++){
			JLabel piegeur=createPiegeur(false);
			zone2.add(piegeur);
		}
		
		int difference=6-(explo+guerrier+voleur+piegeur);
		for(int cpt=0;cpt<difference;cpt++){
			JLabel vide=new JLabel(new ImageIcon("img/champvide.png"));
			zone2.add(vide);
		}
		
	}
	
	public static void main (String[] args) throws InterruptedException {
		Fenetre window=new Fenetre();
		while(!window.leCompteEstBon()){
			window.poserImages();
			window.pack();
			
				Thread.sleep(500);
			
		}
		window.poserImages();
		window.pack();

	}
	
}
