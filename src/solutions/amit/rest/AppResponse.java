package solutions.amit.rest;

public class AppResponse {

	public static final String ERROR = "error"; 
	private String status;
	private String message;
	private Object payload;
	
		public AppResponse(){
			this.status = "success";
		}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
}
