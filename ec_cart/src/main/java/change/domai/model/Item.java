package change.domai.model;

import java.io.Serializable;
import java.util.Date;


public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//購入or削除でapp_emd_dateをとじる
	
	//private int sequential_id;
	private int merchandise_id;
	private Date app_start_date;
	private Date app_end_date;
	private Date addDate;
	private String merchandise_name;
	private int price;
	private int quantity;
	
	public int getMerchandise_id() {
		return merchandise_id;
	}
	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getMerchandise_name() {
		return merchandise_name;
	}
	public void setMerchandise_name(String merchandise_name) {
		this.merchandise_name = merchandise_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	/*
	public int getSequential_id() {
		return sequential_id;
	}
	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
	}
	*/
}
