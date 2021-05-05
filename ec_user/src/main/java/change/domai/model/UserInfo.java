package change.domai.model;

import java.util.Date;

public class UserInfo {
	private int sequential_id; 
	private Date app_start_date;
	private Date app_end_date;
	private String first_name;   
	private String last_name;     
	private String first_name_jp; 
	private String last_name_jp;  
	private String addres;        
	private String tel;           
	private String mail;          
	private Date birth_date;    
	private Date register_date; 
	private Date withdraw_date;
	
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
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getWithdraw_date() {
		return withdraw_date;
	}
	public void setWithdraw_date(Date withdraw_date) {
		this.withdraw_date = withdraw_date;
	}

}
