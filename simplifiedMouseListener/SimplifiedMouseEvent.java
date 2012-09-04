package simplifiedMouseListener;

public class SimplifiedMouseEvent {
	
	// Private fields
	private String message;
	private int number;
	private Object customObject;
	
	// Constructors
	public SimplifiedMouseEvent(String message, int number, Object customObject) {
		this.message = message;
		this.number = number;
		this.customObject = customObject;
	}
	
	public SimplifiedMouseEvent(String message) {
		this(message, 0, null);
	}
	
	public SimplifiedMouseEvent(int number) {
		this(null, number, null);
	}
	
	public SimplifiedMouseEvent(Object customObject) {
		this(null, 0, customObject);
	}
	
	// Get methods for private fields
	public String getMessage() {
		return message;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Object getCustomObject() {
		return customObject;
	}
}
