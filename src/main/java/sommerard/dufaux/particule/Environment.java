package sommerard.dufaux.particule;

public class Environment {

    private int mAgentSize;
    private int mWidth;
	private int mHeight;
	private Agent[][] mSpace;

	public Environment(int width, int height, int agentSize) {
        mAgentSize = agentSize;
		mWidth = width;
		mHeight = height;
		mSpace = new Agent[height][width];
	}

    public int getAgentSize() {
        return mAgentSize;
    }

    public boolean isCorner(int posX, int posY) {
        if (posX == 0 && posY == 0) {
            return true;
        } else if (posX == 0 && posY == mHeight - 1) {
            return true;
        } else if (posX == mWidth - 1 && posY == 0) {
            return true;
        } else if (posX == mWidth - 1 && posY == mHeight - 1) {
            return true;
        } else {
            return false;
        }
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

        return mSpace[posY][posX] != null;
	}

    public void setAgent(int posX, int posY, Agent agent) {
        mSpace[posY][posX] = agent;
    }

    public Agent getAgent(int posX, int posY) {
        return mSpace[posY][posX];
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
