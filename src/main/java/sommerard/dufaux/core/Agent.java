package sommerard.dufaux.core;

import java.awt.Color;

public abstract class Agent {

    protected Color mColor;
    protected int mPosX;
    protected int mPosY;

    protected Environment mEnvironment;

    public Agent(Environment environment, int posX, int posY, Color color) {
        mColor = color;
        mPosX = posX;
        mPosY = posY;
        mEnvironment = environment;
    }

    public Color getColor() {
        return mColor;
    }

    public abstract void doIt();

    public int getPosX() {
        return mPosX;
    }

    public int getPosY() {
        return mPosY;
    }
}