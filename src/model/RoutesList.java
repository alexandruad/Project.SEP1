package model;
import java.util.ArrayList;
import java.io.Serializable;

public class RoutesList implements Serializable
{

	private ArrayList<Route> routesList;

	public RoutesList()
	{
		this.routesList = new ArrayList<>();
	}

	public void addRoute(Route route)
	{
		routesList.add(route);
	}

	public void deleteRoute(String routeNumber)
	{

		routesList.remove(routeNumber);

	}

	public Route getRoutesByRouteNumber(int routeNumber)
	{

		return routesList.get(routeNumber);

	}

	public ArrayList<Route> getRoutes()
	{
		return routesList;
	}
}
