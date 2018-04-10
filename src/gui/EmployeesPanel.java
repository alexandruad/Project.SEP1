package gui;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import model.Chauffeur;
import model.Customer;
import model.CustomerList;
import model.ChauffeurList;
import storage.Storage;
public class EmployeesPanel extends JPanel
{
   private MainFrame frame;

   public EmployeesPanel(MainFrame frame) {
      this.frame = frame;

      this.setPreferredSize(new Dimension(710, 390));
      this.setLayout(null);

      this.initContent();
   }

   // -------------------------------------------------------------------------

   private JLabel lblEmployees, lblName,lblAddress, lblPhone, lblEmail,lblType,lblWishes,lblStatus, lblAvailable;
   private JScrollPane scpEmployees;
   private JList<Chauffeur> lstEmployees;
   private DefaultListModel<Chauffeur> lstEmployeesModel;
   private JTextField txfName,txfAddress,
         txfPhone, txfType, txfEmail, txfNotes,txfAvailable;
   private JTextArea txaWishes;
   private JButton btnAdd, btnEdit, btnRemove;
   private JPanel pnlStatus;
   private JComboBox cbxSearchType;
   private JLabel lblSearchText;
   private JTextField txfSearchText ;

   private void initContent() {
      lblEmployees = new JLabel("Employees");
      this.add(lblEmployees);
      lblEmployees.setLocation(10, 50);
      lblEmployees.setSize(100, 25);

      lstEmployeesModel = new DefaultListModel<>();

      lstEmployees = new JList<Chauffeur>(lstEmployeesModel);
      lstEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      lstEmployees.addListSelectionListener(controller);

      scpEmployees = new JScrollPane(lstEmployees);
      this.add(scpEmployees);
      scpEmployees.setLocation(10, 80);
      scpEmployees.setSize(180, 270);

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

      lblAddress = new JLabel("Address:");
      this.add(lblAddress);
      lblAddress.setLocation(200, 110);
      lblAddress.setSize(100, 25);

      txfAddress = new JTextField();
      this.add(txfAddress);
      txfAddress.setLocation(300, 110);
      txfAddress.setSize(150, 25);
      txfAddress.setEditable(false);
      txfAddress.setFocusable(false);

      lblType = new JLabel("Type:");
      this.add(lblType);
      lblType.setLocation(200, 170);
      lblType.setSize(100, 25);

      txfType = new JTextField();
      this.add(txfType);
      txfType.setLocation(300, 170);
      txfType.setSize(150, 25);
      txfType.setEditable(false);
      txfType.setFocusable(false);

      lblPhone = new JLabel("Phone:");
      this.add(lblPhone);
      lblPhone.setLocation(200, 200);
      lblPhone.setSize(100, 25);

      txfPhone = new JTextField();
      this.add(txfPhone);
      txfPhone.setLocation(300, 200);
      txfPhone.setSize(150, 25);
      txfPhone.setEditable(false);
      txfPhone.setFocusable(false);

      lblEmail = new JLabel("Email:");
      this.add(lblEmail);
      lblEmail.setLocation(200, 140);
      lblEmail.setSize(100, 25);

      txfEmail = new JTextField();
      this.add(txfEmail);
      txfEmail.setLocation(300, 140);
      txfEmail.setSize(150, 25);
      txfEmail.setEditable(false);
      txfEmail.setFocusable(false);

      lblAvailable = new JLabel("Available:");
      this.add(lblAvailable);
      lblAvailable.setLocation(200, 230);
      lblAvailable.setSize(100, 25);
      
      txfAvailable = new JTextField();
      this.add(txfAvailable);
      txfAvailable.setLocation(300, 230);
      txfAvailable.setSize(150, 25);
      txfAvailable.setEditable(false);
      txfAvailable.setFocusable(false);
      
      lblWishes = new JLabel("Wishes:");
      this.add(lblWishes);
      lblWishes.setLocation(200, 260);
      lblWishes.setSize(100, 25);
      
      txaWishes = new JTextArea();
      this.add(txaWishes);
      txaWishes.setLocation(300, 260);
      txaWishes.setSize(150, 75);
      txaWishes.setEditable(false);
      txaWishes.setFocusable(false);

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
      
      cbxSearchType = new JComboBox();
      this.add(cbxSearchType);
      cbxSearchType.setLocation(70, 360);
      cbxSearchType.setSize(120, 25);
      cbxSearchType.setEditable(false);
      cbxSearchType.setFocusable(false);

      cbxSearchType.addItem("Name");
      cbxSearchType.addItem("Phone");
      cbxSearchType.addItem("ID");
      cbxSearchType.addItem("Available");
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
            controller.fillLstEmployeesModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
         }

