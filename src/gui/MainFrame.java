package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import storage.*;

public class MainFrame extends JFrame {
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting look & feel: " + e.getMessage());
		}
	}
	
	// -------------------------------------------------------------------------
	
	public static void main(String[] args) {
		Storage storage = new Storage();
		
		MainFrame frame = new MainFrame();
		frame.pack();
		frame.setVisible(true);
	}
	
	public MainFrame() {
		this.setTitle("VIA Bus");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		
		this.initMenu();
		this.initContent();
	}
	
	// -------------------------------------------------------------------------
	
	private JMenuItem mniExit;
	
	private void initMenu() {
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.LIGHT_GRAY);
		this.setJMenuBar(menubar);
		
		JMenu mnuFile = new JMenu("File");
		menubar.add(mnuFile);
		
		mniExit = new JMenuItem();
		mnuFile.add(mniExit);
		mniExit.setText("Exit");
		mniExit.addActionListener(controller);
	}
	
	// -------------------------------------------------------------------------
	
	private JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
	private CustomersPanel pnlCustomers = new CustomersPanel(this);
	private EmployeesPanel pnlEmployees = new EmployeesPanel(this);
	private ReservationPanel pnlReservation = new ReservationPanel(this);
	private VehiclePanel pnlVehicle = new VehiclePanel(this);
	private RoutePanel pnlRoute = new RoutePanel(this);
	
	private void initContent() {
		this.add(pane);
		pane.addTab("Customers", pnlCustomers);
		pane.add("Employees", pnlEmployees);
		pane.add("Reservations", pnlReservation);
		pane.addTab("Vehicles", pnlVehicle);
		pane.add("Routes", pnlRoute);
		pane.addChangeListener(controller);
	}
	
	// -------------------------------------------------------------------------
	
	private Controller controller = new Controller();
	
	private class Controller implements ChangeListener, ActionListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if (pane.getSelectedIndex() == 0)
				pnlCustomers.update();
			pnlEmployees.update();
			pnlReservation.update();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mniExit) {
				System.exit(0);
			}
		}
	}
}
