package sommerard.dufaux.particule;

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

	public void init(int nbTurn, int nbBall, int width, int height, int agentSize, int speed, boolean equity, String seed, boolean toric) {

		mEnvironment = new Environment(width, height, agentSize);
		
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
		for(int i = 0; i < nbBall; i++){
			int posX = mRandom.nextInt(width - 1);
			int posY = mRandom.nextInt(height - 1);
			int stepX = mRandom.nextInt(2) - 1;
			int stepY = mRandom.nextInt(2) - 1;

			while(stepX == 0 && stepY == 0) {
				stepX = mRandom.nextInt(2) - 1;
				stepY = mRandom.nextInt(2) - 1;
			}
			
			mAgents.add(new Agent(posX, posY, stepX, stepY, mEnvironment));
			mEnvironment.setState(posX, posY, true);
		}
	}


	public Environment getEnvironment() {
		return mEnvironment;
	}

}
