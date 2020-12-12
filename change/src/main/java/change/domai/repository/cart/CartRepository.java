package change.domai.repository.cart;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import change.domai.common.converter.TimeConverter;
import change.domai.model.Cart;
import change.domai.model.Item;
import change.domai.model.Merchandise;

@Component
public class CartRepository {
	@Autowired
	TimeConverter ts;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int checkStock(int merId){
		
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select count(*) from merchandise where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		
		return jdbcTemplate.queryForObject(sql,Integer.class,merId,nowdate,nowdate);
	}
	public Merchandise getMerchandise(int merId){
		
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select * from merchandise where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		Map<String, Object> ret = jdbcTemplate.queryForMap(sql,merId,nowdate,nowdate);
		Merchandise mer = new Merchandise();
		mer.setMerchandise_id((int)ret.get("merchandise_id"));
		mer.setApp_start_date((Date)ret.get("app_start_date"));
		mer.setApp_end_date((Date)ret.get("app_end_date"));
		mer.setPrice((int)ret.get("price"));
		mer.setShop_id((int)ret.get("shop_id"));
		mer.setStock((int)ret.get("stock"));
		mer.setMerchandise_name((String)ret.get("merchandise_name"));
		mer.setDetail_info((String)ret.get("detail_info"));
		return mer;
	}
	
	public int checkCart(int seqId,int merId){
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select count(*) from item where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		
		return jdbcTemplate.queryForObject(sql,Integer.class,seqId,merId,nowdate,nowdate);
	}
	
	public int addCart(Merchandise mer, int seqId,int num) throws IOException {
		String nowdate = ts.CurrentDate_jtos();
		String enddate = ts.MaxDate_jtos();
		String sql = "insert into item values (?,?,?,?,?,?,?,?)";
		
		return jdbcTemplate.update(sql,seqId,mer.getMerchandise_id(),nowdate,enddate,mer.getPrice(),nowdate,mer.getMerchandise_name(),num);
	}
	
	public void addCartQuantity(int merId,int seqId,int num) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update item set quantity = quantity + ? where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,seqId,merId,nowdate,nowdate);
	
	}
	
	public void subtractCartQuantity(int merId,int seqId,int num) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update item set quantity = quantity + ? where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,seqId,merId,nowdate);
		
	}
	
	
	public void stockSubtract(int merId,int num) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update merchandise set stock = stock - ? where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,merId,nowdate,nowdate);
	
	}
	public void stockadd(int merId,int num) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update merchandise set stock = stock + ? where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,merId,nowdate,nowdate);
	}

	public Cart getCart(int seqId) {
		String nowdate = ts.CurrentDate_jtos();
		List<Item> items = new ArrayList<Item>();
		
		String sql = "select * from item where sequential_id = ? and quantity > 0 and app_start_date <= ? and app_end_date > ?";
		List<Map<String, Object>> ret = jdbcTemplate.queryForList(sql,seqId,nowdate,nowdate);
		for(Map<String, Object> tmp :ret) {
			Item item  = new Item();
			item.setSequential_id(seqId);
			item.setMerchandise_id((int)tmp.get("merchandise_id"));
			item.setMerchandise_name((String)tmp.get("merchandise_name"));
			item.setPrice((int)tmp.get("price"));
			item.setQuantity((int)tmp.get("quantity"));
			item.setAddDate((Date)tmp.get("addDate"));
			item.setApp_end_date((Date)tmp.get("app_end_date"));
			item.setApp_start_date((Date)tmp.get("app_start_date"));
			
			items.add(item);
		}
		Cart cart = new Cart();
		cart.setItems(items);
		return cart;
	}
	
}
