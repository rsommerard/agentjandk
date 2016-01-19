package sommerard.dufaux.particule;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static int nbTurn = 1000000;
	public static int nbBall = 3;
	public static int width = 5;
    public static int height = 5;
	public static int agentSize = 20;
	public static int speed = 1000;
	public static boolean grid = true;
	public static boolean equity = false;
	public static String seed = "12345";
	public static boolean toric = true;
	
	
    public static void main(String[] args) throws InterruptedException {
    	
    	Map<String, Option> parameters = new HashMap<String,Option>();
    	
    	parameters.put("nbAgent", new Option("nbAgent", true, "REQUIRED"));
    	/*parameters.put("envsize",new Option("envsize",true," size of the environnement in number of case. (default : 50x50)"));
    	parameters.put("agentsize",new Option("agentsize", true, " size of an agent (in pixel). (default : 10)"));
    	parameters.put("speed",new Option("speed", true, " number of millisecond beetween each turn. (default : 50)"));
    	parameters.put("seed",new Option("seed", true, ""));
    	parameters.put("gridvisibility",new Option("gridvisibility", false, " 1 = true, 0 = false (default : 1)"));
    	parameters.put("equity",new Option("equity", false, " order of action is random. 1 = true, 0 = false (default : 1)"));
    	parameters.put("toric",new Option("toric", false, "the toricity of the this world. (no rebound) 1 = true, 0 = false (default : 1)"));*/

    	Options options = new Options();
    	
    	for(Map.Entry<String, Option> param : parameters.entrySet()) {
    		options.addOption(param.getValue());
    	}

    	CommandLineParser parser = new DefaultParser();

    	try {
    		CommandLine cmd = parser.parse( options, args);
    		
        	if (!cmd.hasOption("-nbBall")) {
        		HelpFormatter formatter = new HelpFormatter();
        		formatter.printHelp( "list of parameters", options );
        		System.exit(0);
        	}

        	if(cmd.hasOption("nbBall") && cmd.getOptionValue("nbBall") != null){
        		nbBall = Integer.parseInt(cmd.getOptionValue("nbBall"));
        	}

        	/*if(cmd.hasOption("envsize") && cmd.getOptionValue("envsize") != null){
        		envsize = Integer.parseInt(cmd.getOptionValue("envsize"));
        	}
        	if(cmd.hasOption("agentsize") && cmd.getOptionValue("agentsize") != null){
        		agentsize = Integer.parseInt(cmd.getOptionValue("agentsize"));
        	}
        	if(cmd.hasOption("speed") && cmd.getOptionValue("speed") != null){
        		speed = Integer.parseInt(cmd.getOptionValue("speed"));
        	}
        	if(cmd.hasOption("seed")){
        		seed = cmd.getOptionValue("seed");
        	}
        	if(cmd.hasOption("gridvisibility")){
        		gridvisibility = true;
        	}
        	if(cmd.hasOption("equity")){
        		equity = true;
        	}
        	if(cmd.hasOption("toric")){
        		toric = true;
        	}*/

        	initSMA();
        	
        } catch (ParseException exp) {
            System.err.println( "Parsing failed. Reason: " + exp.getMessage());
    		HelpFormatter formatter = new HelpFormatter();
    		formatter.printHelp("list of parameters", options);
        }
    }
    
    public static void initSMA() throws InterruptedException {
        View view = new View(width, height, agentSize, grid);
        MAS MAS = new MAS(view);
    	
    	MAS.init(nbTurn, nbAgent, width, height, speed, equity, seed, toric);
    	MAS.run(nbTurn, speed);
    }


}
