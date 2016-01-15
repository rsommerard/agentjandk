package sommerard.dufaux.particule;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TextualView implements Observer{

	
	private int agentsize;
	
	
	public TextualView(int agentsize, int envsize, boolean gridvisibility) {
		
		this.agentsize = agentsize;
	}
	
	
	
	public void drawGrid(int envSize, int agentSize){
		Line line;
		for(int i=0;i<envSize;i++){
			//USELESS IN TEXTUAL VIEW
		}
		for(int i=0;i<envSize;i++){
			//USELESS IN TEXTUAL VIEW
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String ligne = "";
		
		SMA sma = (SMA)arg;
    	Environment env = sma.getEnvironment();
    	
    	//on double en largeur la taille d'une case poura voir quelque chose d'un peu "carré". 
    	int widthCase = 2;
    	for(int i = 0; i < env.space.length*widthCase; i++){
    		ligne+="_";
    	}
		System.out.println(ligne);
    	
    	for(int j = 0; j< env.space.length; j++){
    		ligne = "|";
    		for(int k = 0; k < env.space[j].length; k++ ){
    			if(env.space[j][k]){
    				ligne+="XX"; //widthCase = 2 
    			}else{
    				ligne+="  "; //widthCase = 2
    			}
    		}
    		System.out.println(ligne+"|");
    	}
    	
    	ligne="";
    	for(int i = 0; i < env.space.length*widthCase; i++){
    		ligne+="_";
    	}
		System.out.println(ligne);
		System.out.println();
	}

}
