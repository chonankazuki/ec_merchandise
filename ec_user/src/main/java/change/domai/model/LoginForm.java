package change.domai.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class LoginForm implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	@NotBlank(message = "idは必須です")
	private String id;
	@NotBlank(message = "passwordは必須です")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
