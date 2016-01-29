package sommerard.dufaux.pacman;

import sommerard.dufaux.core.View;

public class PacmanView extends View {

    public PacmanView(int width, int height, int agentSize, boolean grid) {
        super(width, height, agentSize, grid);
    }

    @Override
    protected void createCanvas() {
        mCanvas = new PacmanCanvas(mWidth, mHeight, mAgentSize, mGrid);
    }
}