         @Override
         public void removeUpdate(DocumentEvent e)
         {
            controller.fillLstEmployeesModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
         }

         @Override
         public void changedUpdate(DocumentEvent e)
         {
            controller.fillLstEmployeesModel(cbxSearchType.getSelectedItem().toString(), txfSearchText.getText().trim());
         }
      });

      

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
      controller.fillLstEmployeesModel();
   }

public void update()
{
   controller.updateView();
}

// -------------------------------------------------------------------------

private Controller controller = new Controller();

private class Controller implements ActionListener, ListSelectionListener
{
   public void fillLstEmployeesModel()
   {
      lstEmployeesModel.clear();

      for (Chauffeur chauffeur : Storage.getChauffeurList().getChauffeurs())
      {
         lstEmployeesModel.addElement(chauffeur);
      }

   }
   public void fillLstEmployeesModel(String searchType, String searchText)
   {
      lstEmployeesModel.clear();
      ChauffeurList foundChauffeurs;
      if (searchText.length() <= 0)
      {
         foundChauffeurs = Storage.getChauffeurList();
      } else
      {
         foundChauffeurs = Storage.getFilteredChauffeurList(searchType, searchText);
      }

      for (Chauffeur chauffeur : foundChauffeurs.getChauffeurs())
      {
         lstEmployeesModel.addElement(chauffeur);
      }

   }

   public void updateView()
   {

      if (lstEmployees.getSelectedIndex() >= 0)
      {
         Chauffeur chauffeur = lstEmployees.getSelectedValue();
         txfType.setText(chauffeur.getType());
         txfName.setText(chauffeur.getName());
         txfPhone.setText(chauffeur.getPhone());
         txfAddress.setText(chauffeur.getAddress());
         txfEmail.setText(chauffeur.getEmail());
         if(chauffeur.getAvailability()==true){
         txfAvailable.setText("Yes");}
         if(chauffeur.getAvailability()==false){
            txfAvailable.setText("No");}
         txaWishes.setText(chauffeur.getWishesForTrips());
        

      } else
      {
         txfType.setText("");
         txfName.setText("");
         txfPhone.setText("");
         txfAddress.setText("");
         txfEmail.setText("");
         txfAvailable.setText("");
         txaWishes.setText("");
      }
      lblStatus.setText("");

   }

   // This method is called when a button is pressed.
   @Override
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == btnAdd)
      {
         EmployeesCUDialog createEmployeesDialog = new EmployeesCUDialog(frame, "Add Employee", null);
         createEmployeesDialog.setVisible(true);

         // waiting for modal createCustomerDialog to close

         if (createEmployeesDialog.isOKed())
         {
            /**** update view ****/
            this.fillLstEmployeesModel();
            int lastIndex = lstEmployees.getModel().getSize() - 1;
            lstEmployees.setSelectedIndex(lastIndex); // calls
                                            // updateView()
            lstEmployees.ensureIndexIsVisible(lastIndex);
         }
         createEmployeesDialog.dispose(); // release OS resources
         
      }

      if (e.getSource() == btnEdit)
      {
         if (lstEmployees.getSelectedIndex() == -1)
         {
            lblStatus.setText("Error: Please select Employee!");
            return;
         }

         Chauffeur chauffeur = lstEmployees.getSelectedValue();
         EmployeesCUDialog updateEmployeesDialog = new EmployeesCUDialog(frame, "Update Employee", chauffeur);
         updateEmployeesDialog.setVisible(true);

         // waiting for modal updateCustomersDialog to close

         if (updateEmployeesDialog.isOKed())
         {
            /**** update view ****/
            int selectedIndex = lstEmployees.getSelectedIndex();
            this.fillLstEmployeesModel();
            lstEmployees.setSelectedIndex(selectedIndex); // calls
                                               // updateView()
            lstEmployees.ensureIndexIsVisible(selectedIndex);
         }
         updateEmployeesDialog.dispose(); // release OS resources
      }

      if (e.getSource() == btnRemove)
      {
         if (lstEmployees.getSelectedIndex() == -1)
         {
            lblStatus.setText("Error: Employee is not selected!");
            return;
         }

         Chauffeur chauffeur = lstEmployees.getSelectedValue();

         int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Employee Customers", JOptionPane.YES_NO_OPTION);
         if (answer == JOptionPane.YES_OPTION)
         {

            /**** update storage ****/
            Storage.deleteEmployee(chauffeur);

            /**** update view ****/
            this.fillLstEmployeesModel();
            this.updateView();
         }
      }
   }

   // This method is called when the selection in a list is changed.
   @Override
   public void valueChanged(ListSelectionEvent e)
   {
      if (e.getSource() == lstEmployees)
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
