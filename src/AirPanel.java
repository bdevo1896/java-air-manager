import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AirPanel extends JPanel {
	
	private AirDataManager adm;
	private AirMapper aP;
	private ArrayList<Marker> markers;
	private ImageIcon bgroundImg;
	private static final int MAX_WIDTH = 800, MAX_HEIGHT = 800;
	private static final int ACCURACY = 20;//Pixel length of the squares representing temp on the map. Larger the number slower it goes. (For this I had help from Bryce)
	
	
	public AirPanel(AirDataManager adm, Controller c){
		this.bgroundImg = new ImageIcon("DFW_MAP.png");
		this.adm = adm;
		this.markers = new ArrayList<Marker>();
		this.aP = new AirMapper(adm);
		this.addMouseListener(c);
		this.addMouseMotionListener(c);
		this.setFocusable(true);
		this.updatePnl();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgroundImg.getImage(), 0, 0, MAX_WIDTH, MAX_HEIGHT,null);
		
		paintColorMap(g);
		
		for(Marker m: markers){
			m.drawOn(g);
		}
		
	}
	
	public void paintColorMap(Graphics g){
		
		//Color c = aP.getMappedColor(adm.)
		if(adm.getMonitors().size()== 1){
			Color c = aP.getMappedColor(adm.findValueAt(0, 0));
			g.setColor(c);
			g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
		}else if(adm.getMonitors().size() > 1){
			for(int i = 0; i < MAX_WIDTH / ACCURACY; i++){
				for(int k = 0; k < MAX_HEIGHT / ACCURACY; k++){
					Color c = aP.getMappedColor(adm.findValueAt(i * ACCURACY + ACCURACY /2, k * ACCURACY + ACCURACY /2));
					g.setColor(c);
					g.fillRect(i * ACCURACY, k * ACCURACY, ACCURACY, ACCURACY);
				}
			}
		}
	}
	
	public void updatePnl(){
		this.markers.clear();
		ArrayList<AirMonitor> monitors = adm.getMonitors();
		
		for(AirMonitor a: monitors){
			markers.add(new Marker(a.getName(),a.getX(),a.getY()));
		}
		
		repaint();
	}
	
	
	private class Marker {
		private double x,y;
		private String name;
		
		public Marker(String name, double x, double y){
			this.x = x;
			this.y = y;
			this.name = name;
		}
		
		public void drawOn(Graphics g){
			g.setColor(Color.white);
			
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.drawString(name, (int)x - 5, (int)y - 10);
			g.fillOval((int) x - 5, (int) y -5, 10, 10);
		}
	}
}
