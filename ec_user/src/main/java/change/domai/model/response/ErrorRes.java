package change.domai.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorRes extends ResponsCommon implements ResponseInterface {


	private ErrorTemp erep;

	public ErrorTemp getErep() {
		return erep;
	}

	public void setErep(ErrorTemp erep) {
		this.erep = erep;
	}

}
