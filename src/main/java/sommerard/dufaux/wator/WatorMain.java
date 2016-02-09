package sommerard.dufaux.wator;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.cli.*;
import sommerard.dufaux.core.View;

public class WatorMain {

    //super parametre = nb_shark = 500, nb_fish = 500, fish_breed = 4, shark_breed = 10,
    // starve = 5, with = 120, height = 120, seed = 0, equity = true, toric = true.
    public static final int NB_SHARK = 500;
    public static final int NB_FISH = 500;
    public static final int FISH_BREED = 1;
    public static final int SHARK_BREED = 15;
    public static final int STARVE = 10;
    public static final int NB_TURN = 1000000;
    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    public static final int AGENT_SIZE = 5;
    public static final int SPEED = 5;
    public static final long SEED = 0;
    public static final boolean GRID = false;
    public static final boolean EQUITY = true;
    public static final boolean TORIC = true;


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
        options.addOption("noToric", "enable toric environment");

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
            speed = Integer.parseInt(cmd.getOptionValue("speed"));
            
            if (speed < 0) {
                speed = 0;
            }
        }
        

        long seed = SEED;

        if (cmd.getOptionValue("seed") != null) {
            seed = Long.parseLong(cmd.getOptionValue("seed"));
        	if(Integer.parseInt(cmd.getOptionValue("seed")) == 42) {
        		seed = new Random().nextLong();
        	}
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

        if (cmd.hasOption("noToric")) {
            toric = false;
        }

        View view = new View(width, height, agentSize, grid);
        view.init();
        CsvView stats = null;
        try {
            stats = new CsvView();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WatorMAS watorMAS = new WatorMAS();
        watorMAS.addObserver(view);
        watorMAS.addObserver(stats);
        watorMAS.init(nbTurn, nbShark, nbFish, fBreed, sBreed, starve, width, height, speed, agentSize, equity, seed, toric);
        watorMAS.run();

        try {
            stats.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(1);
    }
}