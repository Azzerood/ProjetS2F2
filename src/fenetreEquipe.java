import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class fenetreEquipe extends JFrame {
		JFrame window;
		JPanel centre;
		JPanel nord;
		int pointsDispo=5;
		int nbExplo;
		int nbGuerrier;
		int nbPiegeur;
		int nbVoleur;
		
	public fenetreEquipe(){
		window=new JFrame();
		centre=new JPanel();
		nord=new JPanel();
		JPanel explorateur=new JPanel();
		JPanel boutonsExplorateur=new JPanel();
		JLabel imageExplo=new JLabel(new ImageIcon("img/explo.png"));
		JButton plusExplo=new JButton("+");
		ajouterListenner(plusExplo, 1, true);
		JButton moinsExplo=new JButton("-");
		ajouterListenner(moinsExplo, 1, false);
		JLabel cptExplorateur=new JLabel(""+nbExplo);
		boutonsExplorateur.setLayout(new FlowLayout(FlowLayout.CENTER));
		boutonsExplorateur.add(moinsExplo);
		boutonsExplorateur.add(cptExplorateur);
		boutonsExplorateur.add(plusExplo);
		explorateur.add(boutonsExplorateur,BorderLayout.SOUTH);
		explorateur.add(imageExplo, BorderLayout.CENTER);
		centre.add(explorateur);
		centre.setPreferredSize(new Dimension(450,330));
		nord.setPreferredSize(new Dimension(450,70));
		window.getContentPane().add(centre, BorderLayout.CENTER);
		window.getContentPane().add(nord, BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Selection de personnages");
		this.setPreferredSize(new Dimension(450, 400));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
	}
	public void ajouterListenner(JButton bouton,int personnage,boolean incremente){
		bouton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(personnage==1){
					if(incremente){
						pointsDispo-=1;
						nbExplo+=1;
					}else{
						pointsDispo+=1;
						nbExplo-=1;
					}
				}else{
					if(personnage==2){
						if(incremente){
							pointsDispo-=1;
							nbGuerrier+=1;
						}else{
							pointsDispo+=1;
							nbGuerrier-=1;
						}
					}else{
						if(personnage==3){
							if(incremente){
								pointsDispo-=1;
								nbPiegeur+=1;
							}else{
								pointsDispo+=1;
								nbPiegeur-=1;
							}
						}else{
							if(incremente){
								pointsDispo-=1;
								nbVoleur+=1;
							}else{
								pointsDispo+=1;
								nbVoleur-=1;
							}
						}
					}
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public static void main (String[] args) throws InterruptedException {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          fenetreEquipe run=new fenetreEquipe();
	          
	          
	        }
		
		});
	}
	
}

