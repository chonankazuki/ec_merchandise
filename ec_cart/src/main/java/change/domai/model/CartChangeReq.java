package change.domai.model;

import java.io.Serializable;

public class CartChangeReq  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private int sequential_id;
	private int merchandise_id;
	private int num;
	
	public int getMerchandise_id() {
		return merchandise_id;
	}
	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	

}
