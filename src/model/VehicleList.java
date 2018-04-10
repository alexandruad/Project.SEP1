package model;

import java.io.Serializable;
import java.util.ArrayList;

public class VehicleList implements Serializable
{
	private ArrayList<Vehicle> list;

	public VehicleList()
	{
		this.list = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle)
	{
		list.add(vehicle);
	}

	public void removeVehicle(String regNo)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (regNo.equals(list.get(i).getRegNo()))
				list.remove(i);
		}
	}

	public void removeVehicle(Vehicle vehicle)
	{
		removeVehicle(vehicle.getRegNo());
	}

	public ArrayList<Vehicle> getVehicles()
	{
		return list;
	}

	public ArrayList<Vehicle> getAvailableVehicles(DateInterval availability)
	{
		ArrayList<Vehicle> availableVehicles = new ArrayList<>();

		for (Vehicle vehicle : list)
		{
			if (!(vehicle.getBooking().isOverLap(availability)))
			{
				availableVehicles.add(vehicle);
			}
		}

		return availableVehicles;

	}
}
