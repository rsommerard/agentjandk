package sommerard.dufaux.wator;

import org.apache.commons.cli.*;
import sommerard.dufaux.core.View;

public class MainWator {

	//super parametre = nb_shark = 150, nb_fish = 400, fish_breed = 4, shark_breed = 10,
	// starve = 3, with = 50, height = 50, seed = 0, equity = true, toric = false.
    public static final int NB_SHARK = 150;
    public static final int NB_FISH = 400;
    public static final int FISH_BREED = 4;
    public static final int SHARK_BREED = 10;
    public static final int STARVE = 3;
    public static final int NB_TURN = 10000;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int AGENT_SIZE =  15;
    public static final int SPEED = 50;
    public static final long SEED = 0;
    public static final boolean GRID = true;
    public static final boolean EQUITY = true;
    public static final boolean TORIC = false;


    public static void main(String[] args) throws InterruptedException, ParseException {
        Options options = new Options();
        options.addOption("nbShark", true, "shark number");
        options.addOption("nbFish", true, "fish number");
        options.addOption("fBreed", true, "number of cycles a fish must exist before reprodicing");
        options.addOption("sBreed", true, "number of cycles a shark must exist before reprodicing");
        options.addOption("starve", true, "number of cycles a shark has to find food before starving");
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

        int nbShark = NB_SHARK;

        if (cmd.getOptionValue("nbShark") != null) {
            nbShark = Integer.parseInt(cmd.getOptionValue("nbShark"));
        }

        int nbFish = NB_FISH;

        if (cmd.getOptionValue("nbFish") != null) {
            nbFish = Integer.parseInt(cmd.getOptionValue("nbFish"));
        }

        int fBreed = FISH_BREED;

        if (cmd.getOptionValue("fBreed") != null) {
            fBreed = Integer.parseInt(cmd.getOptionValue("fBreed"));
        }

        int sBreed = SHARK_BREED;

        if (cmd.getOptionValue("sBreed") != null) {
            sBreed = Integer.parseInt(cmd.getOptionValue("sBreed"));
        }

        int starve = STARVE;

        if (cmd.getOptionValue("starve") != null) {
            starve = Integer.parseInt(cmd.getOptionValue("starve"));
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

        boolean toric = TORIC;

        if (cmd.hasOption("toric")) {
            toric = true;
        }

        View view = new View(width, height, agentSize, grid);
        MASWator masWator = new MASWator(view);
        masWator.init(nbTurn, nbShark, nbFish, fBreed, sBreed, starve, width, height, speed, agentSize, equity, seed, toric);
        masWator.run();
    }
}