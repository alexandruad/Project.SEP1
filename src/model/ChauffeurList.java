package model;
import java.util.ArrayList;
import java.io.Serializable;

public class ChauffeurList implements Serializable
{
	private ArrayList<Chauffeur> chauffeurList;

	public ChauffeurList()
	{
		this.chauffeurList = new ArrayList<Chauffeur>();
	}

	public void addChauffeur(Chauffeur chauffeur)
	{
		chauffeurList.add(chauffeur);
	}

	// delete a specific chauffeur by his ID
	public void deleteChauffeur(int id)
	{
		for (int i = 0; i < chauffeurList.size(); i++)
		{
			if (chauffeurList.get(i).getId() == id)
			{
				chauffeurList.remove(i);
			}
		}
	}

	// Getting a list of chauffeurs by the same name
	public ChauffeurList getChauffeurByName(String name)
	{
		ChauffeurList chauList = new ChauffeurList();

		for (int i = 0; i < chauffeurList.size(); i++)
		{
			if (chauffeurList.get(i).getName().toLowerCase().equals(name.toLowerCase()))
				chauList.addChauffeur(chauffeurList.get(i));

		}
		return chauList;
	}

	// get an specific chauffeur by phone number
	public Chauffeur getChauffeurByPhone(String phone)
	{
		for (int i = 0; i < chauffeurList.size(); i++)
		{
			if (chauffeurList.get(i).getPhone() == phone)
			{
				return chauffeurList.get(i);
			}
		}
		return null;
	}

	// return chauffeur by ID
	public Chauffeur getChauffeurById(int id)
	{
		for (int i = 0; i < chauffeurList.size(); i++)
		{
			if (chauffeurList.get(i).getId() == id)
			{
				return chauffeurList.get(i);
			}

		}
		return null;

	}

	// return all chauffeurs(the full list)
	public ArrayList<Chauffeur> getChauffeurs()
	{
		return chauffeurList;
	}

}
