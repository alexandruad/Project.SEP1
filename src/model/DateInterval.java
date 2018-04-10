package model;
import java.io.Serializable;
import java.util.ArrayList;

public class DateInterval implements Serializable
{
	private Date startDate, endDate;
	private ArrayList<Date> startDates, endDates;

	public DateInterval(Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;

		startDates = new ArrayList<>();
		endDates = new ArrayList<>();

	}

	public DateInterval(Date startDate)
	{
		this.startDate = startDate;
		this.endDate = startDate;

		startDates = new ArrayList<>();
		endDates = new ArrayList<>();

	}

	public Date getStartDate()
   {
      return startDate;
   }

   public void setStartDate(Date startDate)
   {
      this.startDate = startDate;
   }

   public Date getEndDate()
   {
      return endDate;
   }

   public void setEndDate(Date endDate)
   {
      this.endDate = endDate;
   }

   public ArrayList<Date> getStartDates()
   {
      return startDates;
   }

   public void setStartDates(ArrayList<Date> startDates)
   {
      this.startDates = startDates;
   }

   public ArrayList<Date> getEndDates()
   {
      return endDates;
   }

   public void setEndDates(ArrayList<Date> endDates)
   {
      this.endDates = endDates;
   }

   public void addStartDate(Date startDate)
	{
		startDates.add(startDate);
	}

	public void addEndDate(Date endDate)
	{
		endDates.add(endDate);
	}

	// date interval collide or are the same eg 4-5 with 5-6
	public boolean isOverLap(DateInterval other)
	{

		if (startDate.isAfter(other.startDate) && startDate.isBefore(other.endDate) || endDate.isAfter(other.startDate) && endDate.isBefore(other.endDate) || other.startDate.isAfter(startDate) && other.startDate.isBefore(endDate) || other.endDate.isAfter(startDate) && other.endDate.isBefore(endDate))
			return true;
		return false;

	}

}
