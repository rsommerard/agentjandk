package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Environment;

import java.awt.*;
import java.util.Random;


public class Shark extends Agent {

    private Random mRandom;

	public Shark(Environment environment, int posX, int posY, Color color, Random random) {
        super(environment, posX, posY, color);
        mRandom = random;
    }

    @Override
    public void doIt() {

    }
}
