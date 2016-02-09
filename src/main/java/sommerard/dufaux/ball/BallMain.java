package sommerard.dufaux.ball;

import java.util.Random;

import org.apache.commons.cli.*;
import sommerard.dufaux.core.View;

public class BallMain {

    public static final int NB_AGENT = 2;
    public static final int NB_TURN = 1000000;
    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    public static final int AGENT_SIZE = 5;
    public static final int SPEED = 50;
    public static final long SEED = 0;
    public static final boolean GRID = false;
    public static final boolean EQUITY = false;
    public static final boolean TORIC = true;


    public static void main(String[] args) throws InterruptedException, ParseException {
        Options options = new Options();
        options.addOption("nbAgent", true, "agent number");
        options.addOption("nbTurn", true, "turn number");
        options.addOption("width", true, "environment width");
        options.addOption("height", true, "environment height");
        options.addOption("agentSize", true, "agent size");
        options.addOption("speed", true, "speed by turn (ms)");
        options.addOption("seed", true, "generation seed (long)");
        options.addOption("grid", "enable grid");
        options.addOption("equity", "enable equity");
        options.addOption("toric", "enable toric environment");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        int nbAgent = NB_AGENT;

        if (cmd.getOptionValue("nbAgent") != null) {
            nbAgent = Integer.parseInt(cmd.getOptionValue("nbAgent"));
        }

        int nbTurn = NB_TURN;

        if (cmd.getOptionValue("nbTurn") != null) {
            nbTurn = Integer.parseInt(cmd.getOptionValue("nbTurn"));
        }

        int width = WIDTH;

        if (cmd.getOptionValue("width") != null) {
            int tmp = Integer.parseInt(cmd.getOptionValue("width"));

            if (tmp > 0) {
                width = tmp;
            }
        }

        int height = HEIGHT;

        if (cmd.getOptionValue("height") != null) {
            int tmp = Integer.parseInt(cmd.getOptionValue("height"));

            if (tmp > 0) {
                height = tmp;
            }
        }

        int agentSize = AGENT_SIZE;

        if (cmd.getOptionValue("agentSize") != null) {
            int tmp = Integer.parseInt(cmd.getOptionValue("agentSize"));

            if (tmp > 0) {
                agentSize = tmp;
            }
        }

        int speed = SPEED;

        if (cmd.getOptionValue("speed") != null) {
            speed = Integer.parseInt(cmd.getOptionValue("speed"));
            
            if (speed < 0) {
                speed = 0;
            }
        }

        long seed = SEED;

        if (cmd.getOptionValue("seed") != null) {
            seed = Long.parseLong(cmd.getOptionValue("seed"));
        } else {
            seed = new Random().nextLong();
        }

        boolean grid = GRID;

        if (cmd.hasOption("grid")) {
            grid = true;
        }

        boolean equity = EQUITY;

        if (cmd.hasOption("equity")) {
            equity = true;
        }

        boolean toric = TORIC;

        if (cmd.hasOption("toric")) {
            toric = true;
        }

        View view = new View(width, height, agentSize, grid);
        view.init();
        BallMAS ballMAS = new BallMAS();
        ballMAS.addObserver(view);
        ballMAS.init(nbTurn, nbAgent, width, height, speed, agentSize, equity, seed, toric);
        ballMAS.run();
    }
}