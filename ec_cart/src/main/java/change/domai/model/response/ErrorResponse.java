package change.domai.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse implements ResponseTemplate {

	private String status;
	private String message;
	private List<ErrorTemp> errors = new ArrayList<ErrorTemp>();

	public List<ErrorTemp> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorTemp> errors) {
		this.errors = errors;
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
	

}
