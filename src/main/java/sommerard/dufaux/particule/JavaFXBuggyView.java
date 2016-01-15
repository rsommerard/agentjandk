package sommerard.dufaux.particule;

import javafx.scene.layout.Pane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class JavaFXBuggyView implements Observer{

	private int agentSize;
	private Pane canvas;
	private List<Circle> graphicAgents;
	private List<Line> grid;
	
	public JavaFXBuggyView(int agentSize, int envSize, boolean gridVisibility){
		int width = agentSize*envSize;
		int height = agentSize*envSize;
		this.canvas = new Pane();
		this.canvas.setPrefSize(width,  height);
		
		this.agentSize = agentSize;
		
		this.graphicAgents = new ArrayList<Circle>();
		this.grid = new ArrayList<Line>();
		//if gridvisiblity
		drawGrid(envSize, agentSize);
		canvas.getChildren().addAll(grid);
	}
	
	public Pane getCanvas(){
		return this.canvas;
	}
	
	public void drawGrid(int envSize, int agentSize){
		Line line;
		for(int i=0;i<envSize;i++){
			line = new Line(i*agentSize, 0, i*agentSize, envSize*agentSize);
			line.setFill(Color.GRAY);
			grid.add(line);
		}
		for(int i=0;i<envSize;i++){
			line = new Line(0, i*agentSize, envSize*agentSize, i*agentSize);
			line.setFill(Color.GRAY);
			grid.add(line);
		}
	}
	
	public void drawAgents(int[][] space){
		
	}

    
    public void update(Observable o, Object arg){
    	System.out.println("UPDATE VIEW");
    	SMA sma = (SMA)arg;
    	Environment env = sma.getEnvironment();
    	int radius = agentSize/2;
    	graphicAgents.clear();
    	
    	for(int j = 0; j< env.space.length; j++){
    		for(int k = 0; k < env.space[j].length; k++ ){
    			if(env.space[j][k]){
    				//System.out.println("dessine cercle à "+(j*agentSize+radius)+" - "+(k*agentSize+radius));
    				graphicAgents.add(new Circle(j*agentSize+radius,k*agentSize+radius,radius));
    			}
    		}
    	}
    	canvas.getChildren().addAll(graphicAgents);
    	//canvas.requestLayout();
    }


}
/*
public class View extends Application implements Observer{

	private int width;
	private int height;
	
	public View(){
		
	}
	
	public View(int width, int height){
		this.width = width;
		this.height = height;
		
		this.launch(null);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Title");
        
        final Circle circ = new Circle(40, 40, 30);
        final Group root = new Group(circ);
        final Scene scene = new Scene(root, 400, 300);        
        System.out.println("--"+width);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    public void update(Observable o, Object arg){
    	
    }

}

*/
