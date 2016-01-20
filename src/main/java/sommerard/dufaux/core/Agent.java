package sommerard.dufaux.core;

import java.awt.Color;

public abstract class Agent {

    protected Color mColor;
    protected int mPosX;
    protected int mPosY;
    protected int mDirX;
    protected int mDirY;
    protected Environment mEnvironment;

    public Agent(Environment environment, int posX, int posY, int dirX, int dirY, Color color) {
        mColor = color;
        mPosX = posX;
        mPosY = posY;
        mDirX = dirX;
        mDirY = dirY;
        mEnvironment = environment;
    }

    public Color getColor() {
        return mColor;
    }

    public abstract void doIt();

}