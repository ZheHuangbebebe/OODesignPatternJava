package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;


public class RequestSys {

	private static ArrayList<Request> requests; 
	
	public RequestSys() {
		requests = new ArrayList<Request>();
	}
	
	/*******
	 * 
	 * @param id
	 * @param small
	 * @param medium
	 * @param large
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 * addRequest use findAvailableCertainRoomByVenue to find a list of rooms for each size
	 * Then check if their size (smallRooms, mediumRooms, largeRooms) larger then three input integers, if not, go to next venue
	 * Until a venue has enough rooms, add first n rooms into Request by using addRoomToRequest then break
	 * The Rooms in order of the declarations
	 * After checked all venues, if request still has no requestedRooms, return false which means request fail
	 * Otherwise return true, means request successfull!
	 * 
	 *******/
	public boolean addRequest(ArrayList<Venue> venues, String id, int small, int medium, int large, LocalDate startDate, LocalDate endDate) {
		Request request = new Request(id, small, medium, large, startDate, endDate);
		for(Venue v:venues) {
			ArrayList<Room> smallRooms = findAvailableCertainRoomByVenue(v, Size.SMALL, startDate, endDate);
			ArrayList<Room> mediumRooms = findAvailableCertainRoomByVenue(v, Size.MEDIUM, startDate, endDate);
			ArrayList<Room> largeRooms = findAvailableCertainRoomByVenue(v, Size.LARGE, startDate, endDate);
			if(smallRooms.size() >= small && mediumRooms.size() >= medium && largeRooms.size() >= large) {
				addCertainRoomsToRequest(request, smallRooms, small);
				addCertainRoomsToRequest(request, mediumRooms, medium);
				addCertainRoomsToRequest(request, largeRooms, large);
				break;
			}	
		}
		if(request.getRequestedRooms().size() == 0)
			return false;
		requests.add(request);
		return true;
	}
	
	/******
	 * 
	 * @param request
	 * @return boolean
	 * By removing a request, remove it from the list of requests
	 * Then remove all the RequestedRoomws from Request class
	 * 
	 ******/
	public boolean removeRequest(String request){
		Request r = getRequestById(request);
		if(r != null) {
			requests.remove(r);
			r.removeAllRequestedRoom();
			return true;
		}
		return false;
	}
	
	/********
	 * 
	 * @param id
	 * @param small
	 * @param medium
	 * @param large
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 * First store all the old values of the old request
	 * Then remove it by using removeRequest
	 * Create a new requst which has same id, if it work, return true
	 * Otherwise, use the old values used for create the old request
	 * 
	 ********/

	public boolean changeRequest(ArrayList<Venue> venues, String id, int small, int medium, int large, LocalDate startDate, LocalDate endDate) {
		Request old = null;
		old = getRequestById(id);
		int oldSmall = old.getSmall();
		int oldMedium = old.getMedium();
		int oldLarge = old.getLarge();
		LocalDate oldStart = old.getStartDate();
		LocalDate oldEnd = old.getEndDate();
		boolean a = removeRequest(id);
		if(addRequest(venues, id,small,medium,large,startDate, endDate)) {
			return true;
		}
		else {
			addRequest(venues,id,oldSmall,oldMedium,oldLarge,oldStart, oldEnd);
			return false;
		}
	}
	
	// a getter for list of requests
	public ArrayList<Request> getRequest() {
		return requests;
	}
	
	/******
	 * 
	 * @param id
	 * @return Request
	 * Input request id to get the request class
	 * Return null if no such id
	 *
	 */
	public Request getRequestById(String id) {
		for(Request r:requests) {
			if(r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}
	
	// sort requests by start time, just for the correct output of 'list' command
	public void sortRequestsByStartDate() {
		ArrayList<Request> newRequests = new ArrayList<Request>();
		while(!requests.isEmpty()) {
			Request minRequest = requests.get(0);
			int min = convertLocalDateToInt(requests.get(0).getStartDate());
			for(Request request:requests) {
				if(min > convertLocalDateToInt(request.getStartDate())) {
					min = convertLocalDateToInt(request.getStartDate());
					minRequest = request;
				}
			}
			newRequests.add(minRequest);
			requests.remove(minRequest);
		}
		requests = newRequests;
		/***********
		for(Request r: requests) {
			System.out.println(r.toString());
		}
		************/
	}
	
	
	
	//////////// The following methods are private method (only used in RequestSys class) ///////////////
	
	
	
	// return a list of ReqiestedRooms where are conflict with the input Date
	private ArrayList<RequestedRoom> findConflictRequestRoom(LocalDate startDate, LocalDate endDate) {
		ArrayList<RequestedRoom> requestedrooms = new ArrayList<RequestedRoom>();
		for(Request request:requests) {
			if(!(compareLocalDate(request.getStartDate(), endDate) || compareLocalDate(startDate, request.getEndDate()))) {
				requestedrooms.addAll(request.getRequestedRooms());
				//System.out.println("In if");
			}
		}
		return requestedrooms;
	}
	
	/*******
	 * 
	 * @param venue
	 * @param size
	 * @param startDate
	 * @param endDate
	 * @return a list of given size room
	 * By finding available certain room (size) in a certain room
	 * Call the method findConflictRequestRoom to get a list of conflict room (all the conflict room, which means they are not in same venue)
	 * For each room in this venue, if it is not in the conflict room array then it is availabe
	 *
	 *********/
	private ArrayList<Room> findAvailableCertainRoomByVenue(Venue venue, Size size, LocalDate startDate, LocalDate endDate){
		ArrayList<Room> rooms = new ArrayList<Room>();
		ArrayList<RequestedRoom> conflictRooms = findConflictRequestRoom(startDate, endDate);
		for(Room r:venue.getRooms()) {
			if(!conflictRooms.contains(r)) {
				if(r.getSize().equals(size))
					rooms.add(r);
			}
		}
		return rooms;
	}
	
	/********
	 * 
	 * @param date1
	 * @param date2
	 * @return boolean
	 * Compare if first date larger than second date
	 * Only used in finConflictRequestRoom
	 * 
	 *********/
	private boolean compareLocalDate(LocalDate date1, LocalDate date2) {
		int date1ToInt = convertLocalDateToInt(date1);
		int date2ToInt = convertLocalDateToInt(date2);
		if (date1ToInt > date2ToInt)
			return true;
		else return false;
	}
	
	/*******
	 * 
	 * @param date
	 * @return int
	 * Covert local date to int, e.g. 2019-06-16 will be 20190615
	 * Used in compareLocalDate and sortRequestsByStartDate
	 *
	 ********/
	private int convertLocalDateToInt(LocalDate date) {
		return date.getYear()*1000 + date.getMonthValue() * 100 + date.getDayOfMonth();
	}
	
	/*****
	 * 
	 * @param request
	 * @param room
	// When adding a room into request, a requestedRoom will be create in request class
	// Only called in addCertainRoomsToRequest
	 * 
	 *****/
	private void addRoomToRequest(Request re, Room r) {
		re.addRequestedRoom(r);
	}
	
	// Adding a number of rooms into request by a give list of room
	private void addCertainRoomsToRequest(Request request, ArrayList<Room> rooms, int num) {
		for (int n = 0; n < num; n++) {
			addRoomToRequest(request, rooms.get(n));
		}
	}
	
}
