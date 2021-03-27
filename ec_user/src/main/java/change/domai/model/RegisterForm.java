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
public class RegisterForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int sequential_id;
	@NotBlank(message = "idは必須です")
	private String id;
	
	@NotBlank(message = "passwordは必須です")
	@Size(min=6 , max=64,message = "パスワードは6文字以上６４文字以内です" )
	private String password;

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
	private String birth_year;
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	private String birth_month;
	private String birth_day;
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
	public String getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public int getSequential_id() {
		return sequential_id;
	}
	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
	}
	
	
}
