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

	protected List<Agent> addInTurn = new ArrayList<Agent>();
	protected List<Agent> removeInTurn = new ArrayList<Agent>();
	
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

		mRandom = new Random(mSeed);
		
		mEnvironment = new Environment(mWidth, mHeight, mToric, mRandom);
		mEquity = equity;
		mCurrentTurn = 0;
		
		initAgents();
		
		setChanged();
		notifyObservers(this);
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

            
            /*List<Agent> agentsBeforeRun = new ArrayList<Agent>();
            agentsBeforeRun.addAll(mAgents);*/
			for (Agent agent : mAgents) {
                agent.doIt();
			}
			
			//clean dead agents
			for(Agent agent : removeInTurn){
			mAgents.remove(agent);
			}
			removeInTurn.clear();
			
			for(Agent agent : addInTurn){
				if(agent.isAlive()){
					mAgents.add(agent);
				}
			}
			addInTurn.clear();
			
			System.out.println("turn "+mCurrentTurn);
			/*System.out.println("nbAgent = "+mAgents.size());
			System.out.println("removeInTurn = "+removeInTurn.size());
			System.out.println("addInTurn = "+addInTurn.size());*/
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
