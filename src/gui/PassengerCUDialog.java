package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Passenger;

public class PassengerCUDialog extends JDialog
{
	// Note: null is a valid value of customer.
	public PassengerCUDialog(JFrame owner, String title, Passenger passenger)
	{
		super(owner);
		controller.passenger = passenger;

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		this.setSize(250, 215);
		this.setLocation(300, 50);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblName, lblAge, lblStatus;
	private JTextField txfName, txfAge;
	private JButton btnOk, btnCancel;
	private JPanel pnlStatus;

	private void initContent()
	{
		lblName = new JLabel("Passanger name:");
		this.add(lblName);
		lblName.setLocation(20, 10);
		lblName.setSize(100, 25);

		txfName = new JTextField();
		this.add(txfName);
		txfName.setLocation(20, 35);
		txfName.setSize(200, 25);

		lblAge = new JLabel("Passanger age:");
		this.add(lblAge);
		lblAge.setLocation(20, 70);
		lblAge.setSize(100, 25);

		txfAge = new JTextField();
		this.add(txfAge);
		txfAge.setLocation(20, 95);
		txfAge.setSize(200, 25);

		btnOk = new JButton("Ok");
		this.add(btnOk);
		btnOk.setLocation(20, 140);
		btnOk.setSize(90, 25);
		btnOk.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		this.add(btnCancel);
		btnCancel.setLocation(130, 140);
		btnCancel.setSize(90, 25);
		btnCancel.addActionListener(controller);

		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(10, 115);
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

	public Passenger getPassenger()
	{
		return controller.passenger;
	}

	// -------------------------------------------------------------------------

	private Controller controller = new Controller();

	private class Controller implements ActionListener
	{
		private Passenger passenger;
		private boolean closedByOk = false;

		public void updateView()
		{
			if (passenger != null)
			{
				txfName.setText(passenger.getPassengerName());
				txfAge.setText(Integer.toString(passenger.getPassengerAge()));

			} else
			{
				txfName.setText("");
				txfAge.setText("");

			}
		}

		// This method is called when a button is pressed.
		@Override
		public void actionPerformed(ActionEvent e)
		{

			lblStatus.setText("");
			if (e.getSource() == btnOk)
			{
				if (txfName.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Name field is empty!");
					return;
				} else if (txfAge.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Age field is empty!");
					return;
				} else if (!(txfAge.getText().trim().matches("^\\d+$")) || Integer.parseInt(txfAge.getText().trim()) <= 0)
				{
					lblStatus.setText("Error: Age field must be a positive number!");
					return;
				}

				/**** update storage ****/

				if (passenger == null)
				{
					passenger = new Passenger(txfName.getText().trim(), Integer.parseInt(txfAge.getText().trim()));
				} else
				{
					passenger.setName(txfName.getText().trim());
					passenger.setAge(Integer.parseInt(txfAge.getText().trim()));
				}
				closedByOk = true;
				PassengerCUDialog.this.setVisible(false);
			}
			if (e.getSource() == btnCancel)
			{
				closedByOk = false;
				PassengerCUDialog.this.setVisible(false);
			}

		}
	}
}