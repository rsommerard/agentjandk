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
		env.space[posX][posY] = 0;
		this.posX+= dir.x;
		this.posY+= dir.y;
		env.space[posX][posY] = 1;
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
