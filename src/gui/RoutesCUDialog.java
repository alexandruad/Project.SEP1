package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import storage.Storage;
import model.Chauffeur;
import model.Vehicle;
import model.Route;
import model.DateInterval;
import model.Date;
import model.ChauffeurList;
import model.VehicleList;

public class RoutesCUDialog extends JDialog
{
   // Note: null is a valid value of employee.
   public RoutesCUDialog(JFrame owner, String title, Route route)
   {
      super(owner);
      controller.route = route;

      this.setTitle(title);
      this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      this.setLocationRelativeTo(owner);
      this.setSize(400, 650);
      this.setLocation(300, 50);
      this.setModal(true);
      this.setResizable(false);
      this.getContentPane().setLayout(null);

      this.initContent();
   }

   // -------------------------------------------------------------------------

   private JLabel lblRoutes, lblDestination, lblRouteNumber, lblStartDate,
         lblEndDate, lblPrice, lblChauffeurs, lblVehicles, lblStops, lblStatus,
         lblAddChauffeur, lblRemoveChauffeur, lblAddVehicle, lblRemoveVehicle,
         lblH, lblD, lblM, lblY;
   private JTextField txfDestination, txfRouteNumber, txfStartDateH,
         txfStartDateD, txfStartDateM, txfStartDateY, txfEndDateH, txfEndDateD,
         txfEndDateM, txfEndDateY, txfPrice;
   private JButton btnOk, btnCancel;
   private JPanel pnlStatus;
   private JScrollPane scpAddChauffeur, scpRemoveChauffeur, scpAddVehicle,
         scpRemoveVehicle;
   private JList<Chauffeur> lstAddChauffeur, lstRemoveChauffeur;
   private JList<Vehicle> lstAddVehicle, lstRemoveVehicle;
   private DefaultListModel<Chauffeur> lstAddChauffeurModel,
         lstRemoveChauffeurModel;
   private DefaultListModel<Vehicle> lstAddVehicleModel, lstRemoveVehicleModel;

