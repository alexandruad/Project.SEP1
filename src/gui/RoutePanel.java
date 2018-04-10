package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import storage.Storage;
import model.Route;
import model.Chauffeur;
import model.Vehicle;

/**
 * EXPLAIN CODE HERE
 * 
 * @author ...
 */
public class RoutePanel extends JPanel
{
	private MainFrame frame;

	public RoutePanel(MainFrame frame)
	{
		this.frame = frame;
		this.setPreferredSize(new Dimension(710, 500));
		this.setLayout(null);
		this.initContent();
	}

	// -------------------------------------------------------------------------
	private JLabel lblRoutes, lblDestination, lblRouteNumber, lblStartDate, lblEndDate, lblPrice, lblChauffeurs, lblVehicles, lblStops, lblStatus;
	private JScrollPane scpRoutes, scpStops;
	private JList<Route> lstRoutes;
	private DefaultListModel<Route> lstRouteModel;
	private JTextField txfDestination, txfRouteNumber, txfStartDate, txfEndDate, txfPrice;
	private JTextArea txaChauffeurs, txaVehicles, txaStops;
	private JButton btnAdd, btnEdit, btnRemove;
	private JPanel pnlStatus;

	private void initContent()
	{
		lblRoutes = new JLabel("Routes");
		this.add(lblRoutes);
		lblRoutes.setLocation(10, 50);
		lblRoutes.setSize(100, 25);
		lstRouteModel = new DefaultListModel<>();
		lstRoutes = new JList<Route>(lstRouteModel);
		lstRoutes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstRoutes.addListSelectionListener(controller);
		scpRoutes = new JScrollPane(lstRoutes);
		this.add(scpRoutes);
		scpRoutes.setLocation(10, 80);
		scpRoutes.setSize(180, 270);
		lblDestination = new JLabel("Destination: ");
		this.add(lblDestination);
		lblDestination.setLocation(200, 80);
		lblDestination.setSize(80, 25);
		txfDestination = new JTextField();
		this.add(txfDestination);
		txfDestination.setLocation(300, 80);
		txfDestination.setSize(150, 25);
		txfDestination.setEditable(false);
		txfDestination.setFocusable(false);
		lblRouteNumber = new JLabel("Route number:");
		this.add(lblRouteNumber);
		lblRouteNumber.setLocation(200, 110);
		lblRouteNumber.setSize(75, 25);
		txfRouteNumber = new JTextField();
		this.add(txfRouteNumber);
		txfRouteNumber.setLocation(300, 110);
		txfRouteNumber.setSize(150, 25);
		txfRouteNumber.setEditable(false);
		txfRouteNumber.setFocusable(false);
		lblStartDate = new JLabel("Starting Date:");
		this.add(lblStartDate);
		lblStartDate.setLocation(200, 140);
		lblStartDate.setSize(70, 25);
		txfStartDate = new JTextField();
		this.add(txfStartDate);
		txfStartDate.setLocation(300, 140);
		txfStartDate.setSize(150, 25);
		txfStartDate.setEditable(false);
		txfStartDate.setFocusable(false);
		lblEndDate = new JLabel("Ending Date:");
		this.add(lblEndDate);
		lblEndDate.setLocation(200, 170);
		lblEndDate.setSize(70, 25);
		txfEndDate = new JTextField();
		this.add(txfEndDate);
		txfEndDate.setLocation(300, 170);
		txfEndDate.setSize(150, 25);
		txfEndDate.setEditable(false);
		txfEndDate.setFocusable(false);
		lblPrice = new JLabel("Price:");
		this.add(lblPrice);
		lblPrice.setLocation(200, 200);
		lblPrice.setSize(60, 25);
		txfPrice = new JTextField();
		this.add(txfPrice);
		txfPrice.setLocation(300, 200);
		txfPrice.setSize(150, 25);
		txfPrice.setEditable(false);
		txfPrice.setFocusable(false);
		lblChauffeurs = new JLabel("Chauffeurs:");
		this.add(lblChauffeurs);
		lblChauffeurs.setLocation(200, 260);
		lblChauffeurs.setSize(60, 25);
		txaChauffeurs = new JTextArea();
		txaChauffeurs.setEditable(false);
		txaChauffeurs.setLocation(300,265 );
		txaChauffeurs.setSize(300, 80);
		this.add(txaChauffeurs);
		lblVehicles = new JLabel("Vehicles:");
		this.add(lblVehicles);
		lblVehicles.setLocation(200, 355);
		lblVehicles.setSize(60, 25);
		txaVehicles = new JTextArea();
		txaVehicles.setEditable(false);
		txaVehicles.setLocation(300,355);
      txaVehicles.setSize(300, 80);
      this.add(txaVehicles);
		btnAdd = new JButton("Add");
		this.add(btnAdd);
		btnAdd.setLocation(10, 10);
		btnAdd.setSize(80, 25);
		btnAdd.addActionListener(controller);
		btnEdit = new JButton("Edit");
		this.add(btnEdit);
		btnEdit.setLocation(110, 10);
		btnEdit.setSize(80, 25);
		btnEdit.addActionListener(controller);
		btnRemove = new JButton("Remove");
		this.add(btnRemove);
		btnRemove.setLocation(210, 10);
		btnRemove.setSize(80, 25);
		btnRemove.addActionListener(controller);
		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(0, 360);
		pnlStatus.setSize(440, 27);
		pnlStatus.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblStatus = new JLabel("");
		pnlStatus.add(lblStatus);
		lblStatus.setLocation(5, 2);
		lblStatus.setSize(430, 25);
		lblStatus.setForeground(Color.RED);
		// initialize this view
		controller.fillLstRoutesModel();
	}

