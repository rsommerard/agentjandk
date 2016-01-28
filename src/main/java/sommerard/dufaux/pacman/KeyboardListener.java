package sommerard.dufaux.pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import sommerard.dufaux.core.Agent;

public class KeyboardListener extends KeyAdapter{

	private Prey mPrey;
	
	
	public KeyboardListener(){
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(mPrey != null)
			mPrey.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(mPrey != null)
			mPrey.keyReleased(e);
	}

	public void setPrey(Prey prey) {
		this.mPrey = prey;
	}
}
