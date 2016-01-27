package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Environment;

import java.awt.*;

public class Predator extends Agent {

    public Predator(Environment environment, int posX, int posY) {
        super(environment, posX, posY);
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public void doIt() {

    }
}
