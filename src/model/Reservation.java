package model;

import java.io.Serializable;

public class Reservation implements Serializable
{
	private static int reservationId = 0;
	private Customer customer;
	private int numberOfSeats;
	private PassengerList passengers;
	private int price;
	private Route route;

	public Reservation(Customer customer, int numberOfSeats, PassengerList passengers, Route route, int price)
	{
		this.reservationId = reservationId++;
		this.customer = customer;
		this.numberOfSeats = numberOfSeats;

		this.passengers = passengers;

		this.route = route;
		this.price = price;
	}

	public Reservation(Customer customer, Route route, int price)
	{
		this.reservationId = reservationId++;
		this.customer = customer;
		this.route = route;
		this.price = price;
	}

	public void setReservation(Customer customer, int numberOfSeats, PassengerList passengers, Route route, int price)
	{

		this.numberOfSeats = numberOfSeats;
		this.customer = customer;
		this.passengers = passengers;

		this.route = route;
		this.price = price;

	}

	public void setReservation(Customer customer, Route route, int price)
	{

		this.customer = customer;
		this.route = route;
		this.price = price;

	}

	public int getReservationId()
	{
		return reservationId;
	}

	public int getNumberOfSeats()
	{
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
	}

	public PassengerList getPassengers()
	{
		return passengers;
	}

	public void addPassengers(Passenger passenger)
	{
		passengers.addPassenger(passenger);
	}

	public void removePassenger(String name, int age)
	{
		passengers.deletePassenger(name, age);
	}

}
