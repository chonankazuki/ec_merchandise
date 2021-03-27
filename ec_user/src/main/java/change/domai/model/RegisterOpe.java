package change.domai.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//@Entity
//@Table(name="user_info")
//@Component
//@Scope("prototype")
//@Component,@Scopeを付けないとコンポーネントスキャンの対象にならず、autowiredされない
//そもそもentityはautowiredするべきではない？
public class RegisterOpe implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String opeid;
	private String password;
	private String role;
	
	public String getOpeid() {
		return opeid;
	}
	public void setOpeid(String opeid) {
		this.opeid = opeid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