   private void initContent()
   {
      lblDestination = new JLabel("Destination:");
      this.add(lblDestination);
      lblDestination.setLocation(20, 10);
      lblDestination.setSize(100, 25);

      txfDestination = new JTextField();
      this.add(txfDestination);
      txfDestination.setLocation(20, 35);
      txfDestination.setSize(150, 25);

      lblRouteNumber = new JLabel("Route number:");
      this.add(lblRouteNumber);
      lblRouteNumber.setLocation(20, 70);
      lblRouteNumber.setSize(100, 25);

      txfRouteNumber = new JTextField();
      this.add(txfRouteNumber);
      txfRouteNumber.setLocation(20, 95);
      txfRouteNumber.setSize(150, 25);

      lblStartDate = new JLabel("Starting Date:");
      this.add(lblStartDate);
      lblStartDate.setLocation(20, 130);
      lblStartDate.setSize(100, 25);

      txfStartDateH = new JTextField();
      this.add(txfStartDateH);
      txfStartDateH.setLocation(80, 155);
      txfStartDateH.setSize(40, 25);

      txfStartDateD = new JTextField();
      this.add(txfStartDateD);
      txfStartDateD.setLocation(125, 155);
      txfStartDateD.setSize(40, 25);

      txfStartDateM = new JTextField();
      this.add(txfStartDateM);
      txfStartDateM.setLocation(170, 155);
      txfStartDateM.setSize(40, 25);

      txfStartDateY = new JTextField();
      this.add(txfStartDateY);
      txfStartDateY.setLocation(215, 155);
      txfStartDateY.setSize(60, 25);

      lblH = new JLabel("h");
      this.add(lblH);
      lblH.setLocation(100, 180);
      lblH.setSize(20, 25);

      lblD = new JLabel("d");
      this.add(lblD);
      lblD.setLocation(145, 180);
      lblD.setSize(20, 25);

      lblM = new JLabel("m");
      this.add(lblM);
      lblM.setLocation(190, 180);
      lblM.setSize(20, 25);

      lblY = new JLabel("y");
      this.add(lblY);
      lblY.setLocation(245, 180);
      lblY.setSize(20, 25);

      lblEndDate = new JLabel("Ending Date:");
      this.add(lblEndDate);
      lblEndDate.setLocation(20, 190);
      lblEndDate.setSize(100, 25);

      txfEndDateH = new JTextField();
      this.add(txfEndDateH);
      txfEndDateH.setLocation(80, 215);
      txfEndDateH.setSize(40, 25);

      txfEndDateD = new JTextField();
      this.add(txfEndDateD);
      txfEndDateD.setLocation(125, 215);
      txfEndDateD.setSize(40, 25);

      txfEndDateM = new JTextField();
      this.add(txfEndDateM);
      txfEndDateM.setLocation(170, 215);
      txfEndDateM.setSize(40, 25);

      txfEndDateY = new JTextField();
      this.add(txfEndDateY);
      txfEndDateY.setLocation(215, 215);
      txfEndDateY.setSize(60, 25);

      lblPrice = new JLabel("Price:");
      this.add(lblPrice);
      lblPrice.setLocation(20, 245);
      lblPrice.setSize(100, 25);

      txfPrice = new JTextField();
      this.add(txfPrice);
      txfPrice.setLocation(20, 270);
      txfPrice.setSize(150, 25);

      lblAddChauffeur = new JLabel("Add Chauffeur:");
      this.add(lblAddChauffeur);
      lblAddChauffeur.setLocation(200, 10);
      lblAddChauffeur.setSize(100, 25);

      lstAddChauffeurModel = new DefaultListModel<>();

      lstAddChauffeur = new JList<Chauffeur>(lstAddChauffeurModel);
      lstAddChauffeur
            .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      lstAddChauffeur.addListSelectionListener(controller);

      scpAddChauffeur = new JScrollPane(lstAddChauffeur);
      this.add(scpAddChauffeur);
      scpAddChauffeur.setLocation(200, 35);
      scpAddChauffeur.setSize(180, 85);

      lblRemoveChauffeur = new JLabel("Remove Chauffeur:");
      this.add(lblRemoveChauffeur);
      lblRemoveChauffeur.setLocation(200, 130);
      lblRemoveChauffeur.setSize(100, 25);

      lstRemoveChauffeurModel = new DefaultListModel<>();

      lstRemoveChauffeur = new JList<Chauffeur>(lstRemoveChauffeurModel);
      lstRemoveChauffeur
            .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      lstRemoveChauffeur.addListSelectionListener(controller);

      scpRemoveChauffeur = new JScrollPane(lstRemoveChauffeur);
      this.add(scpRemoveChauffeur);
      scpRemoveChauffeur.setLocation(200, 155);
      scpRemoveChauffeur.setSize(180, 85);

      lblAddVehicle = new JLabel("Add Vehicle:");
      this.add(lblAddVehicle);
      lblAddVehicle.setLocation(200, 275);
      lblAddVehicle.setSize(100, 25);

      lstAddVehicleModel = new DefaultListModel<>();

      lstAddVehicle = new JList<Vehicle>(lstAddVehicleModel);
      lstAddVehicle
            .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      lstAddVehicle.addListSelectionListener(controller);

      scpAddVehicle = new JScrollPane(lstAddVehicle);
      this.add(scpAddVehicle);
      scpAddVehicle.setLocation(200, 300);
      scpAddVehicle.setSize(180, 85);

      lblRemoveVehicle = new JLabel("Remove Vehicle:");
      this.add(lblRemoveVehicle);
      lblRemoveVehicle.setLocation(200, 415);
      lblRemoveVehicle.setSize(100, 25);

      lstRemoveVehicleModel = new DefaultListModel<>();

      lstRemoveVehicle = new JList<Vehicle>(lstRemoveVehicleModel);
      lstRemoveVehicle
            .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      lstRemoveVehicle.addListSelectionListener(controller);

      scpRemoveVehicle = new JScrollPane(lstRemoveVehicle);
      this.add(scpRemoveVehicle);
      scpRemoveVehicle.setLocation(200, 440);
      scpRemoveVehicle.setSize(180, 85);

      btnOk = new JButton("Ok");
      this.add(btnOk);
      btnOk.setLocation(200, 550);
      btnOk.setSize(70, 25);
      btnOk.addActionListener(controller);

      btnCancel = new JButton("Cancel");
      this.add(btnCancel);
      btnCancel.setLocation(300, 550);
      btnCancel.setSize(80, 25);
      btnCancel.addActionListener(controller);

      pnlStatus = new JPanel();
      this.add(pnlStatus);
      pnlStatus.setLayout(null);
      pnlStatus.setLocation(10, 335);
      pnlStatus.setSize(375, 27);
      pnlStatus.setBorder(new EmptyBorder(0, 0, 0, 0));

      lblStatus = new JLabel("");
      pnlStatus.add(lblStatus);
      lblStatus.setLocation(5, 1);
      lblStatus.setSize(194, 25);
      lblStatus.setForeground(Color.RED);

      // initialize this view
      controller.fillLstChauffeurModel();
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
      private Route route;
      private boolean closedByOk = false;

      public void fillLstChauffeurModel()
      {
         lstAddChauffeurModel.clear();
         for (Chauffeur chauffeur : Storage.getChauffeurList().getChauffeurs())
         {
            lstAddChauffeurModel.addElement(chauffeur);
         }
         lstAddVehicleModel.clear();
         for (Vehicle vehicle : Storage.getVehicleList().getVehicles())
         {
            lstAddVehicleModel.addElement(vehicle);
         }
      }

      public void updateView()
      {
         if (route != null)
         {
            txfDestination.setText(route.getDestination());
            txfRouteNumber.setText(route.getRouteNumber());
            txfStartDateH.setText(Integer.toString(route.getDateInterval()
                  .getStartDate().getTime()));
            txfStartDateD.setText(Integer.toString(route.getDateInterval()
                  .getStartDate().getDay()));
            txfStartDateM.setText(Integer.toString(route.getDateInterval()
                  .getStartDate().getMonth()));
            txfStartDateY.setText((Integer.toString(route.getDateInterval()
                  .getStartDate().getYear())));
            txfEndDateH.setText((Integer.toString(route.getDateInterval()
                  .getEndDate().getTime())));
            txfEndDateD.setText((Integer.toString(route.getDateInterval()
                  .getEndDate().getDay())));
            txfEndDateM.setText((Integer.toString(route.getDateInterval()
                  .getEndDate().getMonth())));
            txfEndDateY.setText((Integer.toString(route.getDateInterval()
                  .getEndDate().getYear())));
            txfPrice.setText("" + route.getPricePredefined());
            if (route.getChauffeurs() == null)
            {
               fillLstChauffeurModel();
            }
            else
            {
               lstAddChauffeurModel.clear();
               int match = 0;
               for (Chauffeur chauffeur : Storage.getChauffeurList()
                     .getChauffeurs())
               {
                  for (Chauffeur chauffeur2 : route.getChauffeurs()
                        .getChauffeurs())
                  {
                     if (chauffeur == chauffeur2)
                     {
                        match += 1;
                     }
                  }
                  if (match == 0)
                  {
                     lstAddChauffeurModel.addElement(chauffeur);
                  }
                  else
                  {
                     lstRemoveChauffeurModel.addElement(chauffeur);
                  }
                  match = 0;
               }

               lstAddVehicleModel.clear();
               match = 0;
               for (Vehicle vehicle : Storage.getVehicleList().getVehicles())
               {
                  for (Vehicle vehicle2 : route.getVehicles().getVehicles())
                  {
                     if (vehicle == vehicle2)
                     {
                        match += 1;
                     }
                  }
                  if (match == 0)
                  {
                     lstAddVehicleModel.addElement(vehicle);
                  }
                  else
                  {
                     lstRemoveVehicleModel.addElement(vehicle);
                  }
                  match = 0;
               }
            }
         }
         else
         {
            lblRemoveChauffeur.setVisible(false);
            scpRemoveChauffeur.setVisible(false);
            lblRemoveVehicle.setVisible(false);
            scpRemoveVehicle.setVisible(false);
            txfDestination.setText("");
            txfRouteNumber.setText("");
            txfStartDateH.setText("");
            txfStartDateD.setText("");
            txfStartDateM.setText("");
            txfStartDateY.setText("");
            txfEndDateH.setText("");
            txfEndDateD.setText("");
            txfEndDateM.setText("");
            txfEndDateY.setText("");
            txfPrice.setText("");
            fillLstChauffeurModel();
         }
      }

      // This method is called when a button is pressed.
      @Override
      public void actionPerformed(ActionEvent e)
      {
         lblStatus.setText("");

         if (e.getSource() == btnOk)
         {
            if (txfDestination.getText().trim().length() == 0)
            {
               lblStatus.setText("Error: Destination field is empty!");
               return;
            }
            if (txfRouteNumber.getText().trim().length() == 0)
            {
               lblStatus.setText("Error: Route number field is empty!");
               return;
            }
            if (txfStartDateH.getText().trim().length() == 0)
            {
               lblStatus.setText("Error: Starting date field is empty!");
               return;
            }
            if (txfEndDateH.getText().trim().length() == 0)
            {
               lblStatus.setText("Error: Ending date field is empty!");
               return;
            }
            if (txfPrice.getText().trim().length() == 0)
            {
               lblStatus.setText("Error: Price field is empty!");
               return;
            }
            double price = 0;
            try
            {
               price = Double.parseDouble(txfPrice.getText().trim());
            }
            catch (NumberFormatException ex)
            {
               lblStatus.setText("Error: Price must be an double!");
               return;
            }

            /**** update storage ****/
            ChauffeurList addChauffeur = new ChauffeurList();
            ChauffeurList removeChauffeur = new ChauffeurList();
            VehicleList addVehicle = new VehicleList();
            VehicleList removeVehicle = new VehicleList();
            if (route == null)
            {
               while (!lstAddChauffeur.isSelectionEmpty())
               {
                  addChauffeur.addChauffeur(lstAddChauffeurModel
                        .getElementAt(lstAddChauffeur.getSelectedIndex()));
                  lstAddChauffeurModel.remove(lstAddChauffeur
                        .getSelectedIndex());
               }
               while (!lstAddVehicle.isSelectionEmpty())
               {
                  addVehicle.addVehicle(lstAddVehicleModel
                        .getElementAt(lstAddVehicle.getSelectedIndex()));
                  lstAddVehicleModel.remove(lstAddVehicle.getSelectedIndex());
               }
               Date date1 = new Date(Integer.parseInt(txfStartDateH.getText()
                     .trim()),
                     Integer.parseInt(txfStartDateD.getText().trim()),
                     Integer.parseInt(txfStartDateM.getText().trim()),
                     Integer.parseInt(txfStartDateY.getText().trim()));
               Date date2 = new Date(
                     Integer.parseInt(txfEndDateH.getText().trim()), 
                     Integer.parseInt(txfEndDateD.getText().trim()),
                     Integer.parseInt(txfStartDateM.getText().trim()),
                     Integer.parseInt(txfStartDateY.getText().trim()));
               DateInterval dateInterval = new DateInterval(date1, date2);

               Storage.createRoute(
                     txfDestination.getText().trim(),
                     dateInterval,// good
                     txfRouteNumber.getText().trim(), addChauffeur, addVehicle,
                     txfPrice.getText().trim());
            }
            else
            {
               while (!lstAddChauffeur.isSelectionEmpty())
               {
                  addChauffeur.addChauffeur(lstAddChauffeurModel
                        .getElementAt(lstAddChauffeur.getSelectedIndex()));
                  lstAddChauffeurModel.remove(lstAddChauffeur
                        .getSelectedIndex());
               }
               while (!lstRemoveChauffeur.isSelectionEmpty())
               {
                  removeChauffeur.addChauffeur(lstRemoveChauffeurModel
                        .getElementAt(lstRemoveChauffeur.getSelectedIndex()));
                  lstRemoveChauffeurModel.remove(lstRemoveChauffeur
                        .getSelectedIndex());
               }
               while (!lstAddVehicle.isSelectionEmpty())
               {
                  addVehicle.addVehicle(lstAddVehicleModel
                        .getElementAt(lstAddVehicle.getSelectedIndex()));
                  lstAddVehicleModel.remove(lstAddVehicle.getSelectedIndex());
               }
               while (!lstRemoveVehicle.isSelectionEmpty())
               {
                  removeVehicle.addVehicle(lstRemoveVehicleModel
                        .getElementAt(lstRemoveVehicle.getSelectedIndex()));
                  lstRemoveVehicleModel.remove(lstRemoveVehicle
                        .getSelectedIndex());
               }
               Date date1 = new Date(route.getDateInterval().getStartDate()
                     .getTime(), route.getDateInterval().getStartDate()
                     .getDay(), route.getDateInterval().getStartDate()
                     .getMonth(), route.getDateInterval().getStartDate()
                     .getYear());
               Date date2 = new Date(route.getDateInterval().getEndDate()
                     .getTime(), route.getDateInterval().getEndDate().getDay(),
                     route.getDateInterval().getEndDate().getMonth(), route
                           .getDateInterval().getEndDate().getYear());
               DateInterval dateInterval = new DateInterval(date1, date2);
               Storage.updateRoute(route, txfDestination.getText().trim(),
                     dateInterval, txfRouteNumber.getText().trim(),
                     addChauffeur, addVehicle, txfPrice.getText().trim());
            }

            closedByOk = true;
            RoutesCUDialog.this.setVisible(false);
         }

         if (e.getSource() == btnCancel)
         {
            closedByOk = false;
            RoutesCUDialog.this.setVisible(false);
         }
      }

      // This method is called when the selection in a list is changed.
      @Override
      public void valueChanged(ListSelectionEvent e)
      {
      }
   }
}