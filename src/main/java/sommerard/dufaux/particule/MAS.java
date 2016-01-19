package sommerard.dufaux.particule;

import java.awt.*;
import java.util.*;
import java.util.List;

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
	private int mAgentSize;
	private boolean mToric;
	private long mSeed;

	public MAS(View view){
        addObserver(view);
        mAgents = new ArrayList<Agent>();
	}

	public void init(int nbTurn, int nbAgent, int width, int height, int speed, int agentSize, boolean equity, long seed, boolean toric) {
		mSpeed = speed;
		mNbTurn = nbTurn;
		mNbAgent = nbAgent;
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
		for (int i = 0; i < mNbTurn; i++) {
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
	
	public void initAgents() {
		List<Position> positions = new ArrayList<Position>();

		for (int y = 0; y < mHeight; y++) {
			for (int x = 0; x < mWidth; x++) {
				positions.add(new Position(x, y));
			}
		}

		Collections.shuffle(positions, mRandom);

		for(int i = 0; i < mNbAgent; i++) {
			if (positions.isEmpty()) {
				return;
			}

			Position position = positions.get(0);

			int posX = position.getX();
			int posY = position.getY();
			int stepX = mRandom.nextInt(2) - 1;
			int stepY = mRandom.nextInt(2) - 1;

			while(stepX == 0 && stepY == 0) {
				stepX = mRandom.nextInt(2) - 1;
				stepY = mRandom.nextInt(2) - 1;
			}

            Agent agent = new Agent(mEnvironment, posX, posY, stepX, stepY, Color.getColor(null, mRandom.nextInt()));
			mAgents.add(agent);
			mEnvironment.setAgent(posX, posY, agent);

			positions.remove(0);
		}
	}

	public Environment getEnvironment() {
		return mEnvironment;
	}

}
