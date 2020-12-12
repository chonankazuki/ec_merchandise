package change.domai.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="billing_info")
public class BillingInfo implements Serializable{
	@Id
	private int bill_id;
	private Date app_start_date;
	private Date app_end_date;
    private int payment_method;
    private boolean authorization;
    private String card_no;
    private String account_no;
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
	public int getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}
	public boolean isAuthorization() {
		return authorization;
	}
	public void setAuthorization(boolean authorization) {
		this.authorization = authorization;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
    
	
}
