package unsw.venues;

public class RequestedRoom extends Room{

	// This is no other attributes in RequestedRoom
	// This class only create when a new request created successfully
	public RequestedRoom(String name, Size size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}
	
	public Size getSize() {
		return super.getSize();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
