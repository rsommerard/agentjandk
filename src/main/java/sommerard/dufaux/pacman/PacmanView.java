package sommerard.dufaux.pacman;

import sommerard.dufaux.core.View;

public class PacmanView extends View {

    boolean mDijkstra;

    public PacmanView(int width, int height, int agentSize, boolean grid, boolean dijkstra) {
        super(width, height, agentSize, grid);
        mDijkstra = dijkstra;
    }

    @Override
    protected void createCanvas() {
        mCanvas = new PacmanCanvas(mWidth, mHeight, mAgentSize, mGrid, mDijkstra);
    }

}
