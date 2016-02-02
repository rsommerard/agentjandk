package sommerard.dufaux.core;

import java.awt.Color;

public abstract class Agent {

    protected int mPosX;
    protected int mPosY;

    protected Environment mEnvironment;

    public Agent(Environment environment, int posX, int posY) {
        mPosX = posX;
        mPosY = posY;
        mEnvironment = environment;
    }

    public abstract Color getColor();

    public abstract void doIt();

    public int getPosX() {
        return mPosX;
    }

    public int getPosY() {
        return mPosY;
    }
    
    @Override
    public String toString(){
    	return this.getClass().getName()+" ["+mPosY+"]["+mPosX+"]";
    }
}