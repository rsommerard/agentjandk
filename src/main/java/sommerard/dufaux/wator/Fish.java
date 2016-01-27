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
    	//System.out.println("--"+this);
    	
        if(mEnvironment.isFullNeighborhood(mPosX, mPosY)){
        	return; //do not move
        }
        
        
        if(mBreed >= breed){
        	breed();
        	return; //do not move
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
    
    
    private void breed(){
		int[] pos = mEnvironment.getRandomPosition(mPosX, mPosY);
        if(pos != null){
        	Agent fish = this.mMas.createFish((pos[0]), (pos[1])); //affect mas
        	this.mEnvironment.setAgent(pos[0], pos[1], fish);

        	//System.out.println("[BRE] "+fish);
        	mBreed = 0;
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
