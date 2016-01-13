package sommerard.dufaux.particule;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static int nbBall;
	public static int envsize = 100;
	public static int agentsize = 4;
	public static int speed = 50;
	public static boolean gridvisibility = false;
	public static boolean equity = false;
	public static String seed;
	public static boolean toric = false;
	
    public static void main( String[] args )
    {
    	
    	Map<String,Option> parameters = new HashMap<String,Option>();
    	
    	parameters.put("nbBall",new Option("nbBall",true," REQUIRED. number of ball."));
    	parameters.put("envsize",new Option("envsize",true," size of the environnement in number of case. (default : 100x100)"));
    	parameters.put("agentsize",new Option("agentsize", true, " size of an agent (in pixel). (default : 6)"));
    	parameters.put("speed",new Option("speed", true, " number of millisecond beetween each turn. (default : 50)"));
    	parameters.put("seed",new Option("seed", true, ""));
    	parameters.put("gridvisibility",new Option("gridvisibility", false, " 1 = true, 0 = false (default : 1)"));
    	parameters.put("equity",new Option("equity", false, " order of action is random. 1 = true, 0 = false (default : 1)"));
    	parameters.put("toric",new Option("toric", false, "the toricity of the this world. (no rebound) 1 = true, 0 = false (default : 1)"));

    	
    	// create Options object
    	Options options = new Options();
    	
    	for(Entry<String,Option> param : parameters.entrySet()){
    		options.addOption(param.getValue());
    	}

    	
    	CommandLineParser parser = new DefaultParser();
    	try {
    		CommandLine cmd = parser.parse( options, args);
    		


        	System.out.println(cmd.hasOption("nbBall"));
        	System.out.println(cmd.getOptionValue("nbBall"));
        	System.out.println(cmd.hasOption("gridvisibility"));
        	System.out.println(cmd.getOptionValue("gridvisibility"));
    		
        	if(!cmd.hasOption("-nbBall")) {

            	if(cmd.hasOption("nbBall") && cmd.getOptionValue("nbBall") != null){
            		nbBall = Integer.parseInt(cmd.getOptionValue("nbBall"));
            	}
            	if(cmd.hasOption("envsize") && cmd.getOptionValue("envsize") != null){
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
            	}
            	
            	
        		HelpFormatter formatter = new HelpFormatter();
        		formatter.printHelp( "list of parameters", options );
        		System.exit(0);
        	}
        	
        	//Call init
        	initSMA();
        	
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
    		HelpFormatter formatter = new HelpFormatter();
    		formatter.printHelp( "list of parameters", options );
        }
    }
    
    public static void initSMA(){
    	SMA sma = new SMA();
    	sma.run(nbBall, envsize, speed, seed, toric);
    	View view = new View();
    	
    }
}
