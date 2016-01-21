package sommerard.dufaux.core;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class MAS extends Observable {

	protected Environment mEnvironment;
	protected List<Agent> mAgents;
	protected Random mRandom;

	protected boolean mEquity;
	protected int mSpeed;
	protected int mNbTurn;
	protected int mWidth;
	protected int mHeight;
	protected int mAgentSize;
	protected boolean mToric;
	protected long mSeed;

	public MAS(View view){
        addObserver(view);
        mAgents = new ArrayList<Agent>();
	}

	public void init(int nbTurn, int width, int height, int speed, int agentSize, boolean equity, long seed, boolean toric) {
		mSpeed = speed;
		mNbTurn = nbTurn;
		mWidth = width;
		mHeight = height;
		mToric = toric;
		mSeed = seed;
		mAgentSize = agentSize;

		mEnvironment = new Environment(mWidth, mHeight, mToric);
		mEquity = equity;

		
		mRandom = new Random(mSeed);
		
		initAgents();
		
		setChanged();
		notifyObservers(this);
	}
	
	public void run() throws InterruptedException {
		Thread.sleep(mSpeed);
		for (int i = 0; i < mNbTurn; i++) {
			System.out.println("TURN "+i);
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
	
	public abstract void initAgents();

	public Environment getEnvironment() {
		return mEnvironment;
	}

	public List<Agent> getAgents() {
		return mAgents;
	}
}
