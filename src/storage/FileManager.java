package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import model.ChauffeurList;
import model.CustomerList;
import model.ReservationList;
import model.RoutesList;
import model.VehicleList;

@SuppressWarnings("serial")
public class FileManager implements Serializable {
	File file;

	String FILENAME = "ViaBusFile.bin";

	public FileManager(String FILENAME) {
		file = new File(FILENAME);
	}

	@SuppressWarnings("unchecked")
	public ChauffeurList getChauffeurList() throws FileNotFoundException, IOException, ClassNotFoundException {

		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		list = (ArrayList<Object>) is.readObject();
		is.close();

		ChauffeurList chauffeurs = (ChauffeurList) list.get(0);
		return chauffeurs;

	}

	@SuppressWarnings("unchecked")
	public CustomerList getCustomerList() throws FileNotFoundException, IOException, ClassNotFoundException {

		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		list = (ArrayList<Object>) is.readObject();
		is.close();

		CustomerList customers = (CustomerList) list.get(1);
		return customers;

	}

	@SuppressWarnings("unchecked")
	public VehicleList getVehicleList() throws FileNotFoundException, IOException, ClassNotFoundException {

		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		list = (ArrayList<Object>) is.readObject();
		is.close();

		VehicleList vehicles = (VehicleList) list.get(2);
		return vehicles;

	}

	@SuppressWarnings("unchecked")
	public RoutesList getRouteList() throws FileNotFoundException, IOException, ClassNotFoundException {

		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		list = (ArrayList<Object>) is.readObject();
		is.close();

		RoutesList routes = (RoutesList) list.get(3);
		return routes;

	}

	@SuppressWarnings("unchecked")
	public ReservationList getReservationList() throws FileNotFoundException, IOException, ClassNotFoundException {

		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		list = (ArrayList<Object>) is.readObject();
		is.close();

		ReservationList reservations = (ReservationList) list.get(4);
		return reservations;
	}

	public void writeList(ChauffeurList chauffeurs, CustomerList customers, VehicleList vehicles, RoutesList routes, ReservationList reservations) {
		ArrayList<Object> list = new ArrayList<>();

		list.add(chauffeurs);
		list.add(customers);
		list.add(vehicles);
		list.add(routes);
		list.add(reservations);

		ObjectOutputStream os = null;

		try {
			os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(list);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (os != null) {
			try {
				os.writeObject(list);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Object> readList() {
		ArrayList<Object> list = new ArrayList<>();
		ObjectInputStream is = null;

		try {
			is = new ObjectInputStream(new FileInputStream(file));
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		if (is != null) {
			try {
				list = (ArrayList<Object>) is.readObject();
				is.close();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return list;

	}

}
