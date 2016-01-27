package sommerard.dufaux.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

public abstract class Animal extends Agent implements Comparable<Animal>{
	protected MASWator mMas;
	protected Random mRandom;
	protected int mBreed;
	protected int mAge;
	protected boolean mAlive;

	public Animal(MASWator mas, Environment environment, int posX, int posY, Random random) {
        super(environment, posX, posY);
        mMas = mas;
        mRandom = random;
        //mBreed = mRandom.nextInt(breed);
        mAge = 0;
        mAlive = true;
    }

	protected boolean isFullNeighborhood(Cell[][] neighbors){
        for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() == null){
        			//System.out.println("full Neighborhoord ["+y+"]["+x+"]");
        			return false;
        		}
        	}
        }
        return true;
    }
    
	protected Position randomEmptyCell(Cell[][] neighbors){
		
		ArrayList<Position> emptys = new ArrayList<Position>();
    	for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		//not necessary because we remove the fish of the environment instantatly
        		//if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish && ((Fish)neighbors[y][x].getAgent()).isAlive()){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() == null){
        			emptys.add(new Position(x,y));
        		}
        	}
        }
    	
    	if(emptys.isEmpty()){
    		return null;
    	}
    	
    	int random = mRandom.nextInt(emptys.size());
    	return emptys.get(random);
    }
    
    protected void moveRandom(Cell[][] neighbors){
		Position pos = randomEmptyCell(neighbors);
        if(pos != null){
    		moveAgent(neighbors,pos.getX(),pos.getY());	
        }
    }
	
    protected void moveAgent(Cell[][] neighbors, int posX, int posY) {
    	//System.out.println("Move agent [1][1] to ["+posY+"]["+posX+"]");
        neighbors[1][1].setAgent(null);
        neighbors[posY][posX].setAgent(this);
        mPosX = Math.floorMod((mPosX + posX -1), mEnvironment.getWidth()); // -1 because transition local->global
        mPosY = Math.floorMod((mPosY + posY -1), mEnvironment.getHeight());
    }

    public void kill(){
    	this.mAlive = false;
    }
    
    public int compareTo(Animal animal){
    	if(animal.mAge > mAge)
    		return 1;
    	else if(animal.mAge == mAge)
    		return 0;
    	return -1;
    	
    }
    
}
