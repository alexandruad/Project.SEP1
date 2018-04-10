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
import model.Customer;
import storage.*;

public class CustomersCUDialog extends JDialog
{
	// Note: null is a valid value of customer.
	public CustomersCUDialog(JFrame owner, String title, Customer customer)
	{
		super(owner);
		controller.customer = customer;

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		this.setSize(250, 575);
		this.setLocation(300, 50);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblName, lblPhone, lblAddress, lblEMail, lblCompanyInfo, lblNotes, lblStatus;
	private JTextField txfName, txfPhone, txfAddress, txfEMail, txfCompanyInfo;
	private JTextArea txaNotes;
	private JButton btnOk, btnCancel;
	private JPanel pnlStatus;
	private JScrollPane scpNotes;

	private void initContent()
	{
		lblName = new JLabel("Name:");
		this.add(lblName);
		lblName.setLocation(20, 10);
		lblName.setSize(100, 25);

		txfName = new JTextField();
		this.add(txfName);
		txfName.setLocation(20, 35);
		txfName.setSize(200, 25);

		lblPhone = new JLabel("Phone:");
		this.add(lblPhone);
		lblPhone.setLocation(20, 70);
		lblPhone.setSize(100, 25);

		txfPhone = new JTextField();
		this.add(txfPhone);
		txfPhone.setLocation(20, 95);
		txfPhone.setSize(200, 25);

		lblAddress = new JLabel("Address:");
		this.add(lblAddress);
		lblAddress.setLocation(20, 130);
		lblAddress.setSize(100, 25);

		txfAddress = new JTextField();
		this.add(txfAddress);
		txfAddress.setLocation(20, 155);
		txfAddress.setSize(200, 25);

		lblEMail = new JLabel("E-Mail:");
		this.add(lblEMail);
		lblEMail.setLocation(20, 190);
		lblEMail.setSize(100, 25);

		txfEMail = new JTextField();
		this.add(txfEMail);
		txfEMail.setLocation(20, 215);
		txfEMail.setSize(200, 25);

		lblCompanyInfo = new JLabel("CompanyInfo:");
		this.add(lblCompanyInfo);
		lblCompanyInfo.setLocation(20, 245);
		lblCompanyInfo.setSize(100, 25);

		txfCompanyInfo = new JTextField();
		this.add(txfCompanyInfo);
		txfCompanyInfo.setLocation(20, 270);
		txfCompanyInfo.setSize(200, 25);

		lblNotes = new JLabel("Notes:");
		this.add(lblNotes);
		lblNotes.setLocation(20, 300);
		lblNotes.setSize(100, 25);

		txaNotes = new JTextArea();
		this.add(txaNotes);
		txaNotes.setLocation(20, 325);
		txaNotes.setSize(200, 140);

		scpNotes = new JScrollPane(txaNotes);
		this.add(scpNotes);
		scpNotes.setLocation(20, 325);
		scpNotes.setSize(200, 140);

		btnOk = new JButton("Ok");
		this.add(btnOk);
		btnOk.setLocation(20, 500);
		btnOk.setSize(90, 25);
		btnOk.addActionListener(controller);

		btnCancel = new JButton("Cancel");
		this.add(btnCancel);
		btnCancel.setLocation(130, 500);
		btnCancel.setSize(90, 25);
		btnCancel.addActionListener(controller);

		pnlStatus = new JPanel();
		this.add(pnlStatus);
		pnlStatus.setLayout(null);
		pnlStatus.setLocation(10, 470);
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
		private Customer customer;
		private boolean closedByOk = false;

		public void updateView()
		{
			if (customer != null)
			{
				txfName.setText(customer.getName());
				txfPhone.setText(customer.getPhone());
				txfAddress.setText(customer.getAddress());
				txfEMail.setText(customer.getEmail());
				txfCompanyInfo.setText(customer.getCompanyInfo());
				txaNotes.setText(customer.getNotes());
			} else
			{
				txfName.setText("");
				txfPhone.setText("");
				txfAddress.setText("");
				txfEMail.setText("");
				txfCompanyInfo.setText("");
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
				if (txfName.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Name field is empty!");
					return;
				} else if (txfPhone.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Phone field is empty!");
					return;
				} else if (txfAddress.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Address field is empty!");
					return;
				} else if (txfEMail.getText().trim().length() <= 0)
				{
					lblStatus.setText("Error: Ending date field is empty!");
					return;
				}

				/**** update storage ****/

				if (customer == null)
				{
					Storage.createCustomer(txfName.getText().trim(), txfPhone.getText().trim(), txfAddress.getText().trim(), txfEMail.getText().trim(), txfCompanyInfo.getText().trim(), txaNotes.getText().trim());
				} else
				{
					Storage.updateCustomer(customer, txfName.getText().trim(), txfPhone.getText().trim(), txfAddress.getText().trim(), txfEMail.getText().trim(), txfCompanyInfo.getText().trim(), txaNotes.getText().trim());
				}

				closedByOk = true;
				CustomersCUDialog.this.setVisible(false);
			}

			if (e.getSource() == btnCancel)
			{
				closedByOk = false;
				CustomersCUDialog.this.setVisible(false);
			}
		}
	}
}