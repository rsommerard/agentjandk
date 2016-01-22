package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.MAS;
import sommerard.dufaux.core.Position;
import sommerard.dufaux.core.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MASWator extends MAS {

    private int mNbShark;
    private int mNbFish;
    private int mFishBreed;
    private int mSharkBreed;
    private int mStarve;

    public MASWator(View view) {
        super(view);
    }

    @Override
    public void initAgents() {
        
    	List<Position> positions = new ArrayList<Position>();

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                positions.add(new Position(x, y));
            }
        }

        Collections.shuffle(positions, mRandom);

        for(int i = 0; i < mNbFish; i++) {
            if (positions.isEmpty()) {
                return;
            }

            Position position = positions.get(0);

            int posX = position.getX();
            int posY = position.getY();

            Agent agent = new Fish(this, mEnvironment, posX, posY, Color.PINK, mRandom);
            mAgents.add(agent);
            mEnvironment.setAgent(posX, posY, agent);

            positions.remove(0);
        }

        for(int i = 0; i < mNbShark; i++) {
            if (positions.isEmpty()) {
                return;
            }

            Position position = positions.get(0);

            int posX = position.getX();
            int posY = position.getY();

            Agent agent = new Shark(this, mEnvironment, posX, posY, Color.BLUE, mRandom);
            mAgents.add(agent);
            mEnvironment.setAgent(posX, posY, agent);

            positions.remove(0);
        }
    	

    	/*Agent fish = new Fish(this, mEnvironment, 0, 0, Color.PINK, mRandom);
    	mAgents.add(fish);
    	mEnvironment.setAgent(0, 0, fish);
    	
    	Agent shark = new Shark(this, mEnvironment, 3, 3, Color.BLUE, mRandom);
    	mAgents.add(shark);
    	mEnvironment.setAgent(3, 3, shark);
    	/**/
    }

    public void init(int nbTurn, int nbShark, int nbFish, int fBreed, int sBreed, int starve, int width, int height, int speed, int agentSize, boolean equity, long seed, boolean toric) {
        mNbShark = nbShark;
        mNbFish = nbFish;
        mFishBreed = fBreed;
        mSharkBreed = sBreed;
        mStarve = starve;
        
        Fish.setGlobalBreed(mFishBreed);
        Shark.setGlobalBreed(mSharkBreed);
        Shark.setGlobalStarve(mStarve);

        super.init(nbTurn, width, height, speed, agentSize, equity, seed, toric);
    }
    

    @Override
    public void run() throws InterruptedException {
    	super.run();
    	List<Animal> anims = new ArrayList<Animal>();
    	//greedy because we need to cast each Agent in Animal but just used at the end
    	//to have the age.
    	for(Agent a : mAgents){
    		anims.add((Animal)a);
    	}
    	Collections.sort(anims);
    	System.out.println("AT THE END :");
    	for(Agent a : anims){
    		System.out.println(a);
    	}
    	System.out.println("Total agent = "+anims.size());
    	System.out.println("Number of fish at the end = "+mNbFish);
    	System.out.println("Number of shark at the end = "+mNbShark);
    }
    
    
    public Agent createFish(int posX, int posY){
    	Agent fish = new Fish(this, mEnvironment, posX, posY, Color.PINK, mRandom);
    	mAgents.add(fish);
    	mNbFish++;
    	mEnvironment.setAgent(posX, posY, fish);
    	return fish;
    }

	public Agent createShark(int posX, int posY) {
    	Agent shark = new Shark(this, mEnvironment, posX, posY, Color.BLUE, mRandom);
    	mAgents.add(shark);
    	mNbShark++;
    	mEnvironment.setAgent(posX, posY, shark);
    	return shark;
	}    
    public boolean removeAgent(Agent agent){
    	if(agent instanceof Fish){
    		mNbFish--;
    	}
    	if(agent instanceof Shark){
    		mNbShark--;
    	}
    	return mAgents.remove(agent);
    }

    
}
