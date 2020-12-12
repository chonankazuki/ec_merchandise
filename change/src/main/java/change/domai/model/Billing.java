package change.domai.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="billing")
public class Billing implements Serializable{
	@Id
	private int sequential_id;
	private int bill_id;
	private Date app_start_date;
	private Date app_end_date;
	public int getSequential_id() {
		return sequential_id;
	}
	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
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
	
	
}
