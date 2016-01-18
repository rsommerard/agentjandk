package sommerard.dufaux.particule;

import java.awt.Color;

public class Agent {

    private String mName;
    private Color mColor = Color.RED;
	private int mPosX;
	private int mPosY;
    private int mDirX;
    private int mDirY;
	private Environment mEnvironment;

	public Agent(int posX, int posY, int stepX, int stepY, Environment environment) {
        mPosX = posX;
		mPosY = posY;
        setDirection(stepX, stepY);
        mEnvironment = environment;
	}
    
    public Agent(int posX, int posY, int stepX, int stepY, Color color, Environment environment) {
        //System.out.println("Agent: [name: " + name + ", posX: " + posX + ", posY: " + posY + ", stepX: " + stepX + ", stepY: " + stepY + "]");
        //mName = name;
        mColor = color;
        mPosX = posX;
		mPosY = posY;
        setDirection(stepX, stepY);
        mEnvironment = environment;
	}

    public void setDirection(int stepX, int stepY) {
        mDirX = stepX;
        mDirY = stepY;

        //System.out.println(mName + " direction changed.");
    }
	
	public void doIt(){
		
		System.out.println("Do it "+mColor+" X = "+mPosX+" et Y="+mPosY);
		Cell[][] neighbors = mEnvironment.getNeighbors(mPosX,mPosY);
		
		
		System.out.println("list of neighbors of "+this.mColor);
		for(int i =0; i<=2;i++){
			for(int j=0; j<=2;j++){
				if(neighbors[i][j] != null && neighbors[i][j].getAgent() != null){
					System.out.println("Cell["+i+"]["+j+"].getAgent() = "+neighbors[i][j].getAgent());
				}
				else{
					System.out.println("Cell["+i+"]["+j+"] = "+neighbors[i][j]);
				}
			}
		}
		
		//let this as the middle of the array.
		int posXInNeighbors = 1;
		int posYInNeighbors = 1;
		
		System.out.println("########## "+this.mColor);
		System.out.println("currentX="+this.mPosX+", currentY"+this.mPosY);
		System.out.println("try to move ["+posYInNeighbors+"]["+posXInNeighbors+"] to ["+(posYInNeighbors+mDirY)+"]["+(posXInNeighbors+mDirX)+"]");
		//no case mean border of the environment
		if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX] == null){
			System.out.println("--fail");
			this.inverseDirection();
			System.out.println("--try to move ["+posYInNeighbors+"]["+posXInNeighbors+"] to ["+(posYInNeighbors+mDirY)+"]["+(posXInNeighbors+mDirX)+"]");
			if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX] == null){//the case is stuck beetween 2 border?!
				System.out.println("----fail");
				//don't move the agent
			}
			else if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX].getAgent() != null){//the case is occupy
				System.out.println("----fail");
				//don't move the agent
			}
			else{ 
				System.out.println("--- moved ["+posYInNeighbors+"]["+posXInNeighbors+"] to ["+(posYInNeighbors+mDirY)+"]["+(posXInNeighbors+mDirX)+"]");
				
				//free case
				//move the agent in another casemPosX = posXInNeighbors+mDirX;
				mPosX = mPosX+mDirX;
				mPosY = mPosY+mDirY;
				neighbors[posYInNeighbors][posXInNeighbors].setAgent(null);
				neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX].setAgent(this);
			}
		}
		else if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX].getAgent() != null){//the case is occupy
			System.out.println("--fail");
			this.inverseDirection();
			System.out.println("--try to move ["+posYInNeighbors+"]["+posXInNeighbors+"] to ["+(posXInNeighbors+mDirX)+"]["+(posXInNeighbors+mDirY)+"]");
			if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX] == null){//the case is stuck beetween 2 border?!
				System.out.println("----fail");
				//don't move the agent
			}
			else if(neighbors[posYInNeighbors+mDirY][posXInNeighbors+mDirX].getAgent() != null){//the case is occupy
				System.out.println("----fail");
				//don't move the agent
			}
			else{ 
				System.out.println("--- moved ["+posXInNeighbors+"]["+posYInNeighbors+"] to ["+(posYInNeighbors+mDirY)+"]["+(posXInNeighbors+mDirX)+"]");
				
				//free case
				//move the agent in another case
				mPosX = mPosX+mDirX;
				mPosY = mPosY+mDirY;
				neighbors[posXInNeighbors][posYInNeighbors].setAgent(null);
				neighbors[posXInNeighbors+mDirX][posXInNeighbors+mDirY].setAgent(this);
			}
		}
		else{ //free case
			System.out.println("-- free case. move ["+posXInNeighbors+"]["+posYInNeighbors+"] to ["+(posXInNeighbors+mDirX)+"]["+(posXInNeighbors+mDirY)+"]");
			//move the agent in another case
			mPosX = mPosX+mDirX;
			mPosY = mPosY+mDirY;
			neighbors[posXInNeighbors][posYInNeighbors].setAgent(null);
			neighbors[posXInNeighbors+mDirX][posXInNeighbors+mDirY].setAgent(this);
		}
		
		
        /*int rightLimit = mEnvironment.getWidth() - 1;
        int leftLimit = 0;
        int topLimit = 0;
        int bottomLimit = mEnvironment.getHeight() - 1;

        int nextX = mPosX + mDirX;
        int nextY = mPosY + mDirY;

        // Resolve corner bug
        if (mEnvironment.isCorner(mPosX, mPosY) && mEnvironment.isBusy(nextX, nextY)) {
            mDirX = mDirX * (-1);
            mDirY = mDirY * (-1);
            nextX = mPosX + mDirX;
            nextY = mPosY + mDirY;
        }

        // border
        if (mEnvironment.isBusy(nextX, nextY)) {
            if (nextX > rightLimit) {
                mDirX = mDirX * (-1);
            } else if (nextX < leftLimit) {
                mDirX = mDirX * (-1);
            } else if (nextY > bottomLimit) {
                mDirY = mDirY * (-1);
            } else if (nextY < topLimit) {
                mDirY = mDirY * (-1);
            }

            nextX = mPosX + mDirX;
            nextY = mPosY + mDirY;
        }

        if (mEnvironment.isBusy(nextX, nextY)) {
            Agent agent = mEnvironment.getAgent(nextX, nextY);
            agent.inverseDirection();

            mDirX = mDirX * (-1);
            mDirY = mDirY * (-1);
            nextX = mPosX + mDirX;
            nextY = mPosY + mDirY;

            if (mEnvironment.isBusy(nextX, nextY)) {
                mDirX = mDirX * (-1);
                mDirY = mDirY * (-1);
                nextX = mPosX;
                nextY = mPosY;
            }
        }

        mEnvironment.setAgent(mPosX, mPosY, null);
		mPosX = nextX;
		mPosY = nextY;
        mEnvironment.setAgent(mPosX, mPosY, this);*/
	}
	

    private void inverseDirection() {
        mDirX *= -1;
        mDirY *= -1;
    }

    public Color getColor() {
        return mColor;
    }
}