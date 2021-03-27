package change.domai.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User implements Serializable{
	@Id
	private int sequential_id;
	private String id;
	private String password;
	private Date regester_dat;
	private Date withdraw_date;
	private Date app_start_date;
	private Date app_end_date;
	private int bill_id;
	
	public int getSequential_id() {
		return sequential_id;
	}
	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
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
	public Date getRegester_dat() {
		return regester_dat;
	}
	public void setRegester_dat(Date regester_dat) {
		this.regester_dat = regester_dat;
	}
	public Date getWithdraw_date() {
		return withdraw_date;
	}
	public void setWithdraw_date(Date withdraw_date) {
		this.withdraw_date = withdraw_date;
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
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
}
