package parking.data;

public class ViolationsData {
	private final String zipCode;
	private final Integer fine;
	private final String state;
	private final String reason;


	public ViolationsData(String zipCode, Integer fine, String state, String reason) {
		this.zipCode = zipCode;
		this.fine = fine;
		this.state = state;
		this.reason = reason;
	}
	public String getZipCode() {
		return zipCode;
	}
	public Integer getFine() {
		return fine;
	}
	
	public String getState() {
		return state;
	}
	
	public String getReason() {
		return reason;
	}
}