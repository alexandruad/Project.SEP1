package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Customer;
import model.Passenger;
import model.PassengerList;
import model.Reservation;
import model.Route;
import storage.Storage;

public class ReservationCUDialog extends JDialog
{
	// Note: null is a valid value of customer.
	public ReservationCUDialog(JFrame owner, String title, Reservation reservation)
	{
		super(owner);
		controller.reservation = reservation;
		if (controller.reservation == null)
		{
			controller.passengerList = new PassengerList();
		} else
		{
			controller.passengerList = reservation.getPassengers();
		}
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		this.setSize(1100, 400);
		this.setLocation(200, 50);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblReservationId, lblNumberOfSeats, lblPrice, lblStatus, lblCustomers, lblRoutes, lblPassengers, lblPassengerName, lblPassengerAge;
	private JTextField txfReservationId, txfNumberOfSeats, txfPrice, txfPassengerName, txfPassengerAge;
	private JList<Customer> lstCustomers;
	private JList<Route> lstRoutes;
	private JList<Passenger> lstPassengers;
	private DefaultListModel<Customer> lstCustomersModel;
	private DefaultListModel<Route> lstRoutesModel;
	private DefaultListModel<Passenger> lstPassengersModel;
	private JButton btnOk, btnCancel, btnCreate, btnEdit, btnDelete;
	private JPanel pnlStatus;
	private JScrollPane scpCustomers, scpRoutes, scpPassengers;

	private void initContent()
	{
		lblReservationId = new JLabel("Reservation Id:");
		this.add(lblReservationId);
		lblReservationId.setLocation(20, 10);
		lblReservationId.setSize(100, 25);

		txfReservationId = new JTextField();
		this.add(txfReservationId);
		txfReservationId.setLocation(20, 35);
		txfReservationId.setSize(200, 25);
		txfReservationId.setEditable(false);
		txfReservationId.setFocusable(false);

		lblNumberOfSeats = new JLabel("Number of seats:");
		this.add(lblNumberOfSeats);
		lblNumberOfSeats.setLocation(20, 130);
		lblNumberOfSeats.setSize(100, 25);

		txfNumberOfSeats = new JTextField();
		this.add(txfNumberOfSeats);
		txfNumberOfSeats.setLocation(20, 155);
		txfNumberOfSeats.setSize(200, 25);

		lblPrice = new JLabel("Price:");
		this.add(lblPrice);
		lblPrice.setLocation(20, 70);
		lblPrice.setSize(100, 25);

		txfPrice = new JTextField();
		this.add(txfPrice);
		txfPrice.setLocation(20, 95);
		txfPrice.setSize(200, 25);
		// _________________
		lblCustomers = new JLabel("Customers");
		this.add(lblCustomers);
		lblCustomers.setLocation(250, 10);
		lblCustomers.setSize(100, 25);

		lstCustomersModel = new DefaultListModel<>();

		lstCustomers = new JList<Customer>(lstCustomersModel);
		lstCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstCustomers.addListSelectionListener(controller);

		scpCustomers = new JScrollPane(lstCustomers);
		this.add(scpCustomers);
		scpCustomers.setLocation(250, 35);
		scpCustomers.setSize(180, 270);
		// ________________
		lblRoutes = new JLabel("Routes:");
		this.add(lblRoutes);
		lblRoutes.setLocation(450, 10);
		lblRoutes.setSize(100, 25);

		lstRoutesModel = new DefaultListModel<>();

		lstRoutes = new JList<Route>(lstRoutesModel);
		lstRoutes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstRoutes.addListSelectionListener(controller);

		scpRoutes = new JScrollPane(lstRoutes);
		this.add(scpRoutes);
		scpRoutes.setLocation(450, 35);
		scpRoutes.setSize(180, 270);
		// ________________
		lblPassengers = new JLabel("Passengers:");
		this.add(lblPassengers);
		lblPassengers.setLocation(680, 50);
		lblPassengers.setSize(100, 25);

		lstPassengersModel = new DefaultListModel<>();

		lstPassengers = new JList<Passenger>(lstPassengersModel);
		lstPassengers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstPassengers.addListSelectionListener(controller);

		scpPassengers = new JScrollPane(lstPassengers);
		this.add(scpPassengers);
		scpPassengers.setLocation(680, 75);
		scpPassengers.setSize(180, 230);
		// ________________

		lblPassengerName = new JLabel("Passenger name:");
		this.add(lblPassengerName);
		lblPassengerName.setLocation(880, 50);
		lblPassengerName.setSize(100, 25);

		txfPassengerName = new JTextField();
		this.add(txfPassengerName);
		txfPassengerName.setLocation(880, 75);
		txfPassengerName.setSize(150, 25);
		txfPassengerName.setEditable(false);
		txfPassengerName.setFocusable(false);

		lblPassengerAge = new JLabel("Passenger age:");
		this.add(lblPassengerAge);
		lblPassengerAge.setLocation(880, 100);
		lblPassengerAge.setSize(100, 25);

		txfPassengerAge = new JTextField();
		this.add(txfPassengerAge);
		txfPassengerAge.setLocation(880, 125);
		txfPassengerAge.setSize(150, 25);
		txfPassengerAge.setEditable(false);
		txfPassengerAge.setFocusable(false);

		btnOk = new JButton("Ok");
		this.add(btnOk);
		btnOk.setLocation(20, 200);
		btnOk.setSize(90, 25);
		btnOk.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		this.add(btnCancel);
		btnCancel.setLocation(130, 200);
		btnCancel.setSize(90, 25);
		btnCancel.addActionListener(controller);

		btnCreate = new JButton("Create");
		this.add(btnCreate);
		btnCreate.setLocation(680, 10);
		btnCreate.setSize(80, 25);
		btnCreate.addActionListener(controller);

		btnEdit = new JButton("Edit");
		this.add(btnEdit);
		btnEdit.setLocation(780, 10);
		btnEdit.setSize(80, 25);
		btnEdit.addActionListener(controller);

		btnDelete = new JButton("Delete");
		this.add(btnDelete);
		btnDelete.setLocation(880, 10);
		btnDelete.setSize(80, 25);
		btnDelete.addActionListener(controller);

		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(700, 310);
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

	private class Controller implements ActionListener, ListSelectionListener
	{
		public void fillLstCustomerModel()
		{
			lstCustomersModel.clear();
			if (reservation == null)
			{
				for (Customer customer : Storage.getCustomerList().getCustomers())
				{
					lstCustomersModel.addElement(customer);
				}
			} else
			{
				lstCustomersModel.addElement(reservation.getCustomer());
			}

		}

		public void fillLstRoutesModel()
		{
			lstRoutesModel.clear();

			for (Route route : Storage.getRoutes().getRoutes())
			{
				lstRoutesModel.addElement(route);
			}

		}

		public void fillLstPassengerModel()
		{
			lstPassengersModel.clear();

			for (Passenger passenger : passengerList.getPassengers())
			{
				lstPassengersModel.addElement(passenger);
			}

		}

		private Reservation reservation;
		private PassengerList passengerList;

		private boolean closedByOk = false;
		private JFrame frame;

		public void updateView()
		{
			if (reservation != null)
			{
				txfReservationId.setText(Integer.toString(reservation.getReservationId()));
				txfNumberOfSeats.setText(Integer.toString(reservation.getNumberOfSeats()));
				txfPrice.setText(Integer.toString(reservation.getPrice()));
				lstCustomers.setEnabled(false);

			} else
			{
				txfReservationId.setText("");
				txfNumberOfSeats.setText("");
				txfPrice.setText("");
				lstCustomers.setEnabled(true);

			}
			fillLstRoutesModel();
			fillLstCustomerModel();
			fillLstPassengerModel();
			txfPassengerName.setText("");
			txfPassengerAge.setText("");
		}

		// This method is called when a button is pressed.
		@Override
		public void actionPerformed(ActionEvent e)
		{
			lblStatus.setText("");

			if (e.getSource() == btnOk)
			{
				if (txfNumberOfSeats.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Number of seats field is empty!");
					return;
				} else if (!(txfNumberOfSeats.getText().trim().matches("^\\d+$")) || Integer.parseInt(txfNumberOfSeats.getText().trim()) <= 0)
				{
					lblStatus.setText("Error: Number of seats field must be a positive number!");
					return;
				} else if (txfPrice.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Price field is empty!");
					return;
				} else if (!(txfPrice.getText().trim().matches("^\\d+$")) || Integer.parseInt(txfPrice.getText().trim()) <= 0)
				{
					lblStatus.setText("Error: Price field must be a positive number!");
					return;
				} else if (passengerList.getPassengers().size() <= 0)
				{
					lblStatus.setText("Error: You must add at least one passenger!");
					return;
				} else if (reservation == null && lstCustomers.getSelectedIndex() <= 0)
				{
					lblStatus.setText("Error: You must select customer!");
					return;
				} else if (lstRoutes.getSelectedIndex() <= 0)
				{
					lblStatus.setText("Error: You must select route!");
					return;
				}

				/**** update storage ****/

				if (reservation == null)
				{
					Storage.createReservation(lstCustomers.getSelectedValue(), Integer.parseInt(txfNumberOfSeats.getText().trim()), passengerList, lstRoutes.getSelectedValue(), Integer.parseInt(txfPrice.getText().trim()));
				} else
				{
					Storage.updateReservation(reservation, Integer.parseInt(txfNumberOfSeats.getText().trim()), passengerList, lstRoutes.getSelectedValue(), Integer.parseInt(txfPrice.getText().trim()));
				}

				closedByOk = true;
				ReservationCUDialog.this.setVisible(false);
			}

			if (e.getSource() == btnCancel)
			{
				closedByOk = false;
				ReservationCUDialog.this.setVisible(false);
			}
			if (e.getSource() == btnCreate)
			{
				PassengerCUDialog createPassengerDialog = new PassengerCUDialog(frame, "Add Passenger", null);
				createPassengerDialog.setVisible(true);

				if (createPassengerDialog.isOKed())
				{
					/**** update view ****/
					passengerList.addPassenger(createPassengerDialog.getPassenger());
					this.fillLstPassengerModel();
					int lastIndex = lstPassengers.getModel().getSize() - 1;
					lstPassengers.setSelectedIndex(lastIndex); // calls
					// updateView()
					lstPassengers.ensureIndexIsVisible(lastIndex);
				}
				createPassengerDialog.dispose(); // release OS resources

			} // waiting for modal createCustomerDialog to close
			if (e.getSource() == btnEdit)
			{
				if (lstPassengers.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Please select customer!");
					return;
				}

				Passenger passenger = lstPassengers.getSelectedValue();
				PassengerCUDialog updatePassengerDialog = new PassengerCUDialog(frame, "Update Passenger", passenger);
				updatePassengerDialog.setVisible(true);

				// waiting for modal updateCustomersDialog to close

				if (updatePassengerDialog.isOKed())
				{
					/**** update view ****/
					int selectedIndex = lstPassengers.getSelectedIndex();
					passenger = updatePassengerDialog.getPassenger();
					this.fillLstPassengerModel();
					lstPassengers.setSelectedIndex(selectedIndex); // calls
					// updateView()
					lstPassengers.ensureIndexIsVisible(selectedIndex);
				}
				updatePassengerDialog.dispose(); // release OS resources
			}
			if (e.getSource() == btnDelete)
			{
				if (lstPassengers.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Employee is not selected!");
					return;
				}

				Passenger passenger = lstPassengers.getSelectedValue();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Customers", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{

					/**** update storage ****/
					passengerList.deletePassenger(passenger.getPassengerName(), passenger.getPassengerAge());

					/**** update view ****/
					this.fillLstPassengerModel();
					this.updateView();
				}
			}

		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getSource() == lstPassengers)
			{
				if (!e.getValueIsAdjusting())
				{
					if (lstPassengers.getSelectedIndex() >= 0)
					{
						Passenger passenger = lstPassengers.getSelectedValue();
						txfPassengerName.setText(passenger.getPassengerName());
						txfPassengerAge.setText(Integer.toString(passenger.getPassengerAge()));
					} else
					{
						txfPassengerName.setText("");
						txfPassengerAge.setText("");

					}
				}
			}
		}
	}

}