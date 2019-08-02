import java.awt.Color;

public class AirMapper {
	private static final float alpha = 0.5f;
	private AirDataManager adm;
	
	public AirMapper(AirDataManager adm){
		this.adm = adm;
	}
	
	public  Color getMappedColor(double value){
		double max = adm.getMaxVal();
		double min = adm.getMinVal();
		double n = (value - min) / (max - min);
		return new Color((float)n,0,(float)(1.0-n),alpha);
	}

}
