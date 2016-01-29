package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;

public class PacmanEnvironment extends Environment {

    public PacmanEnvironment(int width, int height, boolean toric) {
        super(width, height, toric);
    }

    public void calculateDikjstra(int posX, int posY) {
        Cell[][] cells = getNeighbors(posX, posY);
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
            }
        }
    }

    public PacmanCell[][] getCells() {
        return (PacmanCell[][])mCells;
    }

    protected void initCells() {
        mCells = new PacmanCell[mHeight][mWidth];
        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                mCells[y][x] = new PacmanCell(null);
            }
        }
    }
}
