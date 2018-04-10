package storage;

import java.io.IOException;
import model.Chauffeur;
import model.ChauffeurList;
import model.Customer;
import model.CustomerList;
import model.DateInterval;
import model.PassengerList;
import model.Reservation;
import model.ReservationList;
import model.Route;
import model.RoutesList;
import model.Vehicle;
import model.VehicleList;

public class Storage
{
	private static CustomerList customerList;
	private static ChauffeurList chauffeurList;
	private static VehicleList vehicleList;
	private static RoutesList routeList;
	private static ReservationList reservationList;

	private static FileManager viaBusFile;
	public static final String FILENAME = "ViaBusFile.bin";

	// -------------------------------------------------------------------------
	// Saving changes to the file.
	// taking all 5 lists and putting them into a big list and saving that in
	// binary to a file

	public static void saveL()
	{
		viaBusFile.writeList(chauffeurList, customerList, vehicleList, routeList, reservationList);
	}

	// -------------------------------------------------------------------------
	// Constructor reading from the file or creating new lists
	// If classes are not found it will create new lists and save them to the
	// file

	public Storage()
	{
		try
		{
			viaBusFile = new FileManager(FILENAME);

			chauffeurList = viaBusFile.getChauffeurList();
			customerList = viaBusFile.getCustomerList();
			vehicleList = viaBusFile.getVehicleList();
			routeList = viaBusFile.getRouteList();
			reservationList = viaBusFile.getReservationList();

		} catch (ClassNotFoundException | IOException e)
		{
			chauffeurList = new ChauffeurList();
			customerList = new CustomerList();
			vehicleList = new VehicleList();
			routeList = new RoutesList();
			reservationList = new ReservationList();
			viaBusFile.writeList(chauffeurList, customerList, vehicleList, routeList, reservationList);
		}

	}

	// -------------------------------------------------------------------------

	/**
	 * Returns a list of customers.
	 */
	// return COPY of customers
	public static CustomerList getCustomerList()
	{
		return customerList;
	}

	public static CustomerList getFilteredCustomerList(String searchType, String searchText)
	{
		CustomerList filteredCustomerList = new CustomerList();
		switch (searchType.toLowerCase().trim())
		{
		case "name":
			for (Customer customer : customerList.getCustomers())
			{
				if (customer.getName().toLowerCase().trim().contains(searchText.toLowerCase().trim()))
				{
					filteredCustomerList.addCustomer(customer);
				}

			}
			break;
		case "phone":
			for (Customer customer : customerList.getCustomers())
			{
				if (customer.getPhone().toLowerCase().trim().contains(searchText.toLowerCase().trim()))
				{
					filteredCustomerList.addCustomer(customer);
				}
			}
			break;
		case "frequent count":
			if (searchText.matches("^\\d+$"))
			{
				for (Customer customer : customerList.getCustomers())
				{
					if (customer.getReservationList().getReservations().size() >= Integer.parseInt(searchText))
					{
						filteredCustomerList.addCustomer(customer);
					}
				}
			}
			break;
		case "company info":
			for (Customer customer : customerList.getCustomers())
			{
				if (customer.getCompanyInfo().toLowerCase().trim().contains(searchText.toLowerCase().trim()))
				{
					filteredCustomerList.addCustomer(customer);
				}
			}

			break;
		default:
			break;
		}
		return filteredCustomerList;
	}

	public static void addCustomer(Customer customer)
	{
		customerList.addCustomer(customer);
		saveL();

	}

	// -------------------------------------------------------------------------

	/**
	 * Creates new customer
	 */
	public static Customer createCustomer(String name, String phone, String address, String email, String companyInfo, String notes)
	{
		Customer customer = new Customer(name, phone, address, email, companyInfo, notes);
		customerList.addCustomer(customer);
		saveL();
		return customer;
	}

	/**
	 * Updates the customer.
	 */
	public static void updateCustomer(Customer customer, String name, String phone, String address, String email, String companyInfo, String notes)
	{
		customer.setName(name);
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setCompanyInfo(companyInfo);
		customer.setNotes(notes);
		saveL();
	}

	/**
	 * Remove customer.
	 */
	public static void deleteCustomer(Customer customer)
	{
		customerList.deleteCustomer(customer);
		saveL();
	}

	// -------------------------------------------------------------------------

	/**
	 * Returns a list of chauffeurs.
	 */
	// return COPY of customers
	public static ChauffeurList getChauffeurList()
	{
		return chauffeurList;
	}

	public static void addChauffeur(Chauffeur chauffeur)
	{
		chauffeurList.addChauffeur(chauffeur);
		saveL();
	}

	// -------------------------------------------------------------------------

	/**
	 * Creates new chauffeur
	 */
	public static Chauffeur createChauffeur(String type, String name, String address, String phone, String email, String wishesForTrips)
	{
		Chauffeur chauffeur = new Chauffeur(type, name, address, phone, email, wishesForTrips);
		chauffeurList.addChauffeur(chauffeur);
		saveL();
		return chauffeur;

	}

	/**
	 * Updates the chauffeur.
	 */
	public static void updateChauffeur(Chauffeur chauffeur, String type, String name, String phone, String address, String email, String wishesForTrips)
	{
		chauffeur.setName(name);
		chauffeur.setPhone(phone);
		chauffeur.setAddress(address);
		chauffeur.setEmail(email);
		chauffeur.setWishesForTrips(wishesForTrips);
		saveL();
	}

