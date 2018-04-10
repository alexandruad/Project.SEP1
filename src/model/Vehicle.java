package model;

import java.io.Serializable;

public class Vehicle implements Serializable
{
	private String regNo;
	private int numberOfSeats;
	private DateInterval booking;
	private String vehicleType;
	private String amenities;
	private ReservationList reservationList;

	public ReservationList getReservationList()
	{
		return reservationList;
	}

	public void setReservationList(ReservationList reservationList)
	{
		this.reservationList = reservationList;
	}

	public Vehicle(String regNo, int numberOfSeats, String vehicleType, String amenities)
	{
		this.regNo = regNo;
		this.numberOfSeats = numberOfSeats;
		booking = null;
		this.vehicleType = vehicleType;
		this.amenities = amenities;
	}

	public String getRegNo()
	{
		return regNo;
	}

	public void setRegNo(String regNo)
	{
		this.regNo = regNo;
	}

	public int getNumberOfSeats()
	{
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public String toString()
	{
		return regNo + "," + numberOfSeats + "," + vehicleType + "," + amenities;
	}

	public String getVehicleType()
	{
		return vehicleType;
	}

	public void setVehicleType(String vehicleType)
	{
		this.vehicleType = vehicleType;
	}

	public void setBooking(DateInterval booking)
	{
		this.booking = booking;
	}

	public String getAmenities()
	{
		return amenities;
	}

	public void setAmenities(String amenities)
	{
		this.amenities = amenities;
	}

	public DateInterval getBooking()
	{
		return booking;
	}
}
