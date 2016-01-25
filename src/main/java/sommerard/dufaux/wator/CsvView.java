package sommerard.dufaux.wator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class CsvView implements Observer{
	
	private final static String fileName = "stats.csv";
	private BufferedWriter writer;
	private PrintWriter out;
	
	public CsvView() throws IOException{
		System.out.println("create file writer");
		out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		out.println("fish, shark");
	}

	public void update(Observable o, Object arg) {
		MASWator mas = (MASWator) arg;
		String stats = (mas.getNbFish()/4)+", "+mas.getNbShark();
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
