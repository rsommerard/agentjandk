package sommerard.dufaux.pacman;

import java.awt.event.KeyAdapter;

import org.apache.commons.cli.*;
import sommerard.dufaux.core.View;

public class PacmanMain {

    public static final int NB_PREDATOR = 3;
    public static final int NB_ROCK = 300;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
   public static final int AGENT_SIZE =  14;
    public static final int SPEED = 100;
    public static final long SEED = 0;
    public static final boolean GRID = true;
    public static final boolean EQUITY = true;


    public static void main(String[] args) throws InterruptedException, ParseException {
        Options options = new Options();
        options.addOption("nbPredator", true, "predator number");
        options.addOption("nbRock", true, "rock number");
        options.addOption("width", true, "environment width");
        options.addOption("height", true, "environment height");
        options.addOption("agentSize", true, "agent size");
        options.addOption("speed", true, "speed by turn (ms)");
        options.addOption("seed", true, "generation seed (long)");
        options.addOption("grid", "enable grid");
        options.addOption("equity", "enable equity");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        int nbPredator = NB_PREDATOR;

        if (cmd.getOptionValue("nbPredator") != null) {
            nbPredator = Integer.parseInt(cmd.getOptionValue("nbPredator"));
        }

        int nbRock = NB_ROCK;

        if (cmd.getOptionValue("nbRock") != null) {
            nbRock = Integer.parseInt(cmd.getOptionValue("nbRock"));
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
            int tmp = Integer.parseInt(cmd.getOptionValue("speed"));

            if (tmp > 10) {
                speed = tmp;
            }
        }

        long seed = SEED;

        if (cmd.getOptionValue("seed") != null) {
            seed = Long.parseLong(cmd.getOptionValue("seed"));
        }

        boolean grid = GRID;

        if (cmd.hasOption("grid")) {
            grid = true;
        }

        boolean equity = EQUITY;

        if (cmd.hasOption("equity")) {
            equity = true;
        }

        View view = new PacmanView(width, height, agentSize, grid);
        KeyboardListener keyAdaptator = new KeyboardListener();
        view.addKeyListener(keyAdaptator);
        PacmanMAS pacmanMAS = new PacmanMAS(keyAdaptator);
        
        
        pacmanMAS.addObserver(view);
        pacmanMAS.init(nbPredator, nbRock, width, height, speed, agentSize, equity, seed);
        pacmanMAS.run();

    }
}