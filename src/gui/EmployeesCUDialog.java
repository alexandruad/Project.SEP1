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

import model.Chauffeur;
import model.Customer;
import storage.Storage;

public class EmployeesCUDialog extends JDialog
{
   // Note: null is a valid value of customer.
   public EmployeesCUDialog(JFrame owner, String title, Chauffeur chauffeur)
   {
      super(owner);
      controller.chauffeur = chauffeur;

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

   private JLabel lblName,lblAddress, lblPhone, lblEmail, lblType, lblWishes,lblStatus,lblAvailable;
   private JTextField txfName, txfPhone, txfAddress, txfEmail,txfType,txfAvailable;
   private JTextArea txaWishes;
   private JButton btnOk, btnCancel;
   private JPanel pnlStatus;
   private JScrollPane scpWishes;

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

      lblType = new JLabel("Type: ");
      this.add(lblType);
      lblType.setLocation(20, 190);
      lblType.setSize(100, 25);

      txfType = new JTextField();
      this.add(txfType);
      txfType.setLocation(20, 215);
      txfType.setSize(200, 25);

      lblAddress = new JLabel("Address:");
      this.add(lblAddress);
      lblAddress.setLocation(20, 70);
      lblAddress.setSize(100, 25);

      txfAddress = new JTextField();
      this.add(txfAddress);
      txfAddress.setLocation(20, 95);
      txfAddress.setSize(200, 25);

      lblEmail = new JLabel("E-Mail:");
      this.add(lblEmail);
      lblEmail.setLocation(20, 130);
      lblEmail.setSize(100, 25);

      txfEmail = new JTextField();
      this.add(txfEmail);
      txfEmail.setLocation(20, 155);
      txfEmail.setSize(200, 25);
      
      lblPhone = new JLabel("Phone:");
      this.add(lblPhone);
      lblPhone.setLocation(20, 250);
      lblPhone.setSize(100, 25);
      
      txfPhone = new JTextField();
      this.add(txfPhone);
      txfPhone.setLocation(20, 275);
      txfPhone.setSize(200, 25);
      
      lblAvailable = new JLabel("Available:");
      this.add(lblAvailable);
      lblAvailable.setLocation(20, 300);
      lblAvailable.setSize(100, 25);
      
      txfAvailable = new JTextField();
      this.add(txfAvailable);
      txfAvailable.setLocation(20, 325);
      txfAvailable.setSize(200, 25);
      
      lblWishes = new JLabel("Wishes:");
      this.add(lblWishes);
      lblWishes.setLocation(20, 350);
      lblWishes.setSize(100, 25);

      txaWishes = new JTextArea();
      this.add(txaWishes);
      txaWishes.setLocation(20, 375);
      txaWishes.setSize(200, 90);

      scpWishes = new JScrollPane(txaWishes);
      this.add(scpWishes);
      scpWishes.setLocation(20, 375);
      scpWishes.setSize(200, 90);

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
      private Chauffeur chauffeur;
      private boolean closedByOk = false;

      public void updateView()
      {
         if (chauffeur != null)
         {
            txfType.setText(chauffeur.getType());
            txfName.setText(chauffeur.getName());
            txfPhone.setText(chauffeur.getPhone());
            txfAddress.setText(chauffeur.getAddress());
            txfEmail.setText(chauffeur.getEmail());
            txaWishes.setText(chauffeur.getWishesForTrips());
            if(chauffeur.getAvailability()==true){
               txfAvailable.setText("Yes");}
               if(chauffeur.getAvailability()==false){
                  txfAvailable.setText("No");}
            
         } else
         {
            txfType.setText("");
            txfName.setText("");
            txfPhone.setText("");
            txfAddress.setText("");
            txfEmail.setText("");
            txaWishes.setText("");
            txfAvailable.setText("");
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
            } else if (txfEmail.getText().trim().length() <= 0)
            {
               lblStatus.setText("Error: Email field is empty!");
               return;
            }

            /**** update storage ****/

            if (chauffeur == null)
            {
               Storage.createChauffeur(txfType.getText().trim(),txfName.getText().trim(), txfPhone.getText().trim(), txfAddress.getText().trim(), txfEmail.getText().trim(), txaWishes.getText().trim());
            } else
            {
               Storage.updateChauffeur(chauffeur, txfType.getText().trim(),txfName.getText().trim(), txfPhone.getText().trim(), txfAddress.getText().trim(), txfEmail.getText().trim(), txaWishes.getText().trim());
            }

            closedByOk = true;
            EmployeesCUDialog.this.setVisible(false);
         }

         if (e.getSource() == btnCancel)
         {
            closedByOk = false;
            EmployeesCUDialog.this.setVisible(false);
         }
      }
   }
}