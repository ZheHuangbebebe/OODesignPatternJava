package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Request {
	private String id;
	private int small;
	private int medium;
	private int large;
	private ArrayList <RequestedRoom> requestedRooms;
	private LocalDate startDate;
	private LocalDate endDate;

	public Request(String id, int s, int m, int l, LocalDate start, LocalDate end) {
		this.id = id;
		this.small = s;
		this.medium = m;
		this.large = l;
		requestedRooms = new ArrayList<RequestedRoom>();
		this.startDate = start;
		this.endDate = end;
	}

	// a list of getters
	public String getId() {
		return id;
	}

	public int getSmall() {
		return small;
	}

	public int getMedium() {
		return medium;
	}

	public int getLarge() {
		return large;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public ArrayList<RequestedRoom> getRequestedRooms(){
		return requestedRooms;
	}
	
	// Add a RequestRoom into Request
	public void addRequestedRoom(Room r) {
		requestedRooms.add(new RequestedRoom(r.getName(), r.getSize()));
	}
	
	// Remove all RequestedRoom
	// Only called in removeRequest in RequestSys
	public void removeAllRequestedRoom() {
		requestedRooms = new ArrayList<RequestedRoom>();
	}

	/*****
	@Override
	public String toString() {
		return "Request [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	****/
	
}
