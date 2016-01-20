package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.MAS;
import sommerard.dufaux.core.Position;
import sommerard.dufaux.core.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MASWator extends MAS {

    private int mNbShark;
    private int mNbFish;
    private int mFishBreed;
    private int mSharkBreed;
    private int mStarve;

    public MASWator(View view) {
        super(view);
    }

    @Override
    public void initAgents() {
        List<Position> positions = new ArrayList<Position>();

        for (int y = 0; y < mHeight; y++) {
            for (int x = 0; x < mWidth; x++) {
                positions.add(new Position(x, y));
            }
        }

        Collections.shuffle(positions, mRandom);

        for(int i = 0; i < mNbFish; i++) {
            if (positions.isEmpty()) {
                return;
            }

            Position position = positions.get(0);

            int posX = position.getX();
            int posY = position.getY();

            Agent agent = new Fish(mEnvironment, posX, posY, Color.PINK);
            mAgents.add(agent);
            mEnvironment.setAgent(posX, posY, agent);

            positions.remove(0);
        }

        for(int i = 0; i < mNbShark; i++) {
            if (positions.isEmpty()) {
                return;
            }

            Position position = positions.get(0);

            int posX = position.getX();
            int posY = position.getY();

            Agent agent = new Shark(mEnvironment, posX, posY, Color.BLUE);
            mAgents.add(agent);
            mEnvironment.setAgent(posX, posY, agent);

            positions.remove(0);
        }
    }

    public void init(int nbTurn, int nbShark, int nbFish, int fBreed, int sBreed, int starve, int width, int height, int speed, int agentSize, boolean equity, long seed, boolean toric) {
        mNbShark = nbShark;
        mNbFish = nbFish;
        mFishBreed = fBreed;
        mSharkBreed = sBreed;
        mStarve = starve;

        super.init(nbTurn, width, height, speed, agentSize, equity, seed, toric);
    }
}
