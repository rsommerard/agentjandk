package sommerard.dufaux.core;

public class Cell {

    private Agent mAgent;

    public Cell(Agent a) {
        mAgent = a;
    }

    public void setAgent(Agent a) {
        mAgent = a;
    }

    public Agent getAgent() {
        return mAgent;
    }
}
