package sommerard.dufaux.particule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observer;
import java.util.Random;
import java.util.Observable;

public class SMA extends Observable{
	
	private List<Observer> observers;
	private Environment environment;
	private List<Agent> agents;
	private Random generator;
	
	public SMA(){
	}
	

	public void run(int nbTurn, int nbBall, int envsize, int speed, boolean equity, String seed, boolean toric) throws InterruptedException {
		
		generator = new Random(Long.parseLong(seed));
		//View view = new View();
		
		initAgents(envsize, nbBall);
		run(nbTurn, speed);
		
	}
	
	public void run(int nbTurn, int speed) throws InterruptedException{
		
		Collections.shuffle(agents, generator);
		for(Agent k : agents){
			k.doIt();
		}
		
		this.setChanged(); //update view
		Thread.sleep(speed);
	}
	
	public void initAgents(int envsize, int nbBall){
		Agent j;
		int posX, posY;
		Direction direction;
		
		for(int i = 0; i < nbBall; i++){
			posX = generator.nextInt(envsize-1);
			posY = generator.nextInt(envsize-1);
			direction = new Direction(1,1);
			j = new Agent(posX,posY,direction, this.environment);
			agents.add(j);
		}
	}


	public Environment getEnvironment() {
		return environment;
	}

}
