package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable
{

	private String destination; // Final destination
	private DateInterval dateInterval; // Time interval from myDate
	private String routeNumber; // Index of route

	private ArrayList<String> stops; // Stores stops
	private String pricePredefined;
	ChauffeurList chauffeurList;
	VehicleList vehicleList;

   public Route(String destination, DateInterval dateInterval, String routeNumber, ChauffeurList chauffeurList, VehicleList vehicleList, String pricePredefined)
   {
      this.destination = destination;
      this.dateInterval = dateInterval;
      this.routeNumber = routeNumber;
      this.pricePredefined=pricePredefined;
      this.stops = new ArrayList<String>();// adding the stops and defining the
                              // route
      this.chauffeurList = chauffeurList;
      this.vehicleList = vehicleList;

   }

	public Route(String destination, DateInterval dateInterval, String routeNumbe, String pricePredefined)
	{
		this.destination = destination;
		this.dateInterval = dateInterval;
		this.routeNumber = routeNumber;
		this.pricePredefined=pricePredefined;
		this.stops = new ArrayList<String>();// adding the stops and defining the
										// route
		this.chauffeurList = new ChauffeurList(); // list of chauffeurs
		// drivers can be added using methods
										   // later
		this.vehicleList = new VehicleList(); // list of vehicles

	}

	public String getPricePredefined()
	{
		return pricePredefined;
	}

	public void setPricePredefined(String pricePredefined)
	{
		this.pricePredefined = pricePredefined;
	}

	public DateInterval getDateInterval()
	{
		return dateInterval;
	}

	public void setDateInterval(DateInterval dateInterval)
	{
		this.dateInterval = dateInterval;
	}

	public String getDestination()
	{
		return destination;
	}

	// Setters
	public void setDestination(String rename)
	{
		this.destination = rename;
	}

	public void setRouteNumber(String routeNumber)
	{
		this.routeNumber = routeNumber;
	}

	// Getters

	public String getRouteNumber()
	{
		return routeNumber;
	}

	public ChauffeurList getChauffeurs()
   {
      return chauffeurList;
   }

   public void setChauffeurs(ChauffeurList chauffeurs)
   {
      this.chauffeurList = chauffeurs;
   }

   public VehicleList getVehicles()
   {
      return vehicleList;
   }

   public void setVehicles(VehicleList vehicles)
   {
      this.vehicleList = vehicles;
   }

   public void setStops(ArrayList<String> stops)
   {
      this.stops = stops;
   }

   public String toString()
	{
		return "Route number " + routeNumber + "Destination " + destination + "Date interval " + dateInterval.toString();
	}

	// Getting all stops/adding a stop to the list and removing a particular
	// stop
	// according to the index number
	public ArrayList<String> getStops()
	{
		return stops;
	}

	// Stops lists controllers
	public void addStop(String stopName)
	{
		stops.add(stopName);
	}

	public void deleteStops(int index)
	{
		stops.remove(index);
	}

}
