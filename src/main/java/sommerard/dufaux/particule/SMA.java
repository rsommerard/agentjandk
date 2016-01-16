package sommerard.dufaux.particule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observer;
import java.util.Random;
import java.util.Observable;

public class SMA extends Observable{
	
	private Environment environment;
	private List<Agent> agents;
	private Random generator;
	
	private boolean equity;
	
	public SMA(){
		//observers = new ArrayList<Observer>();
		agents = new ArrayList<Agent>();
	}
	

	public void init(int nbTurn, int nbBall, int envsize, int speed, boolean equity, String seed, boolean toric) throws InterruptedException {
		
		environment = new Environment();
		environment.init(envsize);
		
		this.equity = equity;
		
		if(seed != null){
			generator = new Random(Long.parseLong(seed));	
		}else{
			generator = new Random();
		}
		
		initAgents(envsize, nbBall);
		
		this.setChanged();
		this.notifyObservers(this);
	}

	
	public void run(int nbTurn, int speed) throws InterruptedException{
		
		for(int i = 0; i< nbTurn; i++){
			if(!equity){
				Collections.shuffle(agents, generator);
			}
			for(Agent k : agents){
				k.doIt();
			}
			
			this.setChanged();
			this.notifyObservers(this);
			
			Thread.sleep(speed);
		}
	}
	
	public void initAgents(int envsize, int nbBall){
		Agent j;
		int posX, posY;
		Direction direction;
		
		for(int i = 0; i < nbBall; i++){
			posX = generator.nextInt(envsize-1);
			posY = generator.nextInt(envsize-1);
			do{
				direction = new Direction(generator.nextInt(2)-1,generator.nextInt(2)-1);
			}while(direction.x == 0 && direction.y == 0);
			
			j = new Agent(posX,posY,direction, this.environment);
			agents.add(j);
			environment.space[posX][posY] = true;
		}
	}


	public Environment getEnvironment() {
		return environment;
	}

}
