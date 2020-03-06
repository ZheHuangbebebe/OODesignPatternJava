package unsw.venues;

public class Room {
	private String name;
	private Size size;

	public Room(String name, Size size) {
		this.name = name;
		this.size = size;
	}
	
	public Size getSize() {
		return this.size;
	}
	
	public String getName() {
		return this.name;
	}
	
	// equals() use to check if they are same room
	// It can be equal with a RequestRoom which has same name and size
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Room))
			return false;
		Room other = (Room) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	/*
	@Override
	public String toString() {
		return "Room [name=" + name + "]";
	}
	*/

}
