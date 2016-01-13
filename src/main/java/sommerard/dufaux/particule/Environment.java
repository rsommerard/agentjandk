package sommerard.dufaux.particule;

import java.util.List;

public class Environment {
	
	public int[][] space;
	
	public void init(){
		
	}
	
	public boolean isBusy(int posX, int posY){
		return (space[posX][posY] != 0);
	}
	
}
