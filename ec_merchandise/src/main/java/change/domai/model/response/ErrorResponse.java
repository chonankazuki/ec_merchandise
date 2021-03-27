package change.domai.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse extends ResponsCommon implements ResponseInterface {


	private List<ErrorTemp> errors = new ArrayList<ErrorTemp>();

	public List<ErrorTemp> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorTemp> errors) {
		this.errors = errors;
	}	

}
