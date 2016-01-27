package sommerard.dufaux.ball;

import sommerard.dufaux.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BallMAS extends MAS {

    private int mNbAgent;

    public BallMAS(View view) {
        super(view);
    }

    public void init(int nbTurn, int nbAgent, int width, int height, int speed, int agentSize, boolean equity, long seed, boolean toric) {
        mNbAgent = nbAgent;

        super.init(nbTurn, width, height, speed, agentSize, equity, seed, toric);
    }

    public void initAgents() {
        List<Position> positions = new ArrayList<Position>();

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                positions.add(new Position(x, y));
            }
        }

        Collections.shuffle(positions, mRandom);

        for(int i = 0; i < mNbAgent; i++) {
            if (positions.isEmpty()) {
                return;
            }

            Position position = positions.get(0);

            int posX = position.getX();
            int posY = position.getY();
            int dirX = mRandom.nextInt(2) - 1;
            int dirY = mRandom.nextInt(2) - 1;

            while(dirX == 0 && dirY == 0) {
                dirX = mRandom.nextInt(2) - 1;
                dirY = mRandom.nextInt(2) - 1;
            }

            Agent agent = new Ball(mEnvironment, posX, posY, dirX, dirY, Color.getColor(null, mRandom.nextInt()));
            mAgents.add(agent);
            mEnvironment.setAgent(posX, posY, agent);

            positions.remove(0);
        }
    }
}
