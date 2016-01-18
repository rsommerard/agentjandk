package sommerard.dufaux.particule;

import java.awt.*;

public class Agent {

    private String mName;
    //private Color mColor;
	private int mPosX;
	private int mPosY;
    private int mDirX;
    private int mDirY;
	private Environment mEnvironment;

	//public Agent(String name, int posX, int posY, int stepX, int stepY, Environment environment, Color color) {
        //System.out.println("Agent: [name: " + name + ", posX: " + posX + ", posY: " + posY + ", stepX: " + stepX + ", stepY: " + stepY + "]");
    public Agent(int posX, int posY, int stepX, int stepY, Environment environment) {
        //mName = name;
        //mColor = color;
        mPosX = posX;
		mPosY = posY;
        setDirection(stepX, stepY);
        mEnvironment = environment;
	}

    public void setDirection(int stepX, int stepY) {
        mDirX = stepX;
        mDirY = stepY;

        System.out.println(mName + " direction changed.");
    }
	
	public void doIt(){
        int rightLimit = mEnvironment.getWidth() - 1;
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
        mEnvironment.setAgent(mPosX, mPosY, this);
	}

    private void inverseDirection() {
        mDirX *= -1;
        mDirY *= -1;
    }

    /*public Color getColor() {
        return mColor;
    }*/
}