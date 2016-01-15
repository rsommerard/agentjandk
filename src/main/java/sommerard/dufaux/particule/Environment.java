package sommerard.dufaux.particule;

import java.util.List;

public class Environment {
	
	public boolean[][] space;
	
	public void Environment(){
		
	}
	
	public void init(int envsize){
		space = new boolean[envsize][envsize];
	}
	
	public boolean isBusy(int posX, int posY){
		return ((posX < 0) || (posX>space.length-1) || (posY<0) || (posY>space[0].length-1) || (space[posX][posY]));
	}
	
}
