package change.domai.model;

import java.io.Serializable;
import java.util.Date;

public class Merchandise implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1159739514681630478L;
	private int merchandise_id;
	private Date app_start_date;
	private Date app_end_date;
	private int price;
	private int shop_id;
	private int stock;
	private String merchandise_name;
	private String detail_info;
	
	public int getMerchandise_id() {
		return merchandise_id;
	}
	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMerchandise_name() {
		return merchandise_name;
	}
	public void setMerchandise_name(String merchandise_name) {
		this.merchandise_name = merchandise_name;
	}
	public String getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(String detail_info) {
		this.detail_info = detail_info;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
