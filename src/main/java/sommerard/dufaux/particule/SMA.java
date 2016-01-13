package sommerard.dufaux.particule;

import java.util.List;
import java.util.Observer;
import java.util.Observable;

public class SMA extends Observable{
	
	private List<Observer> observers;
	private Environment environment;
	private List<Agent> agents;
	
	public SMA(){
	}
	
	public void run(){
		
	}

	public Environment getEnvironment() {
		return environment;
	}


}
