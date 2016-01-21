package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;

import java.awt.*;
import java.util.Random;

public class Fish extends Agent {

    private Random mRandom;

	public Fish(Environment environment, int posX, int posY, Color color, Random random) {
        super(environment, posX, posY, color);
        mRandom = random;
    }

    @Override
    public void doIt() {
        Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);
        
        boolean fullNeighborhood = true;
        boolean escape = false;
        
        
        //Analyze local environment
        for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() == null){
        			fullNeighborhood = false;
        		}
        		
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Shark){
        			escape = true;
        		}
        	}
        }
        
        //first comportment is escape
        if(escape){
        	moveToEscape(neighbors);
        	return;
        }
        //then reproduce
        
        //then random
        if(!fullNeighborhood){
        	moveRandom(neighbors);
        }

    }
    
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
	}
    

	private void moveRandom(Cell[][] neighbors){
        int nextDirX = 0, nextDirY = 0;
        do{
        	nextDirX = mRandom.nextInt(3)-1;
            nextDirY = mRandom.nextInt(3)-1;
        }while((nextDirX == 0 && nextDirY ==0) || //don't be stuck in the middle with you. 
        		(neighbors[nextDirY+1][nextDirX+1] == null || //border
        		neighbors[nextDirY+1][nextDirX+1].getAgent() != null)); //agent in case

		System.out.println("Move random dirX="+nextDirX+" and dirY ="+nextDirY+"]");
        moveAgent(neighbors,nextDirX,nextDirY);
        
    }
    
    private void moveAgent(Cell[][] neighbors, int dirX, int dirY) {
        neighbors[1][1].setAgent(null);
        neighbors[dirY+1][dirX+1].setAgent(this);
        mPosX = mPosX + dirX;
        mPosY = mPosY + dirY;
    }
}
