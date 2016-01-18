package sommerard.dufaux.particule;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private MAS mMas;
    private boolean mGridEnable;
    private int mAgentSize;

    private static final int TOP_OFFSET = 22;

    public View(int width, int height, int agentSize, boolean gridEnable) {
        setTitle("Particule chamber");

        mGridEnable = gridEnable;
        mAgentSize = agentSize;

        setSize(width * agentSize, (height * agentSize) + TOP_OFFSET);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        paintGrid(graphics);

        paintAgents(graphics);
    }

    private void paintAgents(Graphics graphics) {
        Environment environment = mMas.getEnvironment();

        int width = environment.getWidth();
        int height = environment.getHeight();

        int agentSize = mAgentSize;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (environment.getAgent(x, y) != null) {
                    Agent agent = environment.getAgent(x, y);
                    graphics.setColor(agent.getColor());
                    graphics.fillRect(x * agentSize, (y * agentSize) + TOP_OFFSET, agentSize, agentSize);
                }
            }
        }
    }

    private void paintGrid(Graphics graphics) {
        Environment environment = mMas.getEnvironment();

        int width = environment.getWidth();
        int height = environment.getHeight();

        int agentSize = mAgentSize;

        if (mGridEnable) {
            graphics.setColor(Color.BLACK);

            for (int i = 0; i <= height; i++) {
                graphics.drawLine(0, (i * agentSize) + TOP_OFFSET, width * agentSize, (i * agentSize) + TOP_OFFSET);
            }

            for (int i = 0; i <= width; i++) {
                graphics.drawLine((i * agentSize), TOP_OFFSET, i * agentSize, (height * agentSize) + TOP_OFFSET);
            }
        }
    }

    public void update(Observable o, Object arg) {
        mMas = (MAS) arg;
        paint(getGraphics());
        setVisible(true);
    }
}
