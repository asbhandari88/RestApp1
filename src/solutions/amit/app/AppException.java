package solutions.amit.app;

public class AppException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 924499919227453960L;

	public AppException(String msg){
		super(msg);
	}
	
	public AppException(String msg,Throwable cause){
		super(msg,cause);
	}
}
