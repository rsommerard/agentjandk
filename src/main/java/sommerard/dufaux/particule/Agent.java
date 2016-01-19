package sommerard.dufaux.particule;

import java.awt.Color;

public class Agent {

    private Color mColor;
	private int mPosX;
	private int mPosY;
    private int mDirX;
    private int mDirY;
	private Environment mEnvironment;

    public Agent(Environment environment, int posX, int posY, int dirX, int dirY, Color color) {
        mColor = color;
        mPosX = posX;
		mPosY = posY;
		mDirX = dirX;
		mDirY = dirY;
        mEnvironment = environment;
	}

	public void doIt(){
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
        mPosX = mPosX + mDirX;
        mPosY = mPosY + mDirY;
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

    public Color getColor() {
        return mColor;
    }
}