package sommerard.dufaux.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sommerard.dufaux.wator.Fish;
import sommerard.dufaux.wator.MASWator;

public class Environment {

	private boolean mToric;
	private int mWidth; //X
	private int mHeight; //Y
	private Agent[][] mSpace; //[Y][X]
	private Random mRandom;
	//idée : faire une map <Position,Agent> 

	public Environment(int width, int height, boolean toric, Random random) {
		mToric = toric;
		mWidth = width;
		mHeight = height;
		mSpace = new Agent[height][width];
		mRandom = random;

		initCells();
	}

	private void initCells() {
		for (int y = 0; y < mHeight; y++) {
			for (int x = 0; x < mWidth; x++) {
				mSpace[y][x] = null;
			}
		}
	}
	
	private boolean isOutOfBorder(int posX, int posY){
		//System.out.println(posX+" - "+posY);
		return ((posX < 0) || (posX > mWidth-1) || (posY < 0) || (posY > mHeight-1));
	}

	public Agent[][] getNeighbors(int posX, int posY){
    	Agent[][] neighbors = new Agent[3][3];

	    for (int y = -1; y <= 1; y++) {
	        for (int x = -1; x <= 1; x++) {
	        	int neighborX = posX + x;
	        	int neighborY = posY + y;

				if (mToric) {
					neighborX = Math.floorMod(neighborX, mWidth);
					neighborY = Math.floorMod(neighborY, mHeight);
					neighbors[y + 1][x + 1] = mSpace[neighborY][neighborX];
					continue;
				}

				if (neighborX < 0 || neighborX > mWidth - 1 || neighborY < 0 || neighborY > mHeight - 1 ) { // Border
					neighbors[y + 1][x + 1] = null;
					continue;
				}

				neighbors[y + 1][x + 1] = mSpace[neighborY][neighborX];
	        }
	    }

	    return neighbors;
	}
	
	
	public boolean isFullNeighborhood(int posX, int posY){
		int pX, pY;
    	for(int y = posY-1; y <= posY+1; y++){
        	for(int x = posX-1; x <= posX+1; x++){
        		pX = x;
        		pY = y;
        		if(mToric){
        			pX = Math.floorMod(x, mWidth);
        			pY = Math.floorMod(y, mHeight);
        		}
        		if(!isOutOfBorder(pX, pY) && mSpace[pY][pX] == null){
        			return false;
        		}
        	}
        }
        return true;
    }
	
	public int[] getRandomPosition(int posX, int posY){
		int pX, pY;
		ArrayList<int[]> positions = new ArrayList<int[]>();
    	for(int y = posY-1; y <= posY+1; y++){
        	for(int x = posX-1; x <= posX+1; x++){
        		pX = x;
        		pY = y;
        		if(mToric){
        			pX = Math.floorMod(x, mWidth);
        			pY = Math.floorMod(y, mHeight);
        		}
        		//not necessary because we remove the fish of the environment instantatly
        		//if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish && ((Fish)neighbors[y][x].getAgent()).isAlive()){
        		if(!isOutOfBorder(pX, pY) && mSpace[pY][pX] == null){
        			positions.add(new int[]{pX,pY});
        		}
        	}
        }
    	
    	if(positions.isEmpty()){
    		return null;
    	}
    	
    	int random = mRandom.nextInt(positions.size());
    	return positions.get(random);
    	
	}
	
	
	public Agent getNeighborFish(int posX, int posY){
		int pX, pY;
		ArrayList<Agent> fishs = new ArrayList<Agent>();
    	for(int y = posY-1; y <= posY+1; y++){
        	for(int x = posX-1; x <= posX+1; x++){
        		pX = x;
        		pY = y;
        		if(mToric){
        			pX = Math.floorMod(x, mWidth);
        			pY = Math.floorMod(y, mHeight);
        		}
        		//not necessary because we remove the fish of the environment instantatly
        		//if(neighbors[y][x] != null && neighbors[y][x].getAgent() instanceof Fish && ((Fish)neighbors[y][x].getAgent()).isAlive()){
        		if(!isOutOfBorder(pX, pY) && mSpace[pY][pX] != null && mSpace[pY][pX] instanceof Fish && mSpace[pY][pX].isAlive()){
        			fishs.add(mSpace[pY][pX]);
        		}
        	}
        }
    	
    	if(fishs.isEmpty()){
    		return null;
    	}
    	
    	int random = mRandom.nextInt(fishs.size());
    	return fishs.get(random);
	}
	

    public void setAgent(int posX, int posY, Agent agent) {
		mSpace[posY][posX] = agent;
    }

    public Agent getAgent(int posX, int posY) {
        return mSpace[posY][posX];
    }
    
    public int getWidth(){
    	return mWidth;
    }
    
    public int getHeight(){
    	return mHeight;
    }

	public void move(Agent agent, int posX, int posY) {
		//System.out.println("move "+agent);
		//System.out.println("["+agent.getPosY()+"]["+agent.getPosX()+"] -- ["+posY+"]["+posX+"]");
		mSpace[agent.getPosY()][agent.getPosX()] = null;
		mSpace[posY][posX] = agent;
	}
    
}
