package sommerard.dufaux.particule;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class View extends JPanel implements Observer{

	private int width;
	private int height;
	
	public View(int width, int height){
		this.width = width;
		this.height = height;
	}
    

	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		//this.paintComponent();
	}

}
