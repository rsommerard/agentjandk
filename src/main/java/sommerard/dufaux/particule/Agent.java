package sommerard.dufaux.particule;

public class Agent {
	
	private int mPosX;
	private int mPosY;
    private int mStepX;
    private int mStepY;
	private Environment mEnvironment;
	
	public Agent(int posX, int posY, int stepX, int stepY, Environment environment) {
        System.out.println("Agent: [posX: " + posX + ", posY: " + posY + ", stepX: " + stepX + ", stepY: " + stepY + "]");

        mPosX = posX;
		mPosY = posY;
        mStepX = stepX;
        mStepY = stepY;
        mEnvironment = environment;
	}
	
	public void doIt(){
		int nextX = mPosX + mStepX;
		int nextY = mPosY + mStepY;

		if (mEnvironment.isBusy(nextX, nextY)) {
			mStepX = mStepX * (-1);
			mStepY = mStepY * (-1);

			nextX = mPosX + mStepX;
			nextY = mPosY + mStepY;

			if (mEnvironment.isBusy(nextX, nextY)) {
				// don't move
				nextX = mPosX;
				nextY = mPosY;
			}
		}

        mEnvironment.setState(mPosX, mPosY, false);
		mPosX = nextX;
		mPosY = nextY;
        mEnvironment.setState(mPosX, mPosY, true);
	}
}