	// -------------------------------------------------------------------------
	public void update()
	{
		controller.updateView();
	}

	// -------------------------------------------------------------------------
	private Controller controller = new Controller();

	private class Controller implements ActionListener, ListSelectionListener//
	{
		public void fillLstRoutesModel()
		{
			lstRouteModel.clear();

			  for (Route route : Storage.getRoutes().getRoutes())
			  {
			     lstRouteModel.addElement(route);
			  }
		}

		public void updateView()
		{
			
			  if (lstRoutes.getSelectedIndex() >= 0) {
			  Route route = lstRoutes.getSelectedValue();
			  txfDestination.setText(route.getDestination());
			  txfRouteNumber.setText(route.getRouteNumber());
			  txfStartDate.setText("" +
			  route.getDateInterval().getStartDate());
			  txfEndDate.setText("" + route.getDateInterval().getEndDate());
			  txfPrice.setText("" + route.getPricePredefined());
			  txaChauffeurs.setText("");
			  for (Chauffeur chauffeur : route.getChauffeurs().getChauffeurs()) {
			  txaChauffeurs.append(chauffeur + "\n");
			  }
			  txaVehicles.setText("");
			  for (Vehicle vehicle : route.getVehicles().getVehicles()) {
			  txaVehicles.append(vehicle + "\n");
			  }
			  txaStops.setText("");
			  
			  } else {
			  txfDestination.setText("");
			  txfRouteNumber.setText("");
			  txfStartDate.setText("");
			  txfEndDate.setText("");
			  txfPrice.setText("");
			  txaChauffeurs.setText("");
			  txaVehicles.setText("");
			 txaStops.setText("");
			 }
			  lblStatus.setText("");
			 
		}

		// This method is called when a button is pressed.
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnAdd)
			{
				RoutesCUDialog createRouteDialog = new RoutesCUDialog(frame, "Create Route", null);
				createRouteDialog.setVisible(true);
				// waiting for modal createRouteDialog to close
				if (createRouteDialog.isOKed())
				{
					/**** update view ****/
					this.fillLstRoutesModel();
					int lastIndex = lstRoutes.getModel().getSize() - 1;
					lstRoutes.setSelectedIndex(lastIndex); // calls
					// updateView()
					lstRoutes.ensureIndexIsVisible(lastIndex);
				}
				createRouteDialog.dispose(); // release OS resources
			}
			if (e.getSource() == btnEdit)
			{
				if (lstRoutes.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Please select conference!");
					return;
				}
				Route route = lstRoutes.getSelectedValue();
				RoutesCUDialog updateRouteDialog = new RoutesCUDialog(frame, "Update Route", route);
				updateRouteDialog.setVisible(true);
				// waiting for modal updateRouteDialog to close
				if (updateRouteDialog.isOKed())
				{
					/**** update view ****/
					int selectedIndex = lstRoutes.getSelectedIndex();
					this.fillLstRoutesModel();
					lstRoutes.setSelectedIndex(selectedIndex); // calls
					// updateView()
					lstRoutes.ensureIndexIsVisible(selectedIndex);
				}
				updateRouteDialog.dispose(); // release OS resources
			}
			if (e.getSource() == btnRemove)
			{
				if (lstRoutes.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Route is not selected!");
					return;
				}
				Route route = lstRoutes.getSelectedValue();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Route", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					/**** update storage ****/
					// Storage.deleteRoute(route);//To-Do: Must implement!
					/**** update view ****/
					this.fillLstRoutesModel();
					this.updateView();
				}
			}
		}

		// This method is called when the selection in a list is changed.
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getSource() == lstRoutes)
			{
				if (!e.getValueIsAdjusting())
				{
					lblStatus.setText("");
					this.updateView();
				}
			}
		}
	}
}