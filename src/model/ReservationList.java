package model;
import java.io.Serializable;
import java.util.ArrayList;

public class ReservationList implements Serializable
{
	private ArrayList<Reservation> list;

	public ReservationList()
	{
		list = new ArrayList<Reservation>();
	}

	public void addReservation(Reservation reservation)
	{
		list.add(reservation);
	}

	public boolean removeReservation(int reservationId)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (reservationId == list.get(i).getReservationId())
			{
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removeReservation(Customer customer)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (customer.equals(list.get(i).getCustomer()))
			{
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean removeReservation(Route route)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (route.equals(list.get(i).getRoute()))
			{
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public ReservationList getReservations(Route route)
	{
		ReservationList reservationList = new ReservationList();
		for (int i = 0; i < list.size(); i++)
		{
			if (route.equals(list.get(i).getRoute()))
			{
				reservationList.addReservation(list.get(i));
			}
		}
		return reservationList;
	}

	public ReservationList getReservations(Customer customer)
	{
		ReservationList reservationList = new ReservationList();
		for (int i = 0; i < list.size(); i++)
		{
			if (customer.equals(list.get(i).getCustomer()))
			{
				reservationList.addReservation(list.get(i));
			}
		}
		return reservationList;
	}

	public Reservation getReservation(Customer customer, Route route)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (customer.equals(list.get(i).getCustomer()) && route.equals(list.get(i).getRoute()))
			{
				return list.get(i);
			}
		}
		return null;

	}

	public Reservation getReservation(DateInterval date)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (date.equals(list.get(i).getRoute().getDateInterval()))
			{
				return list.get(i);
			}
		}
		return null;

	}

	public ArrayList<Reservation> getReservations()
	{
		return list;
	}

}
