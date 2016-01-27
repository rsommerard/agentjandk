package sommerard.dufaux.core;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class View extends JFrame implements Observer {

    private MAS mMas;
    private boolean mGrid;
    private int mAgentSize;
    private int mWidth;
    private int mHeight;
    private Canvas mCanvas;
    
    
    private static final int TOP_OFFSET = 20;
    //private static final int TOP_OFFSET = 22;

    public View(int width, int height, int agentSize, boolean grid) {
        setTitle("MAS");

        mWidth = width;
        mHeight = height;
        mGrid = grid;
        mAgentSize = agentSize;

        mCanvas = new Canvas(width, height, agentSize, grid);
        this.setLayout(new BorderLayout());
        this.add(mCanvas, BorderLayout.CENTER);
        setSize(mWidth * mAgentSize + 2, mHeight * mAgentSize + TOP_OFFSET);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void update(Observable o, Object arg) {    	
    	//System.out.println("UPDATE VIEW");
        mMas = (MAS) arg;
        mCanvas.setMas(mMas);
        repaint();
        //paint(getGraphics());
		// setVisible(true);
	}
}
