package sommerard.dufaux.pacman;

import sommerard.dufaux.core.Agent;
import sommerard.dufaux.core.Cell;

public class PacmanCell extends Cell {

    private int mDijkstraValue;

    public PacmanCell(Agent agent) {
        super(agent);
    }

    public int getDijkstraValue() {
        return mDijkstraValue;
    }

    public void setDijkstraValue(int dijkstraValue) {
        mDijkstraValue = dijkstraValue;
    }


}
