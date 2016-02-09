package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Prey extends Agent {

    private int mDirX;
    private int mDirY;

    public Prey(Environment environment, int posX, int posY, int speedRatio) {
        super(environment, posX, posY, speedRatio);
        mDirX = 0;
        mDirY = 0;
        ((PacmanEnvironment) mEnvironment).calculateDikjstra(mPosX, mPosY);
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    public void doIt() {
        if (((PacmanEnvironment) mEnvironment).getFinish()) {
            return;
        }

        Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

        Cell nextCell = neighbors[mDirY + 1][mDirX + 1];

        if (nextCell == null) {
            return; //do not move
        } else {
            if (nextCell.getAgent() == null) {
                moveAgent(neighbors);
                ((PacmanEnvironment) mEnvironment).resetDikjstra();
                ((PacmanEnvironment) mEnvironment).calculateDikjstra(mPosX, mPosY);
            }
        }
    }

    private void moveAgent(Cell[][] neighbors) {
        neighbors[1][1].setAgent(null);
        neighbors[mDirY + 1][mDirX + 1].setAgent(this);
        mPosX = mPosX + mDirX;
        mPosY = mPosY + mDirY;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) {
            mDirX = -1;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            mDirX = 1;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_Z) {
            mDirY = -1;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            mDirY = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) {
            mDirX = 0;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            mDirX = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_Z) {
            mDirY = 0;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            mDirY = 0;
        }
    }
}
