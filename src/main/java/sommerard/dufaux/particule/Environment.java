package sommerard.dufaux.particule;

public class Environment {

	private boolean mToric;
    private int mWidth; //X
	private int mHeight; //Y
	private Cell[][] mSpace; //[Y][X]

	public Environment(int width, int height, boolean toric) {
		mWidth = width;
		mHeight = height;
		mToric = toric;
		mSpace = new Cell[height][width];
	    for(int y=0;y<height;y++)
	    {
	        for(int x=0;x<width;x++)
	        {
	        	mSpace[y][x] = new Cell(null);
	        }
	    }
	}

	public Cell[][] getNeighbors(int posX, int posY){
    	Cell[][] neighbors = new Cell[3][3];
	    for(int y=-1;y<=1;y++)
	    {
	        for(int x=-1;x<=1;x++)
	        {
	        	int Xneighbor = posX+x;
	        	int Yneighbor = posY+y;
	        	//System.out.println("i+1 "+(i+1)+" et j+1 = "+(j+1));
	        	
	        	if(Xneighbor<0 || Xneighbor >mWidth-1 || Yneighbor<0 || Yneighbor>mHeight-1 ){ //border
	        		if(mToric){
	        			neighbors[y+1][x+1] = mSpace[Yneighbor%mHeight][Xneighbor%mWidth];
	        		}else{
	        			neighbors[y+1][x+1] = null;
	        		}
	        	}else{
	        		neighbors[y+1][x+1]=mSpace[Yneighbor][Xneighbor];
	        	}
	        }
	    }
	    return neighbors;
	}

    public boolean isCorner(int posX, int posY) {
        if (posX == 0 && posY == 0) {
            return true;
        } else if (posX == 0 && posY == mHeight - 1) {
            return true;
        } else if (posX == mWidth - 1 && posY == 0) {
            return true;
        } else if (posX == mWidth - 1 && posY == mHeight - 1) {
            return true;
        } else {
            return false;
        }
    }

	public boolean isBusy(int posX, int posY){
		if (posX < 0) {
			return true;
		}

		if (posX > mWidth - 1) {
            return true;
        }

        if (posY < 0 ) {
            return true;
        }

        if (posY > mHeight - 1) {
            return true;
        }

        return mSpace[posY][posX] != null;
	}

    public void setAgent(int posX, int posY, Agent agent) {
        mSpace[posY][posX].setAgent(agent);
    }

    public Agent getAgent(int posX, int posY) {
        return mSpace[posY][posX].getAgent();
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
