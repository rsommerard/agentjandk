package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Canvas;

import java.awt.*;

public class PacmanCanvas extends Canvas {

    public PacmanCanvas(int width, int height, int agentSize, boolean grid) {
        super(width, height, agentSize, grid);
    }

    protected void paintAgents(Graphics graphics) {
        if (mMas == null) {
            return;
        }

        PacmanCell[][] cells = ((PacmanEnvironment)mMas.getEnvironment()).getCells();

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                Agent agent = cells[y][x].getAgent();
                if (agent != null) {
                    graphics.setColor(agent.getColor());
                    graphics.fillRect(x * mAgentSize, (y * mAgentSize), mAgentSize, mAgentSize);
                } else {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(String.valueOf(cells[y][x].getDijkstraValue()), x * mAgentSize, y * mAgentSize);
                }
            }
        }
    }
}
