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
        
        if(fullNeighborhood){
        	moveRandom(neighbors);
        }

    }
    
    private void moveRandom(Cell[][] neighbors){
        int nextDirX, nextDirY;
        do{
        	nextDirX = mRandom.nextInt(3)-1;
            nextDirY = mRandom.nextInt(3)-1;
        }while((nextDirX == 0 && nextDirY ==0) || //don't be stuck in the middle with you. 
        		(neighbors[nextDirY+1][nextDirX+1] == null || //border
        		neighbors[nextDirY+1][nextDirX+1].getAgent() != null)); //agent in case
        
        moveAgent(neighbors,nextDirX,nextDirY);
        
    }
    
    private void moveAgent(Cell[][] neighbors, int dirX, int dirY) {
        neighbors[1][1].setAgent(null);
        neighbors[dirY+1][dirX+1].setAgent(this);
        mPosX = mPosX + dirX;
        mPosY = mPosY + dirY;
    }
}
