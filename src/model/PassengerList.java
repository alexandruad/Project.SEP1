package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PassengerList implements Serializable
{
	private ArrayList<Passenger> list;

	public PassengerList()
	{
		this.list = new ArrayList<Passenger>();
	}

	public void addPassenger(Passenger passenger)
	{

		list.add(passenger);
	}

	public void deletePassenger(String name, int age)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (name.equals(list.get(i).getPassengerName()) && age == list.get(i).getPassengerAge())
				list.remove(i);
		}
	}

	public ArrayList<Passenger> getPassengers()
	{
		return list;
	}
}
