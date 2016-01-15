package sommerard.dufaux.particule;

public class Agent {
	
	private int posX;
	private int posY;
	private Direction dir;
	//private int speed;
	private Environment env;
	
	
	public Agent(int posX,int posY,Direction dir,Environment env){
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.env = env;
	}
	
	public void doIt(){
		int nextX = posX+ dir.x;
		int nextY = posY+ dir.y;

		//System.out.println("DEBUT agent "+this+" posX = "+posX+" et posY = "+posY);
		//on delegue la gestion de "collision" à l'env. on pourrait le faire ici...
		if(env.isBusy(nextX, nextY)){
			//on inverse la direction;
			dir.x = dir.x * -1;
			dir.y = dir.y * -1;

			nextX = posX+ dir.x;
			nextY = posY+ dir.y;
			if(env.isBusy(nextX, nextY)){
				//on ne bouge pas
				nextX = posX;
				nextY = posY;
			}
		}

		//System.out.println("FIN agent "+this+" posX = "+posX+" et posY = "+posY);
		
		env.space[posX][posY] = false;
		posX = nextX;
		posY = nextY;
		env.space[posX][posY] = true;

	}

	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	/*public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}*/

	public void setEnv(Environment env) {
		this.env = env;
	}
	

}
