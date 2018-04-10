package model;

public class Passenger
{
	private String name;
	private int age;

	public Passenger(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public void setPassenger(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public Passenger getPassenger()
	{
		Passenger passenger = new Passenger(name, age);
		return passenger;
	}

	public String getPassengerName()
	{
		return name;
	}

	public int getPassengerAge()
	{
		return age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String toString()
	{
		return "Name: " + name + " Age: " + age;
	}
}
