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

	public Shark(MASWator mas, Environment environment, int posX, int posY, Color color, Random random) {
        super(mas, environment, posX, posY, color, random);
        mStarve = starve;
    }

    @Override
    public void doIt() {
    	if(!mAlive)
    		return;
    	
    	mStarve--;
    	mBreed++;
    	mAge++;
    	//System.out.println(this);
    	Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

    	if(mStarve <= 0){
    		mAlive = false;
    		this.mMas.removeAgent(this); //affect mas
			neighbors[1][1].setAgent(null); //affect environment
    		return;
    	}
    	
    	try{
    		eatFish(neighbors);
    		//System.out.println("eat fish");
    		//return;
    	}catch(NoFishException e){
    	};
    	
        if(mBreed >= breed){
        	//System.out.println("reproduce");
        	breed(neighbors);
        	mBreed = this.breed;
        	return; //do not move
        }
        moveRandom(neighbors);
    	
    }
    
    private void eatFish(Cell[][] neighbors) throws NoFishException{
    	for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish ){
        			((Fish)neighbors[y][x].getAgent()).isKilled();
        			this.mMas.removeAgent(neighbors[y][x].getAgent()); //affect mas
        			neighbors[y][x].setAgent(null); //affect environment
        			mStarve = starve;
        			return;
        		}
        	}
        }
    	throw new NoFishException();
    }
    
    
    private void breed(Cell[][] neighbors){
		Position pos = randomEmptyCell(neighbors);
        if(pos != null){
        	Agent shark = this.mMas.createShark(mPosX+pos.getX()-1, mPosY+pos.getY()-1); //affect mas
        	neighbors[pos.getY()][pos.getX()].setAgent(shark); //affect environment

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
