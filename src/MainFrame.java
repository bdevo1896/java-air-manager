import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class MainFrame extends JFrame{
	private JList list;
	private JLabel xcoorLbl;
	private JLabel ycoordLbl;
	private JLabel tempValLbl;
	private AirListModel airModel;
	private AirDataManager adm;
	private AirPanel airPnl;
	private Controller c;

	public MainFrame(AirDataManager adm, Controller c) {
		
		this.adm = adm;
		this.c = c;
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(5, 5));
		
		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new BorderLayout(0, 0));
		
		airModel = new AirListModel(adm);
		list = new JList(airModel);
		westPanel.add(list, BorderLayout.CENTER);
		
		JPanel listButtonPanel = new JPanel();
		westPanel.add(listButtonPanel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(c);
		listButtonPanel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(c);
		listButtonPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(c);
		listButtonPanel.add(btnDelete);
		
		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		JButton btnSaveAndExit = new JButton("Save and Exit");
		btnSaveAndExit.addActionListener(c);
		southPanel.add(btnSaveAndExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(c);
		southPanel.add(btnClear);
		
		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Creating and adding the custom AirPanel to the center panel to be displayed
		 */
		airPnl = new AirPanel(adm,c);
		centerPanel.add(airPnl, BorderLayout.CENTER);
		
		JPanel infoPanel = new JPanel();
		centerPanel.add(infoPanel, BorderLayout.SOUTH);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		JPanel xPosPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) xPosPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		infoPanel.add(xPosPanel);
		
		JLabel lblXPosition = new JLabel("X Position: ");
		lblXPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		xPosPanel.add(lblXPosition);
		
		xcoorLbl = new JLabel("0");
		xcoorLbl.setFont(new Font("Calibri", Font.PLAIN, 16));
		xPosPanel.add(xcoorLbl);
		
		JPanel yPosPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) yPosPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		infoPanel.add(yPosPanel);
		
		JLabel lblYPostition = new JLabel("Y Postition: ");
		lblYPostition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yPosPanel.add(lblYPostition);
		
		ycoordLbl = new JLabel("0");
		ycoordLbl.setFont(new Font("Calibri", Font.PLAIN, 16));
		yPosPanel.add(ycoordLbl);
		
		JPanel tempDisplayPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tempDisplayPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		infoPanel.add(tempDisplayPanel);
		
		JLabel lblTemperature = new JLabel("Temperature: ");
		lblTemperature.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tempDisplayPanel.add(lblTemperature);
		
		tempValLbl = new JLabel("0");
		tempValLbl.setFont(new Font("Calibri", Font.PLAIN, 16));
		tempDisplayPanel.add(tempValLbl);
		
		this.setVisible(true);
		this.setSize(1000, 1000);
	}
	
	//Updates the labels in the infor panel
	public void updateInfoPanel(int mx, int my){
		this.xcoorLbl.setText(""+mx);
		this.ycoordLbl.setText(""+my);
		this.tempValLbl.setText(c.getTempVal(mx, my)+" ºF");
		this.repaint();
	}
	
	//updates the list
	public void update(){
		airModel.fireChanges();
		airPnl.updatePnl();
	}

	public AirMonitor getSelectedMonitor() {
		// TODO Auto-generated method stub
		return (AirMonitor)list.getSelectedValue();
	}

}
