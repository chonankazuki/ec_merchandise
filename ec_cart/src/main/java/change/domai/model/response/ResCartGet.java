package change.domai.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import change.domai.model.Item;

@Component
public class ResCartGet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private int sequential_id;
	public int getSequential_id() {
		return sequential_id;
	}

	public void setSequential_id(int sequential_id) {
		this.sequential_id = sequential_id;
	}

	private List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
