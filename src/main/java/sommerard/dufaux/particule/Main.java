package sommerard.dufaux.particule;

import org.apache.commons.cli.*;

public class Main {

	public static final int NB_AGENT = 25;
	public static final int NB_TURN = 1000000;
	public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
	public static final int AGENT_SIZE = 10;
	public static final int SPEED = 100;
	public static final long SEED = 0;
	public static final boolean GRID = true;
	public static final boolean EQUITY = false;
	public static final boolean TORIC = false;
	
	
    public static void main(String[] args) throws InterruptedException, ParseException {
    	Options options = new Options();
		options.addOption("nbAgent", true, "agent number");
		options.addOption("nbTurn", true, "turn number");
		options.addOption("width", true, "environment width");
		options.addOption("height", true, "environment height");
		options.addOption("agentSize", true, "agent size");
		options.addOption("speed", true, "speed by turn (ms)");
		options.addOption("seed", true, "generation seed");
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

		if (cmd.getOptionValue("grid") != null) {
			grid = Boolean.parseBoolean(cmd.getOptionValue("grid"));
		}

		boolean equity = EQUITY;

		if (cmd.getOptionValue("equity") != null) {
			equity = Boolean.parseBoolean(cmd.getOptionValue("equity"));
		}

		boolean toric = TORIC;

		if (cmd.getOptionValue("toric") != null) {
			toric = Boolean.parseBoolean(cmd.getOptionValue("toric"));
		}

		View view = new View(width, height, agentSize, grid);
		MAS MAS = new MAS(view);
		MAS.init(nbTurn, nbAgent, width, height, speed, agentSize, equity, seed, toric);
		MAS.run();
    }
}