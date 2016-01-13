package sommerard.dufaux.particule;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class View extends Application{
	
	public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Title");
        
        final Circle circ = new Circle(5, 5, 5);
        final Group root = new Group(circ);
        final Scene scene = new Scene(root, 500, 500);        
              
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public void update(SMA sma){
	}

}
