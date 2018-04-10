package model;
import java.io.Serializable;

public class Chauffeur implements Serializable
{

	private String type;
	private String name;
	private String address;
	private static int id = 0;
	private String wishesForTrips;
	private boolean availability;
	private String phone;
	private String email;

	public Chauffeur(String type, String name, String address, String phone, String email, String wishesForTrips)
	{
		this.type = type;
		this.name = name;
		this.address = address;
		Chauffeur.id++;
		this.wishesForTrips = wishesForTrips;
		availability = true;
		this.phone = phone;
		this.email = email;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// If driver is available or not
	public void setAvailability(Boolean availability)
	{
		if (availability)
			this.availability = true;
		else
			this.availability = false;
	}

	// Setting type to temporary or fulltime
	public void setType(String type)
	{
		this.type = type;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	// Drivers preferences
	public void setWishesForTrips(String WishesForTrips)
	{
		this.wishesForTrips = WishesForTrips;
	}

	// phone number
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	// Getters
	public boolean getAvailability()
	{
		return availability;
	}

	public String getPhone()
	{
		return phone;
	}

	public String getEmail()
	{
		return email;
	}

	public String getType()
	{
		return type;
	}

	public String getWishesForTrips()
	{
		return wishesForTrips;
	}

	public String getAddress()
	{
		return address;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return " " + type + ", " + name + ", " + address + ", " + id + ", " + ", " + phone + ", " + email + ", " + wishesForTrips + ", " + availability;
	}
}
