package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable
{
	private ArrayList<Customer> list;

	public CustomerList()
	{
		list = new ArrayList<Customer>();
	}

	public void addCustomer(Customer customer)
	{
		list.add(customer);
	}

	public void deleteCustomer(String name, String phone)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (phone == (list.get(i).getPhone()) && name.equals(list.get(i).getName()))
			{
				list.remove(i);
			}
		}
	}

	public void deleteCustomer(Customer customer)
	{
		deleteCustomer(customer.getName(), customer.getPhone());
	}

	public Customer getCustomer(String name, String phone)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (phone == (list.get(i).getPhone()) && name.equals(list.get(i).getName()))
			{
				return list.get(i);
			}
		}
		return null;
	}

	public CustomerList getCustomersByName(String name)
	{
		CustomerList cList = new CustomerList();
		for (int i = 0; i < list.size(); i++)
		{
			if (name.equals(list.get(i).getName()))
				cList.addCustomer(list.get(i));
		}
		return cList;

	}

	public Customer getCustomerByPhone(String phone)
	{

		for (int i = 0; i < list.size(); i++)
		{
			if (phone == (list.get(i).getPhone()))
				return list.get(i);
		}
		return null;

	}

	public ArrayList<Customer> getCustomers()
	{
		return list;
	}

}
