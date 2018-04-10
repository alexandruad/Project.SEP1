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
import model.Passenger;
import model.Reservation;
import storage.Storage;

public class ReservationPanel extends JPanel
{
	private MainFrame frame;

	public ReservationPanel(MainFrame frame)
	{
		this.frame = frame;

		this.setPreferredSize(new Dimension(710, 390));
		this.setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblReservations, lblId, lblNumberOfSeats, lblRoute, lblPrice, lblCustomer, lblPassengers, lblStatus;
	private JScrollPane scpReservations, scpPassengers;
	private JList<Reservation> lstReservations;
	private DefaultListModel<Reservation> lstReservationModel;
	private JTextField txfId, txfNumberOfSeats, txfRoute, txfCustomer, txfPrice, txfNotes;
	private JTextArea txaPassengers;
	private JButton btnAdd, btnEdit, btnRemove;
	private JPanel pnlStatus;

	private void initContent()
	{
		lblReservations = new JLabel("Reservations");
		this.add(lblReservations);
		lblReservations.setLocation(10, 50);
		lblReservations.setSize(100, 25);

		lstReservationModel = new DefaultListModel<>();

		lstReservations = new JList<Reservation>(lstReservationModel);
		lstReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReservations.addListSelectionListener(controller);

		scpReservations = new JScrollPane(lstReservations);
		this.add(scpReservations);
		scpReservations.setLocation(10, 80);
		scpReservations.setSize(180, 270);

		lblId = new JLabel("Reservation Id:");
		this.add(lblId);
		lblId.setLocation(200, 80);
		lblId.setSize(60, 25);

		txfId = new JTextField();
		this.add(txfId);
		txfId.setLocation(300, 80);
		txfId.setSize(150, 25);
		txfId.setEditable(false);
		txfId.setFocusable(false);

		lblNumberOfSeats = new JLabel("Number of seats:");
		this.add(lblNumberOfSeats);
		lblNumberOfSeats.setLocation(200, 110);
		lblNumberOfSeats.setSize(100, 25);

		txfNumberOfSeats = new JTextField();
		this.add(txfNumberOfSeats);
		txfNumberOfSeats.setLocation(300, 110);
		txfNumberOfSeats.setSize(150, 25);
		txfNumberOfSeats.setEditable(false);
		txfNumberOfSeats.setFocusable(false);

		lblPrice = new JLabel("Price:");
		this.add(lblPrice);
		lblPrice.setLocation(200, 140);
		lblPrice.setSize(100, 25);

		txfPrice = new JTextField();
		this.add(txfPrice);
		txfPrice.setLocation(300, 140);
		txfPrice.setSize(150, 25);
		txfPrice.setEditable(false);
		txfPrice.setFocusable(false);

		lblCustomer = new JLabel("Customer:");
		this.add(lblCustomer);
		lblCustomer.setLocation(200, 170);
		lblCustomer.setSize(100, 25);

		txfCustomer = new JTextField();
		this.add(txfCustomer);
		txfCustomer.setLocation(300, 170);
		txfCustomer.setSize(150, 25);
		txfCustomer.setEditable(false);
		txfCustomer.setFocusable(false);

		lblRoute = new JLabel("Route:");
		this.add(lblRoute);
		lblRoute.setLocation(200, 200);
		lblRoute.setSize(100, 25);

		txfRoute = new JTextField();
		this.add(txfRoute);
		txfRoute.setLocation(300, 200);
		txfRoute.setSize(150, 25);
		txfRoute.setEditable(false);
		txfRoute.setFocusable(false);

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

		lblPassengers = new JLabel("Passengers:");
		this.add(lblPassengers);
		lblPassengers.setLocation(480, 50);
		lblPassengers.setSize(60, 25);

		txaPassengers = new JTextArea();
		txaPassengers.setEditable(false);

		scpPassengers = new JScrollPane(txaPassengers);
		this.add(scpPassengers);
		scpPassengers.setLocation(480, 80);
		scpPassengers.setSize(210, 270);

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
		controller.fillLstReservationModel();
	}

	public void update()
	{
		controller.updateView();
	}

	// -------------------------------------------------------------------------

	private Controller controller = new Controller();

	private class Controller implements ActionListener, ListSelectionListener
	{
		public void fillLstReservationModel()
		{
			lstReservationModel.clear();

			for (Reservation reservation : Storage.getReservationList().getReservations())
			{
				lstReservationModel.addElement(reservation);
			}

		}

		public void updateView()
		{

			if (lstReservations.getSelectedIndex() >= 0)
			{
				Reservation reservation = lstReservations.getSelectedValue();
				txfId.setText(Integer.toString(reservation.getReservationId()));
				txfNumberOfSeats.setText(Integer.toString(reservation.getNumberOfSeats()));
				txfPrice.setText(Integer.toString(reservation.getPrice()));
				txfCustomer.setText(reservation.getCustomer().toString());
				txfRoute.setText(reservation.getRoute().toString());
				txaPassengers.setText("");
				for (Passenger passenger : reservation.getPassengers().getPassengers())
				{
					txaPassengers.append(passenger + "\n");
				}
			} else
			{
				txfId.setText("");
				txfNumberOfSeats.setText("");
				txfPrice.setText("");
				txfCustomer.setText("");
				txfRoute.setText("");
				txaPassengers.setText("");

			}
			lblStatus.setText("");

		}

		// This method is called when a button is pressed.
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnAdd)
			{
				ReservationCUDialog createReservationDialog = new ReservationCUDialog(frame, "Add Reservation", null);
				createReservationDialog.setVisible(true);

				if (createReservationDialog.isOKed())
				{
					/**** update view ****/
					this.fillLstReservationModel();
					int lastIndex = lstReservations.getModel().getSize() - 1;
					lstReservations.setSelectedIndex(lastIndex); // calls
					// updateView()
					lstReservations.ensureIndexIsVisible(lastIndex);
				}
				createReservationDialog.dispose(); // release OS resources

			} // waiting for modal createCustomerDialog to close

			if (e.getSource() == btnEdit)
			{
				if (lstReservations.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Please select customer!");
					return;
				}

				Reservation reservation = lstReservations.getSelectedValue();
				ReservationCUDialog updateReservationDialog = new ReservationCUDialog(frame, "Update Reservation", reservation);
				updateReservationDialog.setVisible(true);

				// waiting for modal updateCustomersDialog to close

				if (updateReservationDialog.isOKed())
				{
					/**** update view ****/
					int selectedIndex = lstReservations.getSelectedIndex();
					this.fillLstReservationModel();
					lstReservations.setSelectedIndex(selectedIndex); // calls
					// updateView()
					lstReservations.ensureIndexIsVisible(selectedIndex);
				}
				updateReservationDialog.dispose(); // release OS resources
			}

			if (e.getSource() == btnRemove)
			{
				if (lstReservations.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Employee is not selected!");
					return;
				}

				Reservation reservation = lstReservations.getSelectedValue();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Customers", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{

					/**** update storage ****/
					Storage.deleteReservation(reservation);

					/**** update view ****/
					this.fillLstReservationModel();
					this.updateView();
				}
			}
		}

		// This method is called when the selection in a list is changed.
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getSource() == lstReservations)
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