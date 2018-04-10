package model;
import java.io.Serializable;

public class Date implements Serializable
{
	private int day;
	private int month;
	private int year;
	private int time;

	public Date(int time, int day, int month, int year)
	{
		setDate(time, day, month, year);//

	}

	public Date(int day, int month, int year)
	{
		this.time = 0;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setDate(int time, int day, int month, int year)
	{
		this.time = time;
		this.day = day;
		this.month = month;
		this.year = year;

	}

	public int getDay()
   {
      return day;
   }

   public void setDay(int day)
   {
      this.day = day;
   }

   public int getMonth()
   {
      return month;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public int getTime()
   {
      return time;
   }

   public void setTime(int time)
   {
      this.time = time;
   }

   public Date getDate()
	{
		return new Date(this.time, this.day, this.month, this.year);
	}

	public Date copy()
	{
		return new Date(this.time, this.day, this.month, this.year);
	}

	public String toString()
	{
		String all = "";
		all += time;
		all += "/";
		all += day;
		all += "/";
		all += month;
		all += "/";
		all += year;

		return all;

		// return time+"/"day+"/"+month+"/"+year
	}

	// COMPARING DATES

	public boolean isBefore(Date other)
	{
		double d1 = (double) (year * 360 + month * 30 + day + time / 24);
		double d2 = (double) (other.year * 360 + other.month * 30 + other.day + time / 24);
		return d1 < d2;
	}

	public boolean isAfter(Date other)
	{
		double d1 = (double) (year * 360 + month * 30 + day + time / 24);
		double d2 = (double) (other.year * 360 + other.month * 30 + other.day + time / 24);
		return d1 > d2;
	}

}
