package sommerard.dufaux.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Canvas extends JPanel{
	
    private MAS mMas;
    private boolean mGrid;
    private int mAgentSize;
    private int mWidth;
    private int mHeight;
	
    public Canvas(int width, int height, int agentSize, boolean grid) {
        mWidth = width;
        mHeight = height;
        mGrid = grid;
        mAgentSize = agentSize;

        this.setSize(mWidth * mAgentSize, mHeight * mAgentSize);
    }
    
    
    public void paintComponent(Graphics g) {
    	//System.out.println("paint component");
    	if (mGrid) {
            paintGrid(g);
        }
        paintAgents(g);
    }
    

    private void paintAgents(Graphics graphics) {
        if (mMas == null) {
            return;
        }

        List<Agent> agents = new ArrayList<Agent>(mMas.getAgents());

        //System.out.println("paint "+agents.size()+" agents");
        for(Agent agent : agents) {
        	if(agent != null){
        		graphics.setColor(agent.getColor());
        		graphics.fillRect(agent.getPosX() * mAgentSize, (agent.getPosY() * mAgentSize), mAgentSize, mAgentSize);
        		//graphics.fillRoundRect(agent.getPosX() * mAgentSize, (agent.getPosY() * mAgentSize) + TOP_OFFSET, mAgentSize, mAgentSize);
        		//graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        	}
        }
        
    }

    private void paintGrid(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        for (int i = 0; i <= mHeight; i++) {
            graphics.drawLine(0, (i * mAgentSize), mWidth * mAgentSize, (i * mAgentSize));
        }

        for (int i = 0; i <= mWidth; i++) {
            graphics.drawLine((i * mAgentSize), 0, i * mAgentSize, (mHeight * mAgentSize));
        }
    }


	public void setMas(MAS mas) {
		this.mMas = mas;
	}
}
