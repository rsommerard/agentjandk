package sommerard.dufaux.ball;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;

import java.awt.*;

public class Ball extends Agent {

    private int mDirX;
    private int mDirY;
    private Color mColor;

    public Ball(Environment environment, int posX, int posY, int dirX, int dirY, Color color) {
        super(environment, posX, posY);
        mDirX = dirX;
        mDirY = dirY;
        mColor = color;
    }

    @Override
    public Color getColor() {
        return mColor;
    }

    public void doIt() {
        Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

        Cell nextCell = neighbors[mDirY + 1][mDirX + 1];

        if (nextCell == null) {
            changeDir(neighbors);
        } else {
            if (nextCell.getAgent() == null) {
                moveAgent(neighbors);
            } else {
                inverseDir();
            }
        }
    }

    private void moveAgent(Cell[][] neighbors) {
        neighbors[1][1].setAgent(null);
        neighbors[mDirY + 1][mDirX + 1].setAgent(this);
        mPosX = Math.floorMod((mPosX + mDirX) , mEnvironment.getWidth());
        mPosY = Math.floorMod((mPosY + mDirY) , mEnvironment.getHeight());
    }

    private void changeDir(Cell[][] neighbors) {
        // Corner
        if ((neighbors[1][0] == null && neighbors[0][1] == null) ||
                (neighbors[0][1] == null && neighbors[1][2] == null) ||
                (neighbors[1][0] == null && neighbors[2][1] == null) ||
                (neighbors[1][2] == null && neighbors[2][1] == null)) {
            inverseDir();
            return;
        }

        if (mDirX != 0 && mDirY == 0) {
            mDirX *= -1;
            return;
        }

        if (mDirX == 0 && mDirY != 0) {
            mDirY *= -1;
            return;
        }

        if (mDirX == -1 && mDirY == -1) {
            if (neighbors[0][1] == null) {
                mDirY *= -1;
            } else {
                mDirX *= -1;
            }

            return;
        }

        if (mDirX == -1 && mDirY == 1) {
            if (neighbors[1][0] == null) {
                mDirX *= -1;
            } else {
                mDirY *= -1;
            }

            return;
        }

        if (mDirX == 1 && mDirY == 1) {
            if (neighbors[2][1] == null) {
                mDirY *= -1;
            } else {
                mDirX *= -1;
            }

            return;
        }

        if (mDirX == 1 && mDirY == -1) {
            if (neighbors[0][1] == null) {
                mDirY *= -1;
            } else {
                mDirX *= -1;
            }
        }
    }

    private void inverseDir() {
        mDirX *= -1;
        mDirY *= -1;
    }
}
