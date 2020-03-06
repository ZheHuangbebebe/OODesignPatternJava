package unsw.venues;

import java.util.ArrayList;

public class VenueSys {
	
	private ArrayList <Venue> venues;
	
	public VenueSys() {
		venues = new ArrayList<Venue>();
	}
	
	/****
	 * 
	 * @param name
	 * Add a new venue into venueSys
	 * Only called in addRoomToVenue
	 * This is the only private method in VenueSys
	 * 
	 ****/
	private void addVenue(String name) {
		Venue venue = new Venue(name);
		venues.add(venue);
	}
	
	
	/******
	 * 
	 * @param venue
	 * @param room
	 * @param size
	 * Add a new room to a venue
	 * If venue not exist, create a new venue then add a new room
	 * 
	 ******/
	public void addRoomToVenue(String venue, String room, Size size) {
		// find the venue by name
		Venue v = getVenueByName(venue);
		if(v != null) {
			v.addRoom(room, size);
		}
		else {
			addVenue(venue);
			Venue newVenue = getVenueByName(venue);
			newVenue.addRoom(room, size);
		}
	}
	
	// Input the name of venue and return the Venue, if not exist return null
	public Venue getVenueByName(String venue) {
		for(Venue v:venues) {
			if(v.getName().equals(venue)) {
				return v;
			}
		}
		return null;
	}
	
	// By input a room to find which venue it belongs to
	public Venue getVenueByRoom(Room room) {
		for(Venue v:venues) {
			if(v.getRooms().contains(room)) {
				return v;
			}
		}
		return null;
	}
	
	// A getter for venues
	public ArrayList<Venue> getVenues(){
		return venues;
	}
	
	// Get the rooms by input the name of venue, if venue not exist, return null
	public ArrayList<Room> getRoomsByVenue(String venue){
		Venue v = getVenueByName(venue);
		if(v != null) {
			return v.getRooms();
		}
		else return null;
	}
	
}
