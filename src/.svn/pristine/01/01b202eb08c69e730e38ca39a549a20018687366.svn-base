package hemlock;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable
{
	private String locationName;
	
	//list of all descriptions in a location, rather than broad description
	private ArrayList<String> locationDescription;

	public static Location createLocation(String locationName)
	{
		Location location = new Location();
		location.locationName = locationName;
		location.locationDescription = Dialogue.loadLocationDialogue(locationName);
		return location;
	}
	//setters
	
	
	
	//getters
	public ArrayList<String> getLocationDescription()
	{
		return this.locationDescription;
	}

	public String getLocationName()
	{
		return this.locationName;
	}	

}
