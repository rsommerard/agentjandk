package sommerard.dufaux.wator;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;
import sommerard.dufaux.core.Environment;
import sommerard.dufaux.core.Position;

import java.awt.*;
import java.util.Random;

public class Fish extends Animal {

    private static int breed; //can't be final static because initialize at the execution

    public Fish(WatorMAS mas, Environment environment, int posX, int posY, Random random, boolean randomState) {
        super(mas, environment, posX, posY, random);
        if (randomState) {
            mBreed = random.nextInt(breed);
            return;
        }
        mBreed = 0;
    }

    public Fish(WatorMAS mas, Environment environment, int posX, int posY, Random random) {
        super(mas, environment, posX, posY, random);
        mBreed = 0;
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }

    @Override
    public void doIt() {
        if (!mAlive)
            return;

        mBreed++;
        mAge++;

        Cell[][] neighbors = mEnvironment.getNeighbors(mPosX, mPosY);

        if (isFullNeighborhood(neighbors)) {
            return; //do not move
        }


        if (mBreed >= breed) {
            breed(neighbors);
            mBreed = 0;
        }

        moveRandom(neighbors);

    }

    private void breed(Cell[][] neighbors) {
        Position pos = randomEmptyCell(neighbors);
        if (pos != null) {
            Agent fish = this.mMas.createFish((mPosX + pos.getX() - 1), (mPosY + pos.getY() - 1)); //affect mas
            neighbors[pos.getY()][pos.getX()].setAgent(fish); //affect environment
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + Integer.toHexString(hashCode()) + " [" + mPosY + "][" + mPosX + "] breed = " + mBreed + ", age =" + mAge;
    }

    public static void setGlobalBreed(int breed) {
        Fish.breed = breed;
    }
}
