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

	private boolean mEquity;
	private int mSpeed;
	private int mNbTurn;
	private int mNbAgent;
	private int mWidth;
	private int mHeight;
	private boolean mToric;
	private long mSeed;

	public MAS(View view){
        addObserver(view);
        mAgents = new ArrayList<Agent>();
	}

	public void init(int nbTurn, int nbAgent, int width, int height, int speed, boolean equity, String seed, boolean toric) {
		mSpeed = speed;
		mNbTurn = nbTurn;
		mNbAgent = nbAgent;
		mWidth = width;
		mHeight = height;
		mToric = toric;
		mSeed = (seed != null) ? Long.parseLong(seed) : null;

		mEnvironment = new Environment(mWidth, mHeight, mToric);
		mEquity = equity;

		
		mRandom = (seed != null) ? new Random(mSeed) : new Random();
		
		initAgents();
		
		setChanged();
		notifyObservers(this);
	}
	
	public void run() throws InterruptedException {
		for (int i = 0; i < mNbTurn; i++) {
            System.out.println("Turn: " + i);

			setChanged();
			notifyObservers(this);

            if (mEquity) {
				Collections.shuffle(mAgents, mRandom);
			}

			for (Agent agent : mAgents) {
                agent.doIt();
			}
			
			Thread.sleep(mSpeed);
		}
	}
	
	public void initAgents(){
		for(int i = 0; i < mNbAgent; i++){
			int posX = mRandom.nextInt(mWidth - 1);
			int posY = mRandom.nextInt(mHeight - 1);
			int stepX = mRandom.nextInt(2) - 1;
			int stepY = mRandom.nextInt(2) - 1;

			while(stepX == 0 && stepY == 0) {
				stepX = mRandom.nextInt(2) - 1;
				stepY = mRandom.nextInt(2) - 1;
			}

            Agent agent = new Agent(mEnvironment, posX, posY, stepX, stepY, Color.getColor(null, mRandom.nextInt()));
			mAgents.add(agent);
			mEnvironment.setAgent(posX, posY, agent);
		}
	}

	public Environment getEnvironment() {
		return mEnvironment;
	}

}
