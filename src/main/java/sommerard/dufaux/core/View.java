package sommerard.dufaux.core;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class View extends JFrame implements Observer {

    private MAS mMas;
    protected boolean mGrid;
    protected int mAgentSize;
    protected int mWidth;
    protected int mHeight;
    protected Canvas mCanvas;

    private static final int TOP_OFFSET = 21;

    public View(int width, int height, int agentSize, boolean grid) {
        setTitle("MAS");

        mWidth = width;
        mHeight = height;
        mGrid = grid;
        mAgentSize = agentSize;
    }

    public void init() {
        createCanvas();

        this.setLayout(new BorderLayout());
        this.add(mCanvas, BorderLayout.CENTER);
        setSize(mWidth * mAgentSize + 2, mHeight * mAgentSize + TOP_OFFSET);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    protected void createCanvas() {
        mCanvas = new Canvas(mWidth, mHeight, mAgentSize, mGrid);
    }

    public void update(Observable o, Object arg) {

        mMas = (MAS) arg;
        mCanvas.setMas(mMas);
        repaint();
        setVisible(true);
    }
}
