package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Environment;

import java.awt.*;

public class Rock extends Agent {

    public Rock(Environment environment, int posX, int posY) {
        super(environment, posX, posY);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public void doIt() {
        return;
    }
}
