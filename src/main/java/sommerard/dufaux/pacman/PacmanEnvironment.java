package sommerard.dufaux.pacman;

import java.util.LinkedList;

import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

public class PacmanEnvironment extends Environment {

    private boolean mFinish;

    public PacmanEnvironment(int width, int height, boolean toric) {
        super(width, height, toric);
    }


    public void resetDikjstra() {
        PacmanCell[][] cells = (PacmanCell[][]) mCells; //need to cast
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                cells[y][x].setDijkstraValue(0);
            }
        }
    }

    public void calculateDikjstra(int posX, int posY) {

        LinkedList<Position> modifiedCells = new LinkedList<Position>();
        int globalX, globalY;
        int pX = posX;
        int pY = posY;
        do {
            PacmanCell[][] cells = getPacmanNeighbors(pX, pY);
            int currentDistance = cells[1][1].getDijkstraValue() + 1;

            for (int y = 0; y <= 2; y++) {
                for (int x = 0; x <= 2; x++) {
                    if (cells[y][x] != null && //cells[y][x].getAgent() == null &&
                            (cells[y][x].getDijkstraValue() == 0 || cells[y][x].getDijkstraValue() > currentDistance)) {
                        cells[y][x].setDijkstraValue(currentDistance);
                        globalX = x + pX - 1; //need to set globalPos because we work with local coord (neighbors)
                        globalY = y + pY - 1;

                        //(we need to modify neighbors only if the case is empty (not agent)){
                        if (cells[y][x].getAgent() == null) {
                            modifiedCells.addLast(new Position(globalX, globalY));
                        }
                    }
                }
            }

            Position pos = modifiedCells.removeFirst();
            pX = pos.getX();
            pY = pos.getY();
        }
        while (modifiedCells.size() > 0);

    }

    public PacmanCell[][] getPacmanNeighbors(int posX, int posY) {
        PacmanCell[][] neighbors = new PacmanCell[3][3];

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                int neighborX = posX + x;
                int neighborY = posY + y;

                if (mToric) {
                    neighborX = Math.floorMod(neighborX, mWidth);
                    neighborY = Math.floorMod(neighborY, mHeight);
                    neighbors[y + 1][x + 1] = (PacmanCell) mCells[neighborY][neighborX];
                    continue;
                }

                if (neighborX < 0 || neighborX > mWidth - 1 || neighborY < 0 || neighborY > mHeight - 1) { // Border
                    neighbors[y + 1][x + 1] = null;
                    continue;
                }

                neighbors[y + 1][x + 1] = (PacmanCell) mCells[neighborY][neighborX];
            }
        }

        return neighbors;
    }

    public PacmanCell[][] getCells() {
        return (PacmanCell[][]) mCells;
    }

    protected void initCells() {
        mCells = new PacmanCell[mHeight][mWidth];
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                mCells[y][x] = new PacmanCell(null);
            }
        }
    }

    public void setFinish(boolean end) {
        mFinish = end;

    }

    public boolean getFinish() {
        return this.mFinish;
    }
}
