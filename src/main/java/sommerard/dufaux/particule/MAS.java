package sommerard.dufaux.particule;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Observable;

public class MAS extends Observable {
	
	private Environment mEnvironment;
	private List<Agent> mAgents;
	private Random mRandom;

	private boolean equity;
	
	public MAS(View view){
        addObserver(view);

        mAgents = new ArrayList<Agent>();
	}

	public void init(int nbTurn, int nbBall, int width, int height, int speed, boolean equity, String seed, boolean toric) {

		mEnvironment = new Environment(width, height, toric);
		
		this.equity = equity;
		
		if(seed != null){
            mRandom = new Random(Long.parseLong(seed));
		}else{
            mRandom = new Random();
		}
		
		initAgents(width, height, nbBall);
		
		setChanged();
		notifyObservers(this);
	}
	
	public void run(int nbTurn, int speed) throws InterruptedException {
		
		for (int i = 0; i < nbTurn; i++) {
            System.out.println("Turn: " + i);

            if (!equity) {
				Collections.shuffle(mAgents, mRandom);
			}
			for (Agent agent : mAgents) {
                agent.doIt();
			}
			
			setChanged();
			notifyObservers(this);
			
			Thread.sleep(speed);
		}
	}
	
	public void initAgents(int width, int height, int nbBall){
		/*for(int i = 0; i < nbBall; i++){
			int posX = mRandom.nextInt(width - 1);
			int posY = mRandom.nextInt(height - 1);
			int stepX = mRandom.nextInt(2) - 1;
			int stepY = mRandom.nextInt(2) - 1;

			while(stepX == 0 && stepY == 0) {
				stepX = mRandom.nextInt(2) - 1;
				stepY = mRandom.nextInt(2) - 1;
			}

            Agent agent = new Agent(posX, posY, stepX, stepY, Color.getColor(null, mRandom.nextInt()), mEnvironment);
			mAgents.add(agent);
			mEnvironment.setAgent(posX, posY, agent);
		}*/

        // Corner bug test ---------
        //Agent agent = new Agent(0, 0, 1, 1, mEnvironment);
        //mAgents.add(agent);
        //mEnvironment.setAgent(0, 0, agent);

        //agent = new Agent(2, 0, 1, 1, mEnvironment);
        //mAgents.add(agent);
        //mEnvironment.setAgent(2, 0, agent);
		// ---------------

       /*Agent agent = new Agent( 0, 1, 1, 1, Color.BLUE, mEnvironment);
        mAgents.add(agent);
        mEnvironment.setAgent(0, 1, agent);*/

        Agent agent = new Agent( 1, 2, -1, 0, Color.RED, mEnvironment);
        mAgents.add(agent);
        mEnvironment.setAgent(1, 2, agent);

        //agent = new Agent(2, 4, 1, 1, mEnvironment);
        //mAgents.add(agent);
        //mEnvironment.setAgent(2, 4, agent);

        /*agent = new Agent(5, 5, 1, 0, mEnvironment);
        mAgents.add(agent);
        mEnvironment.setAgent(5, 5, agent);

        agent = new Agent(9, 9, 0, 1, mEnvironment);
        mAgents.add(agent);
        mEnvironment.setAgent(9, 9, agent);*/
	}


	public Environment getEnvironment() {
		return mEnvironment;
	}

}
