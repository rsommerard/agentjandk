package sommerard.dufaux.particule;

import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class GraphicView extends JFrame implements View {

    private MAS mMas;

    private static final int TOP_OFFSET = 22;

    public GraphicView(int width, int height, int agentSize) {
        setTitle("Particule chamber");
        setSize(width * agentSize, (height * agentSize) + TOP_OFFSET);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        //graphics.setColor(Color.RED);
        //graphics.fillRect(0, 299 + TOP_OFFSET, 1, 1);
        //graphics.fillRect(299, 0 + TOP_OFFSET, 1, 1);

        // left
        /*graphics.fillRect(0, 0 + TOP_OFFSET, 1, 299);
        // right
        graphics.fillRect(299, 0 + TOP_OFFSET, 1, 299);


        graphics.setColor(Color.RED);
        // top
        graphics.fillRect(0, 0 + TOP_OFFSET, 299, 1);
        // bottom
        graphics.fillRect(0, 299 + TOP_OFFSET, 299, 1);*/

        Environment environment = mMas.getEnvironment();
        int width = environment.getWidth();
        int height = environment.getHeight();
        int agentSize = environment.getAgentSize();

        graphics.setColor(Color.RED);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (environment.isBusy(j, i)) {
                    System.out.println("print: [x: " + (j * agentSize) + ", y: " + (i * agentSize) + "]");
                    graphics.fillRect(j * agentSize, (i * agentSize) + TOP_OFFSET, agentSize, agentSize);
                }
            }
        }
    }

    public void drawGrid(int envSize, int agentSize){
        Line line;
        for(int i=0;i<envSize;i++){
            //USELESS IN TEXTUAL VIEW
        }
        for(int i=0;i<envSize;i++){
            //USELESS IN TEXTUAL VIEW
        }
    }

    public void update(Observable o, Object arg) {
        System.out.println("update");
        mMas = (MAS) arg;
        paint(getGraphics());
    }
}
