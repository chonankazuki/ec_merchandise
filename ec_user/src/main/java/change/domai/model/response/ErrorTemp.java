package change.domai.model.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ErrorTemp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7253850293872201183L;
	
	private String error_code;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	

}
