package change.domai.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//@Entity
//@Table(name="user_info")
//@Component
//@Scope("prototype")
//@Component,@Scopeを付けないとコンポーネントスキャンの対象にならず、autowiredされない
//そもそもentityはautowiredするべきではない？
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int sequential_id;
	@NotBlank(message = "idは必須です")
	private String id;
	@NotBlank(message = "passwordは必須です")
	private String password;
	private Date app_start_date;	
	private Date app_end_date;
	@NotBlank(message = "firstnameは必須です")
	private String first_name;
	@NotBlank(message = "lastnameは必須です")
	private String last_name;
	@NotBlank(message = "名は必須です")
	private String first_name_jp;
	@NotBlank(message = "姓は必須です")
	private String last_name_jp;
	@NotBlank(message = "住所は必須です")
	private String addres;
	@NotBlank(message = "電番は必須です")
	private String tel;
	@NotBlank(message = "メルアドは必須です")
	private String mail;
	@NotBlank(message = "誕生日は必須です")
	private String birth_date;
	
	public int getSequential_id() {
		return sequential_id;
	}
	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
	}
	public Date getApp_start_date() {
		return app_start_date;
	}
	public void setApp_start_date(Date app_start_date) {
		this.app_start_date = app_start_date;
	}
	public Date getApp_end_date() {
		return app_end_date;
	}
	public void setApp_end_date(Date app_end_date) {
		this.app_end_date = app_end_date;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name_jp() {
		return first_name_jp;
	}
	public void setFirst_name_jp(String first_name_jp) {
		this.first_name_jp = first_name_jp;
	}
	public String getLast_name_jp() {
		return last_name_jp;
	}
	public void setLast_name_jp(String last_name_jp) {
		this.last_name_jp = last_name_jp;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
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
