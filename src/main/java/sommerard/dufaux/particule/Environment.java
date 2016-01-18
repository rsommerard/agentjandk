package sommerard.dufaux.particule;

public class Environment {

    private int mWidth; //X
	private int mHeight; //Y
	private Cell[][] mCells; //[Y][X]

	public Environment(int width, int height) {
		mWidth = width;
		mHeight = height;
		mCells = new Cell[height][width];

		initCells();
	}

	private void initCells() {
		for (int y = 0; y < mHeight; y++) {
			for (int x = 0; x < mWidth; x++) {
				mCells[y][x] = new Cell(null);
			}
		}
	}

	public Cell[][] getNeighbors(int posX, int posY){
    	Cell[][] neighbors = new Cell[3][3];

	    for (int y = -1; y <= 1; y++) {
	        for (int x = -1; x <= 1; x++) {
	        	int neighborX = posX + x;
	        	int neighborY = posY + y;

	        	if (neighborX < 0 || neighborX > mWidth - 1 || neighborY < 0 || neighborY > mHeight - 1 ) { // Border
					neighbors[y + 1][x + 1] = null;
	        	} else {
	        		neighbors[y + 1][x + 1] = mCells[neighborY][neighborX];
	        	}
	        }
	    }

	    return neighbors;
	}

    public void setAgent(int posX, int posY, Agent agent) {
		mCells[posY][posX].setAgent(agent);
    }

    public Agent getAgent(int posX, int posY) {
        return mCells[posY][posX].getAgent();
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
