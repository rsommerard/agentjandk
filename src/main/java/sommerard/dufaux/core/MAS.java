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
	protected int mCurrentTurn;

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

		mEquity = equity;
		mCurrentTurn = 0;
		
		mRandom = new Random(mSeed);

		initEnvironment();
		initAgents();
		
		setChanged();
		notifyObservers(this);
	}

	public void initEnvironment() {
		mEnvironment = new Environment(mWidth, mHeight, mToric);
	}
	
	public void run() throws InterruptedException {

		for (int i = 0; i < mNbTurn; i++) {
			setChanged();
			notifyObservers(this);
			Thread.sleep(mSpeed);

			
			//System.out.println("TURN "+i);
            if (mEquity) {
				Collections.shuffle(mAgents, mRandom);
			}

            
            List<Agent> agentsBeforeRun = new ArrayList<Agent>();
            agentsBeforeRun.addAll(mAgents);
			for (Agent agent : agentsBeforeRun) {
                agent.doIt();
			}

			//System.out.println("turn "+mCurrentTurn);
			//System.out.println("nbAgent = "+mAgents.size());
			mCurrentTurn++;
		}
	}
	
	public abstract void initAgents();

	public Environment getEnvironment() {
		return mEnvironment;
	}

	public List<Agent> getAgents() {
		return mAgents;
	}
	
	public int getCurrentTurn() {
		return mCurrentTurn;
	}
}
