import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AirDataManager {

	private ArrayList<AirMonitor> monitors;
	private final static String FILE = "monitors.txt";

	public AirDataManager(){
		this.monitors = readInFile();
	}

	public void test(){
		monitors.add(new AirMonitor(22,"Colorado",55,80,80));
	}

	public void removeMonitor(AirMonitor a){
		if(a != null){
			this.monitors.remove(a);
		}
	}

	public void addMonitor(AirMonitor a){
		if(a != null){
			this.monitors.add(a);
		}
	}

	public AirMonitor monitorAt(int arg0) {
		if(arg0 >= 0 && arg0 < monitors.size()){
			return monitors.get(arg0);
		}
		return null;
	}

	public ArrayList<AirMonitor> getMonitors() {
		return monitors;
	}

	public double getMinVal() {
		if(monitors.size() > 0){
			double min = monitors.get(0).getValue();
			for(int i = 1; i < monitors.size(); i++){
				if(monitors.get(i).getValue() < min){
					min = monitors.get(i).getValue();
				}
			}

			return min;
		}

		return -99999;
	}

	public double getMaxVal() {
		if(monitors.size() > 0){
			double max = monitors.get(0).getValue();
			for(int i = 1; i < monitors.size(); i++){
				if(monitors.get(i).getValue() > max){
					max = monitors.get(i).getValue();
				}
			}

			return max;
		}

		return -99999;
	}

	public double findValueAt(double x, double y){
		double D = 0.0;
		double rtnVal = 0.0;
		ArrayList<Double> contributions = new ArrayList<Double>();


		for(int i = 0; i < monitors.size(); i++){
			D += 1 / (monitors.get(i).distance(x, y));
		}

		for(int i = 0; i < monitors.size(); i++){
			contributions.add((monitors.get(i).getValue()/monitors.get(i).distance(x, y)/D));
		}

		for(int i = 0; i < contributions.size(); i++){
			rtnVal += contributions.get(i);
		}

		return rtnVal;
	}
	
	public ArrayList<AirMonitor> readInFile(){
		ArrayList<AirMonitor> newMonitors = new ArrayList<AirMonitor>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(FILE)))){
			int monitors = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < monitors; i++){
				AirMonitor aM = AirMonitor.readIn(br.readLine());
				newMonitors.add(aM);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newMonitors;
	}
	
	public void writeToFile(){
		File f = new File(FILE);
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))){
			pw.println(monitors.size());
			
			for(AirMonitor a: monitors){
				pw.println(a.toWrite());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
