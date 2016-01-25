package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.MAS;
import sommerard.dufaux.core.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Fish extends Animal {

	private static int breed; //can't be final static because initialize at the execution
	private final static ArrayList<Position> positions = new ArrayList<Position>(); //list of neighbors positions possible.
	{
		positions.add(new Position(0,0));
		positions.add(new Position(0,1));
		positions.add(new Position(0,2));
		positions.add(new Position(1,0));
		positions.add(new Position(1,2));
		positions.add(new Position(2,0));
		positions.add(new Position(2,1));
		positions.add(new Position(2,2));
	}
	
	public Fish(MASWator mas, Environment environment, int posX, int posY, Color color, Random random, boolean randomState) {
        super(mas, environment, posX, posY, color, random);
        if (randomState){
        	mBreed = random.nextInt(breed);
        	return;
        }
        mBreed = 0;
    }
	
	public Fish(MASWator mas, Environment environment, int posX, int posY, Color color, Random random) {
        super(mas, environment, posX, posY, color, random);
        //mBreed = random.nextInt(breed);
        mBreed = 0;
    }

    @Override
    public void doIt() {
    	if(!mAlive)
    		return;
    	
    	mBreed++;
    	mAge++;
    	//System.out.println(this);
    	
        Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

        if(isFullNeighborhood(neighbors)){
        	return; //do not move
        }
        
        
        if(mBreed >= breed){
        	breed(neighbors);
        	mBreed = 0;
        	return; //do not move
        }
        
        moveRandom(neighbors);


    }
    
    //NOT USED FOR THE MOMENT
    /*
    private void moveToEscape(Cell[][] neighbors) {
    	int nextDirX = 0, nextDirY = 0;
    	analyzeEnvironment:
        for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Shark){
        			//try to move
                	nextDirX = 1-x;
                    nextDirY = 1-y;
                    if(neighbors[nextDirY+1][nextDirX+1] != null && neighbors[nextDirY+1][nextDirX+1].getAgent() == null){
                    	//case free
                    	break analyzeEnvironment;
                    }
        		}
        	}
        }

		System.out.println("Move random dirX="+nextDirX+" and dirY ="+nextDirY+"]");
        moveAgent(neighbors,nextDirX,nextDirY);
	}*/
    
    private void breed(Cell[][] neighbors){
		Position pos = randomEmptyCell(neighbors);
        if(pos != null){
        	Agent fish = this.mMas.createFish((mPosX+pos.getX()-1), (mPosY+pos.getY()-1)); //affect mas
        	neighbors[pos.getY()][pos.getX()].setAgent(fish); //affect environment
        }
    }
    
    @Override
    public String toString(){
    	return this.getClass().getName()+" "+Integer.toHexString(hashCode())+" ["+mPosY+"]["+mPosX+"] breed = "+mBreed+", age ="+mAge;
    }
    
    public static void setGlobalBreed(int breed){
    	Fish.breed = breed;
    }
}
