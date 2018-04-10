package model;

import java.io.Serializable;

public class Customer implements Serializable
{
	private String name;
	private String phone;
	private String address;
	private String email;
	private String companyInfo;
	private String notes;
	private ReservationList reservationList;

	public Customer(String name, String phone, String address, String email, String companyInfo, String notes)
	{
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.companyInfo = companyInfo;
		this.notes = notes;
		this.reservationList = new ReservationList();

	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCompanyInfo()
	{
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo)
	{
		this.companyInfo = companyInfo;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return "" + name + ", " + phone + ", " + address + ", " + email + ", " + companyInfo + ", " + notes + ", ";
	}

	public ReservationList getReservationList()
	{
		return reservationList;
	}

	public void setReservationList(ReservationList reservationList)
	{
		this.reservationList = reservationList;
	}

}
