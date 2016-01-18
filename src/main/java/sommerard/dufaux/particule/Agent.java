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

        // DEBUD printer
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {
                if (neighbors[y][x] == null) {
                    System.out.print("null ");
                } else {
                    System.out.print((neighbors[y][x].getAgent() != null) + " ");
                }
            }
            System.out.println();
        }

        Cell nextCell = neighbors[mDirY + 1][mDirX + 1];

        if (nextCell == null) {
            changeDir();
            // TODO: move or stay after inverse ?
        } else {
            if (nextCell.getAgent() == null) {
                moveAgent(mPosX + mDirX, mPosY + mDirY);
            } else {
                inverseDir();
            }
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

        */
	}

    private void moveAgent(int newPosX, int newPosY) {
        mEnvironment.setAgent(mPosX, mPosY, null);
        mPosX = newPosX;
        mPosY = newPosY;
        mEnvironment.setAgent(mPosX, mPosY, this);
    }

    private void changeDir() {

        System.out.println(mDirX + 1);
        System.out.println(mDirY + 1);

        // TOP Border
        if (mDirY + 1 == 0) {
            mDirY *= -1;
        }

        // BOTTOM Border
        if (mDirY + 1 == 2) {
            mDirY *= -1;
        }

        // LEFT Border
        if (mDirX + 1 == 0) {
            mDirX *= -1;
        }

        // RIGHT Border
        if (mDirX + 1 == 2) {
            mDirX *= -1;
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