package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Predator extends Agent {

    public Predator(Environment environment, int posX, int posY) {
        super(environment, posX, posY);
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public void doIt() {
    	if(((PacmanEnvironment)mEnvironment).getFinish()){
    		return;
    	}
    	
        PacmanCell[][] neighbors = ((PacmanEnvironment)mEnvironment).getPacmanNeighbors(mPosX, mPosY);
        int minDijkstraValue = 0;
        ArrayList<Position> nextCells = new ArrayList<Position>();
        int xMin = 1;
        int yMin = 1;
        
        for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Prey){
        			((PacmanEnvironment)mEnvironment).setFinish(true);
        			
        		}else if(neighbors[y][x] != null && neighbors[y][x].getAgent() == null){
        			if(minDijkstraValue == 0 || neighbors[y][x].getDijkstraValue() < minDijkstraValue){
        				minDijkstraValue = neighbors[y][x].getDijkstraValue();
        				nextCells.clear();
        				nextCells.add(new Position(x,y));
        			}else if(minDijkstraValue == 0 || neighbors[y][x].getDijkstraValue() == minDijkstraValue){
        				//if same distance, take it randomly (to have a random beetween 
        				minDijkstraValue = neighbors[y][x].getDijkstraValue();
        				nextCells.add(new Position(x,y));
        			}
        		}
        	}
        }
        Collections.shuffle(nextCells);
        moveAgent(neighbors, nextCells.get(0).getX(), nextCells.get(0).getY());
    }
    
    protected void moveAgent(Cell[][] neighbors, int posX, int posY) {
    	//System.out.println("Move agent [1][1] to ["+posY+"]["+posX+"]");
        neighbors[1][1].setAgent(null);
        neighbors[posY][posX].setAgent(this);
        mPosX = Math.floorMod((mPosX + posX -1), mEnvironment.getWidth()); // -1 because transition local->global
        mPosY = Math.floorMod((mPosY + posY -1), mEnvironment.getHeight());
    }
}
