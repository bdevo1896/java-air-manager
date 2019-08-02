import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Controller implements ActionListener, MouseListener, MouseMotionListener{
	
	private AirDataManager adm;
	private MainFrame mf;
	
	public Controller(){
		this.adm = new AirDataManager();
		this.mf = new MainFrame(adm,this);
	}
	
	public double getTempVal(double x, double y){
		return adm.findValueAt(x, y);
	}
	
	public void onAdd(){
		adm.addMonitor(new AirMonitor(-999,"Default",adm.getMinVal(),0,0));
		mf.update();
	}
	
	public void onEdit(){
		
	}
	
	public void onDelete(){
		adm.removeMonitor(mf.getSelectedMonitor());
		mf.update();
	}
	
	public void onSaveAndExit(){
		adm.writeToFile();
		System.exit(0);
	}
	
	public void onClear(){
		adm.getMonitors().clear();
		mf.update();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mf.updateInfoPanel(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.onClick(e.getX(),e.getY());
	}

	private void onClick(int mx, int my) {
		//From StackOverflow
		JTextField nameF = new JTextField();
		JTextField valueF = new JTextField();
		JTextField idF = new JTextField();
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Name"),
				nameF,
				new JLabel("Temperature"),
				valueF,
				new JLabel("ID"),
				idF
		};
		JOptionPane.showMessageDialog(null, inputs, "New Monitor", JOptionPane.PLAIN_MESSAGE);
		
		if(idF.getText().equals("") || nameF.getText().equals("") || valueF.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Entered bad information, a monitor could not be made");
		}else{
			AirMonitor newM = new AirMonitor(Integer.parseInt(idF.getText()),nameF.getText(),Double.parseDouble(valueF.getText()),mx,my);
			adm.addMonitor(newM);
		}
		
		mf.update();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if(btn.getText() == "Add"){
			this.onAdd();
		}else if(btn.getText() == "Edit"){
			
		}else if(btn.getText() == "Delete"){
			this.onDelete();
		}else if(btn.getText() == "Save and Exit"){
			this.onSaveAndExit();
		}else if(btn.getText() == "Clear"){
			
		}
	}

}
