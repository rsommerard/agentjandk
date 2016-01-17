package sommerard.dufaux.particule;

import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class GraphicView extends JFrame implements View {

    private MAS mMas;
    private boolean mGridEnable;

    private static final int TOP_OFFSET = 22;

    public GraphicView(int width, int height, int agentSize, boolean gridEnable) {
        setTitle("Particule chamber");

        mGridEnable = gridEnable;

        setSize(width * agentSize, (height * agentSize) + TOP_OFFSET);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        Environment environment = mMas.getEnvironment();
        int width = environment.getWidth();
        int height = environment.getHeight();
        int agentSize = environment.getAgentSize();

        if (mGridEnable) {
            graphics.setColor(Color.BLACK);

            for (int i = 0; i <= height; i++) {
                graphics.drawLine(0, (i * agentSize) + TOP_OFFSET, width * agentSize, (i * agentSize) + TOP_OFFSET);
            }

            for (int i = 0; i <= width; i++) {
                graphics.drawLine((i * agentSize), 0 + TOP_OFFSET, i * agentSize, (height * agentSize) + TOP_OFFSET);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (environment.isBusy(j, i)) {
                    Agent agent = environment.getAgent(j, i);
                    graphics.setColor(agent.getColor());
                    System.out.println("print: [x: " + (j * agentSize) + ", y: " + (i * agentSize) + "]");
                    graphics.fillRect(j * agentSize, (i * agentSize) + TOP_OFFSET, agentSize, agentSize);
                }
            }
        }
    }

    public void update(Observable o, Object arg) {
        System.out.println("update");
        mMas = (MAS) arg;
        paint(getGraphics());
    }
}
