package sommerard.dufaux.particule;

public class Environment {

    private int mAgentSize;
    private int mWidth;
	private int mHeight;
	private boolean[][] mSpace;

	public Environment(int width, int height, int agentSize) {
        mAgentSize = agentSize;
		mWidth = width;
		mHeight = height;
		mSpace = new boolean[height][width];
	}

    public int getAgentSize() {
        return mAgentSize;
    }

	public boolean isBusy(int posX, int posY){
		if (posX < 0) {
			return true;
		}

		if (posX > mWidth - 1) {
            return true;
        }

        if (posY < 0 ) {
            return true;
        }

        if (posY > mHeight - 1) {
            return true;
        }

        return mSpace[posY][posX];
	}

    public void setState(int posX, int posY, boolean state) {
        mSpace[posY][posX] = state;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
