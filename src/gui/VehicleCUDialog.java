package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Vehicle;
import storage.Storage;

public class VehicleCUDialog extends JDialog
{
	// Note: null is a valid value of customer.
	public VehicleCUDialog(JFrame owner, String title, Vehicle vehicle)
	{
		super(owner);
		controller.vehicle = vehicle;

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		this.setSize(250, 465);
		this.setLocation(300, 50);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblRegistrationNumber, lblVehicleType, lblNumberOfSeats, lblNotes, lblStatus;
	private JTextField txfRegistrationNumber, txfVehicleType, txfNumberOfSeats;
	private JTextArea txaNotes;
	private JButton btnOk, btnCancel;
	private JPanel pnlStatus;
	private JScrollPane scpNotes;

	private void initContent()
	{
		lblRegistrationNumber = new JLabel("RegistrationNumber:");
		this.add(lblRegistrationNumber);
		lblRegistrationNumber.setLocation(20, 10);
		lblRegistrationNumber.setSize(100, 25);

		txfRegistrationNumber = new JTextField();
		this.add(txfRegistrationNumber);
		txfRegistrationNumber.setLocation(20, 35);
		txfRegistrationNumber.setSize(200, 25);

		lblVehicleType = new JLabel("VehicleType:");
		this.add(lblVehicleType);
		lblVehicleType.setLocation(20, 70);
		lblVehicleType.setSize(100, 25);

		txfVehicleType = new JTextField();
		this.add(txfVehicleType);
		txfVehicleType.setLocation(20, 95);
		txfVehicleType.setSize(200, 25);

		lblNumberOfSeats = new JLabel("NumberOfSeats:");
		this.add(lblNumberOfSeats);
		lblNumberOfSeats.setLocation(20, 130);
		lblNumberOfSeats.setSize(100, 25);

		txfNumberOfSeats = new JTextField();
		this.add(txfNumberOfSeats);
		txfNumberOfSeats.setLocation(20, 155);
		txfNumberOfSeats.setSize(200, 25);

		lblNotes = new JLabel("Notes:");
		this.add(lblNotes);
		lblNotes.setLocation(20, 190);
		lblNotes.setSize(100, 25);

		txaNotes = new JTextArea();
		this.add(txaNotes);
		txaNotes.setLocation(20, 215);
		txaNotes.setSize(200, 140);

		scpNotes = new JScrollPane(txaNotes);
		this.add(scpNotes);
		scpNotes.setLocation(20, 215);
		scpNotes.setSize(200, 140);

		btnOk = new JButton("Ok");
		this.add(btnOk);
		btnOk.setLocation(20, 390);
		btnOk.setSize(90, 25);
		btnOk.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		this.add(btnCancel);
		btnCancel.setLocation(130, 390);
		btnCancel.setSize(90, 25);
		btnCancel.addActionListener(controller);

		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(10, 360);
		pnlStatus.setSize(375, 27);
		pnlStatus.setBorder(new EmptyBorder(0, 0, 0, 0));

		lblStatus = new JLabel("");
		pnlStatus.add(lblStatus);
		lblStatus.setLocation(5, 1);
		lblStatus.setSize(194, 25);
		lblStatus.setForeground(Color.RED);

		// initialize this view

		controller.updateView();
	}

	// -------------------------------------------------------------------------

	public boolean isOKed()
	{
		return controller.closedByOk;
	}

	// -------------------------------------------------------------------------

	private Controller controller = new Controller();

	private class Controller implements ActionListener
	{
		private Vehicle vehicle;
		private boolean closedByOk = false;

		public void updateView()
		{
			if (vehicle != null)
			{
				txfRegistrationNumber.setText(vehicle.getRegNo());
				txfVehicleType.setText(vehicle.getVehicleType());
				txfNumberOfSeats.setText(Integer.toString(vehicle.getNumberOfSeats()));
				txaNotes.setText(vehicle.getAmenities());
			} else
			{
				txfRegistrationNumber.setText("");
				txfVehicleType.setText("");
				txfNumberOfSeats.setText("");
				txaNotes.setText("");
			}
		}

		// This method is called when a button is pressed.
		@Override
		public void actionPerformed(ActionEvent e)
		{

			lblStatus.setText("");
			if (e.getSource() == btnOk)
			{
				if (txfRegistrationNumber.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Registration number field is empty!");
					return;
				} else if (txfVehicleType.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Vehicle type field is empty!");
					return;
				} else if (txfNumberOfSeats.getText().trim().length() <= 0)
				{

					lblStatus.setText("Error: Number of seats field is empty!");
					return;
				} else if (!(txfNumberOfSeats.getText().trim().matches("^\\d+$")) || Integer.parseInt(txfNumberOfSeats.getText().trim()) <= 0)
				{
					lblStatus.setText("Error: Number of seats field must be a positive number!");
					return;
				}

				/**** update storage ****/

				if (vehicle == null)
				{
					Storage.createVehicle(txfRegistrationNumber.getText().trim(), Integer.parseInt(txfNumberOfSeats.getText().trim()), txfVehicleType.getText().trim(), txaNotes.getText().trim());
				} else
				{
					Storage.updateVehicle(vehicle, txfRegistrationNumber.getText().trim(), Integer.parseInt(txfNumberOfSeats.getText().trim()), txfVehicleType.getText().trim(), txaNotes.getText().trim());
				}
				closedByOk = true;
				VehicleCUDialog.this.setVisible(false);
			}
			if (e.getSource() == btnCancel)
			{
				closedByOk = false;
				VehicleCUDialog.this.setVisible(false);
			}

		}
	}
}