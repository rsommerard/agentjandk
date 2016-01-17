package sommerard.dufaux.particule;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.shape.Line;

public class TextualView implements View {

	public void update(Observable o, Object arg) {
		String ligne = "";
		MAS mas = (MAS) arg;
    	Environment environment = mas.getEnvironment();

    	for (int i = 0; i < environment.getWidth() + 2; i++){
    		ligne += " . ";
    	}

		System.out.println(ligne);
    	
    	for (int i = 0; i < environment.getHeight(); i++){
    		ligne = " . ";

    		for (int j = 0; j < environment.getWidth(); j++ ){
                ligne += environment.isBusy(j, i) ? " o " : "   ";
    		}

    		System.out.println(ligne + " . ");
    	}
    	
    	ligne="";
        for (int i = 0; i < environment.getWidth() + 2; i++){
            ligne += " . ";
        }

		System.out.println(ligne);
		System.out.println();
	}

}
