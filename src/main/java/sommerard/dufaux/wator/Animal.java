package sommerard.dufaux.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

public abstract class Animal extends Agent implements Comparable<Animal>{

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
	
	protected MASWator mMas;
	protected Random mRandom;
	protected int mBreed;
	protected int mAge;
	protected boolean mAlive;

	public Animal(MASWator mas, Environment environment, int posX, int posY, Color color, Random random) {
        super(environment, posX, posY, color);
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
    	//check if there is at least one empty cell
    	if(isFullNeighborhood(neighbors))
    		return null;
    	
    	Position newPos;
    	do{
        	newPos = positions.get(mRandom.nextInt(positions.size()));
        }while((neighbors[newPos.getY()][newPos.getX()] == null || //border
        		neighbors[newPos.getY()][newPos.getX()].getAgent() != null)); //agent in case

    	return newPos;
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
    
    
    public void die(){
    	this.mAlive = false;
    }
    
    public boolean isAlive(){
    	return this.mAlive;
    }
    
    public int compareTo(Animal animal){
    	if(animal.mAge > mAge)
    		return 1;
    	else if(animal.mAge == mAge)
    		return 0;
    	return -1;
    	
    }
    
}