	/**
	 * Remove chauffeur.
	 */
	public static void deleteEmployee(Chauffeur chauffeur)
	{
		chauffeurList.deleteChauffeur(chauffeur.getId());
		saveL();
	}

	public static ChauffeurList getFilteredChauffeurList(String searchType, String searchText)
	{
		ChauffeurList filteredChauffeurList = new ChauffeurList();
		switch (searchType.toLowerCase().trim())
		{
		case "name":
			for (Chauffeur chauffeur : chauffeurList.getChauffeurs())
			{
				if (chauffeur.getName().toLowerCase().trim().contains(searchText.toLowerCase().trim()))
				{
					filteredChauffeurList.addChauffeur(chauffeur);
				}

			}
			break;
		case "phone":
			for (Chauffeur chauffeur : chauffeurList.getChauffeurs())
			{
				if (chauffeur.getPhone().toLowerCase().trim().contains(searchText.toLowerCase().trim()))
				{
					filteredChauffeurList.addChauffeur(chauffeur);
				}
			}
			break;
		case "ID":
			if (searchText.matches("^\\d+$"))
			{
				for (Chauffeur chauffeur : chauffeurList.getChauffeurs())
				{
					if (chauffeur.getId() == Integer.parseInt(searchText))
					{
						filteredChauffeurList.addChauffeur(chauffeur);
					}
				}
			}
			break;
		case "Available":
			for (Chauffeur chauffeur : chauffeurList.getChauffeurs())
			{
				if (chauffeur.getAvailability() == true)
				{
					filteredChauffeurList.addChauffeur(chauffeur);
				}
			}

			break;
		default:
			break;
		}
		return filteredChauffeurList;
	}

	// -------------------------------------------------------------------------

	/**
	 * Returns a list of vehicles.
	 */
	// return COPY of Vehicles
	public static VehicleList getVehicleList()
	{
		return vehicleList;
	}

	public static void addVehicle(Vehicle vehicle)
	{
		vehicleList.addVehicle(vehicle);
		saveL();
	}

	// -------------------------------------------------------------------------

	/**
	 * Creates new vehicle
	 */
	public static Vehicle createVehicle(String regNo, int numberOfSeats, String vehicleType, String amenities)
	{
		Vehicle vehicle = new Vehicle(regNo, numberOfSeats, vehicleType, amenities);
		vehicleList.addVehicle(vehicle);
		saveL();
		return vehicle;
	}

	/**
	 * Updates the vehicle.
	 */
	public static void updateVehicle(Vehicle vehicle, String regNo, int numberOfSeats, String vehicleType, String amenities)
	{
		vehicle.setRegNo(regNo);
		vehicle.setNumberOfSeats(numberOfSeats);
		vehicle.setVehicleType(vehicleType);
		vehicle.setAmenities(amenities);
		saveL();
	}

	/**
	 * Remove route.
	 */
	public static void deleteVehicle(Vehicle vehicle)
	{
		vehicleList.removeVehicle(vehicle);
		saveL();
	}

	// -------------------------------------------------------------------------

	/**
	 * Creates new route.
	 */
	public static Route createRoute(String destination, DateInterval dateInterval, String routeNumber, ChauffeurList chauffeurList, VehicleList vehicleList, String price)
	{
		Route route = new Route(destination, dateInterval, routeNumber, chauffeurList, vehicleList, price);
		routeList.addRoute(route);// ye, blindess kicks in
		saveL();
		return route;
	}

	/**
	 * Updates the Route.
	 */
	public static void updateRoute(Route route, String destination, DateInterval dateInterval, String routeNumber, ChauffeurList chauffeurs, VehicleList vehicles, String price)
	{
		route.setDestination(destination);
		route.setDateInterval(dateInterval);
		route.setRouteNumber(routeNumber);
		route.setChauffeurs(chauffeurs);
		route.setVehicles(vehicles);
		route.setPricePredefined(price);
		saveL();
	}

	/**
	 * Remove vehicle.
	 */
	public static void deleteRoute(Route route)
	{
		routeList.deleteRoute(route.getRouteNumber());
		saveL();
	}

	public static RoutesList getRoutes()
	{
		return routeList;
	}

	// -------------------------------------------------------------------------

	/**
	 * Creates new reservation
	 */
	public static Reservation createReservation(Customer customer, int numberOfSeats, PassengerList passengers, Route route, int price)
	{
		Reservation reservation = new Reservation(customer, numberOfSeats, passengers, route, price);
		reservationList.addReservation(reservation);
		saveL();
		return reservation;
	}

	/**
	 * Updates the reservation.
	 */
	public static void updateReservation(Reservation reservation, int numberOfSeats, PassengerList passengers, Route route, int price)
	{
		// reservation.setCustomer(customer);
		reservation.setNumberOfSeats(numberOfSeats);
		reservation.setRoute(route);
		reservation.setPrice(price);
		saveL();
	}

	/**
	 * Returns reservation list
	 */
	public static ReservationList getReservationList()
	{
		return reservationList;
	}

	/**
	 * Remove reservation.
	 */
	public static void deleteReservation(Reservation reservation)
	{
		reservationList.removeReservation(reservation.getReservationId());
		saveL();
	}

	// -------------------------------------------------------------------------
}
