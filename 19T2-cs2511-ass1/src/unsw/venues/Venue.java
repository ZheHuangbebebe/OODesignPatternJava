package unsw.venues;

import java.util.ArrayList;

public class Venue {
	private String name;
	private ArrayList <Room> rooms;
	
	public Venue(String name) {
		this.name = name;
		rooms = new ArrayList<Room>();
	}
	
	public void addRoom(String room, Size size) {
		rooms.add(new Room(room, size));
	}
	
	public ArrayList <Room> getRooms(){
		return rooms;
	}
	
	public String getName() {
		return name;
	}
}
