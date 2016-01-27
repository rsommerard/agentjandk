package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.MAS;
import sommerard.dufaux.core.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Shark extends Animal {
	
	private static int breed; //can't be final static because initialize at the execution
	private static int starve; //can't be final static because initialize at the execution

    private int mStarve;
    
	public Shark(MASWator mas, Environment environment, int posX, int posY, Color color, Random random, boolean randomState) {
        super(mas, environment, posX, posY, color, random);
        
        if (randomState){
        	mBreed = random.nextInt(breed);        
        	mStarve = random.nextInt(starve-1);
        	return;
        }
        mStarve = 0;
        mBreed = 0;
    }
	
	
	public Shark(MASWator mas, Environment environment, int posX, int posY, Color color, Random random) {
        super(mas, environment, posX, posY, color, random);
        mStarve = 0;
        mBreed = 0;
    }

    @Override
    public void doIt() {
    	if(!mAlive)
    		return;
    	
    	mStarve++;
    	mBreed++;
    	mAge++;
    	//System.out.println("--"+this);
    	//Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

    	if(mStarve >= starve){
    		mAlive = false;
    		this.mMas.removeAgent(this); //affect mas
    		this.mEnvironment.setAgent(mPosX, mPosY, null); //affect environment
    		return;
    	}
    	
    	eatFish();
    	
        if(mBreed >= breed){
        	//System.out.println("reproduce");
        	breed();
        	mBreed = 0;
        	//return; //do not move
        }
    	
    	//move random
        int[] newPos = this.mEnvironment.getRandomPosition(mPosX, mPosY);
        if(newPos != null){
        	//System.out.println("[MOV]"+this);
	        this.mEnvironment.move(this, newPos[0], newPos[1]);
	        this.mPosX = newPos[0];
	        this.mPosY = newPos[1];
        	//System.out.println("[MOV]"+this);
        }
    	
    }
    
    private void eatFish(){
    	
    	Agent fish = this.mEnvironment.getNeighborFish(mPosX, mPosY);
    	if(fish == null){
    		return;
    	}
    	
    	//System.out.println("[EAT] "+this);
    	//System.out.println("[EAT] "+fish);
    	fish.die();
		this.mMas.removeAgent(fish); //affect mas
		mEnvironment.setAgent(fish.getPosX(), fish.getPosY(), null);
		mStarve = 0;    	
    }
    
    
    private void breed(){
		int[] pos = mEnvironment.getRandomPosition(mPosX, mPosY);
        if(pos != null){
        	Agent fish = this.mMas.createShark((pos[0]), (pos[1])); //affect mas
        	this.mEnvironment.setAgent(pos[0], pos[1], fish);
        }
    }
    
    @Override
    public String toString(){
    	return this.getClass().getName()+" "+Integer.toHexString(hashCode())+" ["+mPosY+"]["+mPosX+"] breed = "+mBreed+", starve = "+mStarve+", age ="+mAge;
    }
    
    public static void setGlobalBreed(int breed){
    	Shark.breed = breed;
    }
    
    public static void setGlobalStarve(int starve){
    	Shark.starve = starve;
    }
    
}
