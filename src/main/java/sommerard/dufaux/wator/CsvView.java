package sommerard.dufaux.wator;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class CsvView implements Observer{
	
	private final static String fileName = "stats.csv";
	private BufferedWriter writer;
	private PrintWriter out;
	
	public CsvView() throws IOException {
		System.out.println("create file writer");
		File png = new File("stats.png");
		if(png.exists())
			png.delete();
		File csv = new File("stats.csv");

		if(csv.exists())
			csv.delete();

		out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		out.println("fish, shark");
	}

	public void update(Observable o, Object arg) {
		WatorMAS mas = (WatorMAS) arg;
		String stats = (mas.getNbFish())+", "+mas.getNbShark();
		if(mas.getCurrentTurn()%100 == 0){
			try {
				//System.out.println("close and re-open");
				out.close();
				out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println(stats);

	}
	
	public void close() throws IOException{
		System.out.println("close file writer");
		out.close();
	}

}
