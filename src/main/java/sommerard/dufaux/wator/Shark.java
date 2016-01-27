package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Shark extends Animal {
	
	private static int breed; //can't be final static because initialize at the execution
	private static int starve; //can't be final static because initialize at the execution

    private int mStarve;
    
	public Shark(WatorMAS mas, Environment environment, int posX, int posY, Random random, boolean randomState) {
        super(mas, environment, posX, posY, random);
        
        if (randomState){
        	mBreed = random.nextInt(breed);
        	mStarve = random.nextInt(starve-1);
        	return;
        }
        mStarve = 0;
        mBreed = 0;
    }
	
	
	public Shark(WatorMAS mas, Environment environment, int posX, int posY, Random random) {
        super(mas, environment, posX, posY, random);
        mStarve = 0;
        mBreed = 0;
    }

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
    public void doIt() {
    	if(!mAlive)
    		return;
    	
    	mStarve++;
    	mBreed++;
    	mAge++;
    	//System.out.println(this);
    	Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

    	if(mStarve >= starve){
    		mAlive = false;
    		this.mMas.removeAgent(this); //affect mas
			neighbors[1][1].setAgent(null); //affect environment
    		return;
    	}
    	
    	eatFish(neighbors);
    	
        if(mBreed >= breed){
        	//System.out.println("reproduce");
        	breed(neighbors);
        	mBreed = 0;
        	//return; //do not move
        }
        moveRandom(neighbors);
    	
    }
    
    private void eatFish(Cell[][] neighbors){
    	ArrayList<Cell> fishs = new ArrayList<Cell>();
    	for(int y = 0; y <= 2; y++){
        	for(int x = 0; x <= 2; x++){
        		//not necessary because we remove the fish of the environment instantatly
        		//if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish && ((Fish)neighbors[y][x].getAgent()).isAlive()){
        		if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish){
        			fishs.add(neighbors[y][x]);
        		}
        	}
        }
    	
    	if(fishs.isEmpty()){
    		return;
    	}
    	int random = mRandom.nextInt(fishs.size());
    	Cell cellWithFish = fishs.get(random);
    	
		((Fish)cellWithFish.getAgent()).kill();
		this.mMas.removeAgent(cellWithFish.getAgent()); //affect mas
		cellWithFish.setAgent(null); //affect environment
		mStarve = 0;
		return;
    	
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
