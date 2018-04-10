package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Customer;
import model.CustomerList;
import storage.Storage;

public class CustomersPanel extends JPanel
{
	private MainFrame frame;

	public CustomersPanel(MainFrame frame)
	{
		this.frame = frame;

		this.setPreferredSize(new Dimension(710, 500));
		this.setLayout(null);

		this.initContent();
	}

	// -------------------------------------------------------------------------

	private JLabel lblCustomers, lblSearchType, lblSearchText, lblName, lblPhone, lblAddress, lblEMail, lblCompanyInfo, lblNotes, lblStatus;
	private JScrollPane scpCustomers, scpNotes;
	private JList<Customer> lstCustomers;
	private DefaultListModel<Customer> lstCustomersModel;
	private JTextField txfSearchText, txfName, txfPhone, txfAddress, txfEMail, txfCompanyInfo;
	private JComboBox cbxSearchType;
	private JTextArea txaNotes;
	private JButton btnCreate, btnEdit, btnDelete;
	private JPanel pnlStatus;

	private void initContent()
	{
		lblCustomers = new JLabel("Customers");
		this.add(lblCustomers);
		lblCustomers.setLocation(10, 50);
		lblCustomers.setSize(100, 25);

		lstCustomersModel = new DefaultListModel<>();

		lstCustomers = new JList<Customer>(lstCustomersModel);
		lstCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstCustomers.addListSelectionListener(controller);

		scpCustomers = new JScrollPane(lstCustomers);
		this.add(scpCustomers);
		scpCustomers.setLocation(10, 80);
		scpCustomers.setSize(180, 270);

		lblSearchType = new JLabel("Type:");
		this.add(lblSearchType);
		lblSearchType.setLocation(10, 360);
		lblSearchType.setSize(60, 25);

		cbxSearchType = new JComboBox();
		this.add(cbxSearchType);
		cbxSearchType.setLocation(70, 360);
		cbxSearchType.setSize(120, 25);
		cbxSearchType.setEditable(false);
		cbxSearchType.setFocusable(false);

		cbxSearchType.addItem("Name");
		cbxSearchType.addItem("Phone");
		cbxSearchType.addItem("Frequent count");
		cbxSearchType.addItem("Company Info");
		cbxSearchType.addActionListener(controller);

		lblSearchText = new JLabel("Search:");
		this.add(lblSearchText);
		lblSearchText.setLocation(10, 390);
		lblSearchText.setSize(60, 25);

		txfSearchText = new JTextField();
		this.add(txfSearchText);
		txfSearchText.setLocation(70, 390);
		txfSearchText.setSize(120, 25);
		txfSearchText.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				controller.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				controller.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				controller.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
			}
		});

		lblName = new JLabel("Name:");
		this.add(lblName);
		lblName.setLocation(200, 80);
		lblName.setSize(60, 25);

		txfName = new JTextField();
		this.add(txfName);
		txfName.setLocation(300, 80);
		txfName.setSize(150, 25);
		txfName.setEditable(false);
		txfName.setFocusable(false);

		lblPhone = new JLabel("Phone:");
		this.add(lblPhone);
		lblPhone.setLocation(200, 110);
		lblPhone.setSize(60, 25);

		txfPhone = new JTextField();
		this.add(txfPhone);
		txfPhone.setLocation(300, 110);
		txfPhone.setSize(150, 25);
		txfPhone.setEditable(false);
		txfPhone.setFocusable(false);

		lblAddress = new JLabel("Address:");
		this.add(lblAddress);
		lblAddress.setLocation(200, 140);
		lblAddress.setSize(70, 25);

		txfAddress = new JTextField();
		this.add(txfAddress);
		txfAddress.setLocation(300, 140);
		txfAddress.setSize(150, 25);
		txfAddress.setEditable(false);
		txfAddress.setFocusable(false);

		lblEMail = new JLabel("E-Mail:");
		this.add(lblEMail);
		lblEMail.setLocation(200, 170);
		lblEMail.setSize(70, 25);

		txfEMail = new JTextField();
		this.add(txfEMail);
		txfEMail.setLocation(300, 170);
		txfEMail.setSize(150, 25);
		txfEMail.setEditable(false);
		txfEMail.setFocusable(false);

		lblCompanyInfo = new JLabel("Company Info:");
		this.add(lblCompanyInfo);
		lblCompanyInfo.setLocation(200, 200);
		lblCompanyInfo.setSize(100, 25);

		txfCompanyInfo = new JTextField();
		this.add(txfCompanyInfo);
		txfCompanyInfo.setLocation(300, 200);
		txfCompanyInfo.setSize(150, 25);
		txfCompanyInfo.setEditable(false);
		txfCompanyInfo.setFocusable(false);

		lblNotes = new JLabel("Notes:");
		this.add(lblNotes);
		lblNotes.setLocation(200, 230);
		lblNotes.setSize(60, 25);

		txaNotes = new JTextArea();
		this.add(txaNotes);
		txaNotes.setLocation(300, 230);
		txaNotes.setSize(150, 120);
		txaNotes.setEditable(false);
		txaNotes.setFocusable(false);

		scpNotes = new JScrollPane(txaNotes);
		this.add(scpNotes);
		scpNotes.setLocation(300, 230);
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
		controller.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
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
		public void fillLstCustomersModel(String searchType, String searchText)
		{
			lstCustomersModel.clear();
			CustomerList foundCustomers;
			if (searchText.length() <= 0)
			{
				foundCustomers = Storage.getCustomerList();
			} else
			{
				foundCustomers = Storage.getFilteredCustomerList(searchType, searchText);
			}

			for (Customer customer : foundCustomers.getCustomers())
			{
				lstCustomersModel.addElement(customer);
			}

		}

		public void updateView()
		{

			if (lstCustomers.getSelectedIndex() >= 0)
			{
				Customer customer = lstCustomers.getSelectedValue();
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
			lblStatus.setText("");

		}

		// This method is called when a button is pressed or option is selected
		// in combobox.
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnCreate)
			{
				CustomersCUDialog createCustomersDialog = new CustomersCUDialog(frame, "Create Customer", null);
				createCustomersDialog.setVisible(true);

				// waiting for modal createCustomerDialog to close

				if (createCustomersDialog.isOKed())
				{
					/**** update view ****/
					this.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
					int lastIndex = lstCustomers.getModel().getSize() - 1;
					lstCustomers.setSelectedIndex(lastIndex); // calls
															  // updateView()
					lstCustomers.ensureIndexIsVisible(lastIndex);
				}
				createCustomersDialog.dispose(); // release OS resources
			}

			if (e.getSource() == btnEdit)
			{
				if (lstCustomers.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Please select customer!");
					return;
				}

				Customer customer = lstCustomers.getSelectedValue();
				CustomersCUDialog updateCustomersDialog = new CustomersCUDialog(frame, "Update Customer", customer);
				updateCustomersDialog.setVisible(true);

				// waiting for modal updateCustomersDialog to close

				if (updateCustomersDialog.isOKed())
				{
					/**** update view ****/
					int selectedIndex = lstCustomers.getSelectedIndex();
					this.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
					lstCustomers.setSelectedIndex(selectedIndex); // calls
																  // updateView()
					lstCustomers.ensureIndexIsVisible(selectedIndex);
				}
				updateCustomersDialog.dispose(); // release OS resources
			}

			if (e.getSource() == btnDelete)
			{
				if (lstCustomers.getSelectedIndex() == -1)
				{
					lblStatus.setText("Error: Customer is not selected!");
					return;
				}

				Customer customer = lstCustomers.getSelectedValue();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Customers", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{

					/**** update storage ****/
					Storage.deleteCustomer(customer);

					/**** update view ****/
					this.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
					this.updateView();
				}

			}
			if (e.getSource() == cbxSearchType)
			{
				this.fillLstCustomersModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
			}
		}

		// This method is called when the selection in a list is changed.
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getSource() == lstCustomers)
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