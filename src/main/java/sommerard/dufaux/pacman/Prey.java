package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Environment;

import java.awt.*;

public class Prey extends Agent {

    private int mDirX;
    private int mDirY;

    public Prey(Environment environment, int posX, int posY) {
        super(environment, posX, posY);
        mDirX = 0;
        mDirY = 0;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public void doIt() {

    }
}
