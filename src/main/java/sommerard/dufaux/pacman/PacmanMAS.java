package sommerard.dufaux.pacman;

import sommerard.dufaux.core.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacmanMAS extends MAS {

    private int mNbPredator;
    private int mNbRock;
    private double mSpeedRatio;
    private KeyboardListener mKeyListener;

    public PacmanMAS(KeyboardListener keyListener) {
        super();
        this.mKeyListener = keyListener;
    }

    public void init(int nbPredator, int nbRock, int width, int height, int speed, double speedRatio, int agentSize, boolean equity, long seed) {
        mNbPredator = nbPredator;
        mNbRock = nbRock;
        mSpeedRatio = speedRatio;
        super.init(1000000, width, height, speed, agentSize, equity, seed, false);
    }

    public void initEnvironment() {
        mEnvironment = new PacmanEnvironment(mWidth, mHeight, mToric);
    }

    @Override
    public void initAgents() {
    	
    	double ratio = mSpeedRatio;
    	int predatorSpeed = (int) Math.round(ratio);
    	int preySpeed = 1;
    	
    	while(ratio % 1 != 0){
    		ratio = ratio*10;
    		predatorSpeed = (int) Math.round(ratio);
    		preySpeed = preySpeed*10;
    	}
    	int gcd = Utils.GCD(predatorSpeed,preySpeed);
    	predatorSpeed = predatorSpeed/gcd;
    	preySpeed = preySpeed/gcd;

        List<Position> positions = new ArrayList<Position>();
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                positions.add(new Position(x, y));
            }
        }
        Collections.shuffle(positions, mRandom);

        //CREATE ROCKS
        for (int i = 0; i < mNbRock; i++) {
            Position position = positions.get(0);

            Agent rock = new Rock(mEnvironment, position.getX(), position.getY());
            mAgents.add(rock);
            mEnvironment.setAgent(position.getX(), position.getY(), rock);

            positions.remove(0);
        }

        //CREATE PREDATORS
        for (int i = 0; i < mNbPredator; i++) {
            Position position = positions.get(0);

            Agent predator = new Predator(mEnvironment, position.getX(), position.getY(), predatorSpeed);
            mAgents.add(predator);
            mEnvironment.setAgent(position.getX(), position.getY(), predator);

            positions.remove(0);
        }

        //CREATE THE PREY
        Position position = positions.get(0);
        Prey prey = new Prey(mEnvironment, position.getX(), position.getY(), preySpeed);
        mAgents.add(prey);
        mEnvironment.setAgent(position.getX(), position.getY(), prey);
        positions.remove(0);
        this.mKeyListener.setPrey(prey);
    }
    
    
    @Override
	protected boolean checkStop(){
    	if(((PacmanEnvironment)mEnvironment).getFinish()){
    		System.out.println("END OF GAME");
    		
    		for(Agent a : mAgents){
    			System.out.println(a);
    		}
    		return true;
    	}
    	return false;
    }
    
}
