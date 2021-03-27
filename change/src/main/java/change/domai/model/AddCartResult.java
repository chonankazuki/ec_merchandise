package change.domai.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


public class AddCartResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
