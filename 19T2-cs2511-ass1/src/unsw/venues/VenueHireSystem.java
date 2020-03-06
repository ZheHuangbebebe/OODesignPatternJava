/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 */
public class VenueHireSystem {

	RequestSys requestSys;
	VenueSys venueSys;
    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
        // TODO Auto-generated constructor stub
    	requestSys = new RequestSys();
    	venueSys = new VenueSys();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;

        // TODO Implement other commands
        case "change":
        	String changeId = json.getString("id");
            LocalDate changeStart = LocalDate.parse(json.getString("start"));
            LocalDate changeEnd = LocalDate.parse(json.getString("end"));
            int changeSmall = json.getInt("small");
            int changeMedium = json.getInt("medium");
            int changeLarge = json.getInt("large");
            
            JSONObject changeResult = change(changeId, changeStart, changeEnd, changeSmall, changeMedium, changeLarge);
            
            System.out.println(changeResult.toString(2));
            break;
        
        case "cancel":
        	String cancelId = json.getString("id");
        	
        	JSONObject cancelResult = cancel(cancelId);
        	
        	System.out.println(cancelResult.toString(2));
        	
        	break;
        	
        case "list": 
        	
        	String venueName = json.getString("venue");
        	
        	JSONArray list = list(venueName);
        	
        	System.out.println(list.toString(2));
        	
        	break;
        }
    }

    // Calling the addRoomToVenue() in VenueSys
    private void addRoom(String venue, String room, String size) {
        // TODO Process the room command
    	venueSys.addRoomToVenue(venue, room, Size.valueOf(size.toUpperCase()));
    	//System.out.println(venueSys.getVenueByName(venue).getRooms().toString() + venue.toString());
    }

    // Calling the addRequest() in RequestSys
    // If request successful, return successfulRequestOrCange(id), which output a JSONObject
    // If request fail, just return {"statue": "rejected"}
    private JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
       
        if(requestSys.addRequest(venueSys.getVenues(), id, small, medium, large, start, end)) {
        	return successfulRequestOrChange(id);
        }
        else
        	return new JSONObject().put("status", "rejected"); 
    }
    
    // Calling changeRequest in RequestSys
    // Same with request method
    private JSONObject change(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
    	if(requestSys.changeRequest(venueSys.getVenues(), id, small, medium, large, start, end))
    		return successfulRequestOrChange(id);
    	else
           	return new JSONObject().put("status", "rejected"); 
    }
    
    // Calling removeRequest in RequestSys
    private JSONObject cancel(String id) {
    	if(requestSys.removeRequest(id))
    		return new JSONObject().put("status", "success");
    	return new JSONObject().put("status", "rejected");
    	
    }
    
    // Crate a JSONObject used in request or change if successful
    private JSONObject successfulRequestOrChange(String id) {
    	JSONObject result = new JSONObject();
    	result.put("status", "success");
        result.put("venue", getVenueByRequest(id).getName());

        JSONArray rooms = new JSONArray();
        for(RequestedRoom r:requestSys.getRequestById(id).getRequestedRooms()) {
        	rooms.put(r.getName());
        }
        result.put("rooms", rooms);
        return result;
    }
    
    /*****
     * 
     * @param request id
     * @return the venue for this request
     * 
     * Only called in successfulRequestOrChange
     ******/
    private Venue getVenueByRequest(String id) {
    	Request request = requestSys.getRequestById(id);
    	RequestedRoom firstRequestedRoom = request.getRequestedRooms().get(0);
    	Venue venue = venueSys.getVenueByRoom(firstRequestedRoom);
    	return venue;
    }
    
    /*****
     * 
     * @param venue
     * @return JSONArray
    // Sort the requests (every room, not same room) by start date in requestSys
    // Then create JSONObject and JSONArray
     * 
     *****/
    private JSONArray list(String venue) {
		requestSys.sortRequestsByStartDate();
    	JSONArray result = new JSONArray();
    	//if(venueSys.getRoomByVenue(venue) == null)
		//	return new JSONObject().put("status", "rejected");
		for(Room room:venueSys.getRoomsByVenue(venue)) {
			JSONObject ro = new JSONObject();
			ro.put("room", room.getName());
			JSONArray reservations = new JSONArray();
			for(Request request:requestSys.getRequest()) {
				if(request.getRequestedRooms().contains(room)) {
					JSONObject r = new JSONObject();
					r.put("id", request.getId());
					r.put("start", request.getStartDate());
					r.put("end", request.getEndDate());
					reservations.put(r);
				}
			}
			ro.put("reservations", reservations);
			result.put(ro);
		}
		return result;
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
