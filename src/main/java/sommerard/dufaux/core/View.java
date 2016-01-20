package sommerard.dufaux.core;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private MAS mMas;
    private boolean mGrid;
    private int mAgentSize;
    private int mWidth;
    private int mHeight;

    private static final int TOP_OFFSET = 22;

    public View(int width, int height, int agentSize, boolean grid) {
        setTitle("MAS");

        mWidth = width;
        mHeight = height;
        mGrid = grid;
        mAgentSize = agentSize;

        setSize(mWidth * mAgentSize, mHeight * mAgentSize + TOP_OFFSET);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (mGrid) {
            paintGrid(graphics);
        }

        paintAgents(graphics);
    }

    private void paintAgents(Graphics graphics) {
        if (mMas == null) {
            return;
        }

        Environment environment = mMas.getEnvironment();

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                if (environment.getAgent(x, y) != null) {
                    Agent agent = environment.getAgent(x, y);
                    graphics.setColor(agent.getColor());
                    graphics.fillRect(x * mAgentSize, (y * mAgentSize) + TOP_OFFSET, mAgentSize, mAgentSize);
                }
            }
        }
    }

    private void paintGrid(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        for (int i = 0; i <= mHeight; i++) {
            graphics.drawLine(0, (i * mAgentSize) + TOP_OFFSET, mWidth * mAgentSize, (i * mAgentSize) + TOP_OFFSET);
        }

        for (int i = 0; i <= mWidth; i++) {
            graphics.drawLine((i * mAgentSize), TOP_OFFSET, i * mAgentSize, (mHeight * mAgentSize) + TOP_OFFSET);
        }
    }

    public void update(Observable o, Object arg) {
        mMas = (MAS) arg;
        paint(getGraphics());
        setVisible(true);
    }
}
