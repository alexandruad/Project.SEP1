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
import model.Vehicle;
import storage.Storage;

public class VehiclePanel extends JPanel
{
	private MainFrame frame;

	public VehiclePanel(MainFrame frame)
	{
		this.frame = frame;

		this.setPreferredSize(new Dimension(710, 500));
		this.setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblRegistrationNumber, lblVehicleType, lblNumberOfSeats, lblNotes, lblStatus;
	private JScrollPane scpVehicles, scpNotes;
	private JList<Vehicle> lstVehicles;
	private DefaultListModel<Vehicle> lstVehiclesModel;
	private JTextField txfRegistrationNumber, txfVehicleType, txfNumberOfSeats;
	private JTextArea txaNotes;
	private JButton btnCreate, btnEdit, btnDelete;
	private JPanel pnlStatus;

	private void initContent()
	{

		lstVehiclesModel = new DefaultListModel<>();

		lstVehicles = new JList<Vehicle>(lstVehiclesModel);
		lstVehicles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstVehicles.addListSelectionListener(controller);

		scpVehicles = new JScrollPane(lstVehicles);
		this.add(scpVehicles);
		scpVehicles.setLocation(10, 80);
		scpVehicles.setSize(180, 270);

		lblRegistrationNumber = new JLabel("RegistrationNumber:");
		this.add(lblRegistrationNumber);
		lblRegistrationNumber.setLocation(200, 80);
		lblRegistrationNumber.setSize(100, 25);

		txfRegistrationNumber = new JTextField();
		this.add(txfRegistrationNumber);
		txfRegistrationNumber.setLocation(300, 80);
		txfRegistrationNumber.setSize(150, 25);
		txfRegistrationNumber.setEditable(false);
		txfRegistrationNumber.setFocusable(false);

		lblVehicleType = new JLabel("VehicleType:");
		this.add(lblVehicleType);
		lblVehicleType.setLocation(200, 110);
		lblVehicleType.setSize(100, 25);

		txfVehicleType = new JTextField();
		this.add(txfVehicleType);
		txfVehicleType.setLocation(300, 110);
		txfVehicleType.setSize(150, 25);
		txfVehicleType.setEditable(false);
		txfVehicleType.setFocusable(false);

		lblNumberOfSeats = new JLabel("NumberOfSeats:");
		this.add(lblNumberOfSeats);
		lblNumberOfSeats.setLocation(200, 140);
		lblNumberOfSeats.setSize(100, 25);

		txfNumberOfSeats = new JTextField();
		this.add(txfNumberOfSeats);
		txfNumberOfSeats.setLocation(300, 140);
		txfNumberOfSeats.setSize(150, 25);
		txfNumberOfSeats.setEditable(false);
		txfNumberOfSeats.setFocusable(false);

		lblNotes = new JLabel("Notes:");
		this.add(lblNotes);
		lblNotes.setLocation(200, 170);
		lblNotes.setSize(60, 25);

		txaNotes = new JTextArea();
		this.add(txaNotes);
		txaNotes.setLocation(300, 170);
		txaNotes.setSize(150, 120);
		txaNotes.setEditable(false);
		txaNotes.setFocusable(false);

		scpNotes = new JScrollPane(txaNotes);
		this.add(scpNotes);
		scpNotes.setLocation(300, 170);
		scpNotes.setSize(150, 120);

		btnCreate = new JButton("Create");
		this.add(btnCreate);
		btnCreate.setLocation(10, 10);
		btnCreate.setSize(80, 25);
		btnCreate.addActionListener(controller);

		btnEdit = new JButton("Edit");
		this.add(btnEdit);
		btnEdit.setLocation(110, 10);
		btnEdit.setSize(80, 25);
		btnEdit.addActionListener(controller);

		btnDelete = new JButton("Delete");
		this.add(btnDelete);
		btnDelete.setLocation(210, 10);
		btnDelete.setSize(80, 25);
		btnDelete.addActionListener(controller);

		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(0, 420);
		pnlStatus.setSize(440, 27);
		pnlStatus.setBorder(new EmptyBorder(0, 0, 0, 0));

		lblStatus = new JLabel("");
		pnlStatus.add(lblStatus);
		lblStatus.setLocation(5, 2);
		lblStatus.setSize(430, 25);
		lblStatus.setForeground(Color.RED);

		// initialize this view
		controller.fillLstVehicleModel();
	}

	// -------------------------------------------------------------------------

	public void update()
	{
		controller.updateView();
	}

	// -------------------------------------------------------------------------

	private Controller controller = new Controller();

	private class Controller implements ActionListener, ListSelectionListener
	{
		public void fillLstVehicleModel()
		{
			lstVehiclesModel.clear();

			for (Vehicle vehicle : Storage.getVehicleList().getVehicles())
			{
				lstVehiclesModel.addElement(vehicle);
			}
		}

		public void updateView()
		{

			if (lstVehicles.getSelectedIndex() >= 0)
			{
				Vehicle vehicle = lstVehicles.getSelectedValue();
				txfRegistrationNumber.setText(vehicle.getRegNo());
				txfVehicleType.setText(vehicle.getVehicleType());
				txfNumberOfSeats.setText(String.valueOf(vehicle.getNumberOfSeats()));
				txaNotes.setText(vehicle.getAmenities());

			} else
			{
				txfRegistrationNumber.setText("");
				txfVehicleType.setText("");
				txfNumberOfSeats.setText("");
				txaNotes.setText("");
			}
			lblStatus.setText("");

		}

		// This method is called when a button is pressed or option is selected
		// in combobox.
		@Override
		public void actionPerformed(ActionEvent e)
		{

			if (e.getSource() == btnCreate)
			{
				VehicleCUDialog createVehicleDialog = new VehicleCUDialog(frame, "Create Vehicle", null);
				createVehicleDialog.setVisible(true);
				// waiting for modal createVehicleDialog to close
				if (createVehicleDialog.isOKed())
				{
					/**** update view ****/

					this.fillLstVehicleModel();
					int lastIndex = lstVehicles.getModel().getSize() - 1;
					lstVehicles.setSelectedIndex(lastIndex); // calls
															 // updateView();
					lstVehicles.ensureIndexIsVisible(lastIndex);
				}
				createVehicleDialog.dispose(); // release OS resources
			}
			if (e.getSource() == btnEdit)
			{
				if (lstVehicles.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Please select vehicle!");
					return;
				}
				Vehicle vehicle = lstVehicles.getSelectedValue();
				VehicleCUDialog updateVehicleDialog = new VehicleCUDialog(frame, "Update Vehicle", vehicle);
				updateVehicleDialog.setVisible(true);
				// waiting for modal updateCustomersDialog to close
				if (updateVehicleDialog.isOKed())
				{
					/**** update view ****/

					int selectedIndex = lstVehicles.getSelectedIndex();
					this.fillLstVehicleModel();
					lstVehicles.setSelectedIndex(selectedIndex); // calls
																 // updateView()
					lstVehicles.ensureIndexIsVisible(selectedIndex);
				}
				updateVehicleDialog.dispose(); // release OS resources
			}
			if (e.getSource() == btnDelete)
			{
				if (lstVehicles.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Vehicle is not selected!");
					return;
				}
				Vehicle vehicle = lstVehicles.getSelectedValue();
				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Customers", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					/**** update storage ****/

					Storage.deleteVehicle(vehicle);
					/**** update view ****/
					this.fillLstVehicleModel();
					this.updateView();
				}
			}

		}

		// This method is called when the selection in a list is changed.

		public void valueChanged(ListSelectionEvent e)
		{

			if (e.getSource() == lstVehicles)
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