package sommerard.dufaux.core;

public class Environment {

    protected boolean mToric;
    protected int mWidth; //X
    protected int mHeight; //Y
    protected Cell[][] mCells; //[Y][X]
    //idée : faire une map <Position,Agent>

    public Environment(int width, int height, boolean toric) {
        mToric = toric;
        mWidth = width;
        mHeight = height;

        initCells();
    }

    protected void initCells() {
        mCells = new Cell[mHeight][mWidth];
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                mCells[y][x] = new Cell(null);
            }
        }
    }

    public Cell[][] getNeighbors(int posX, int posY) {
        Cell[][] neighbors = new Cell[3][3];

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                int neighborX = posX + x;
                int neighborY = posY + y;

                if (mToric) {
                    neighborX = Math.floorMod(neighborX, mWidth);
                    neighborY = Math.floorMod(neighborY, mHeight);
                    neighbors[y + 1][x + 1] = mCells[neighborY][neighborX];
                    continue;
                }

                if (neighborX < 0 || neighborX > mWidth - 1 || neighborY < 0 || neighborY > mHeight - 1) { // Border
                    neighbors[y + 1][x + 1] = null;
                    continue;
                }

                neighbors[y + 1][x + 1] = mCells[neighborY][neighborX];
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

    public void reset() {
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                mCells[y][x].setAgent(null);
            }
        }
    }

}
