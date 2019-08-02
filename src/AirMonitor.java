
public class AirMonitor {
	
	private int id;
	private String name;
	private double value,x,y;
	
	public AirMonitor(int id, String name, double value, double x, double y) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return name+" ("+value+")";
	}
	
	public String toWrite(){
		return id+"|"+value+"|"+name+"|"+x+"|"+y;
	}
	
	public static AirMonitor readIn(String line){
		//Example line: 1|76.5|Denton|30|30
		String[] data = line.split("[|]");
		if(data.length == 5){//If the line read isn't formatted to match the 'toWrite' it will return a default broken monitor
			int id = Integer.parseInt(data[0]);
			double value = Double.parseDouble(data[1]);
			String name = data[2];
			double x = Double.parseDouble(data[3]);
			double y = Double.parseDouble(data[4]);
			return new AirMonitor(id,name,value,x,y);
		}else{
			return new AirMonitor(-999,"",0,0,0);
		}
		
	}
	
	public double distance (double x, double y){
		return Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y),2));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	

}
