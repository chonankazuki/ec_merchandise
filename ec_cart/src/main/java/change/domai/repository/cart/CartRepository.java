package change.domai.repository.cart;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import change.domai.common.converter.TimeConverter;
import change.domai.model.Item;
import change.domai.model.Merchandise;
import change.domai.model.response.ResCartGet;

@Component
public class CartRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public int checkStock(int merId){
		int stock;
		stock = 10;//apiで商品サービスから取得
		String nowdate = TimeConverter.CurrentDate_jtos();
		
		//String sql = "select count(*) from merchandise where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		//return jdbcTemplate.queryForObject(sql,Integer.class,merId,nowdate,nowdate);
		
		return stock;
	}
	public Merchandise getMerchandise(int merId) throws ParseException, IOException{
		//apiで商品サービスから取得
		String nowdate = TimeConverter.CurrentDate_jtos();
		String maxdate = TimeConverter.MaxDate_jtos();
		System.out.println(nowdate);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date ndate = (Date) sdFormat.parse(nowdate);
        Date mdate = (Date) sdFormat.parse(maxdate);
        
		Merchandise mer = new Merchandise();
		mer.setMerchandise_id(1234567890);
		mer.setApp_start_date(ndate);
		mer.setApp_end_date(mdate);
		mer.setPrice(12345);
		mer.setShop_id(12345678);
		mer.setStock(10);
		mer.setMerchandise_name("木彫りのkuma");
		mer.setDetail_info("シャケは咥えてません");
		/*
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
		*/
		return mer;
	}
	
	public int checkCart(int seqId,int merId){
		String nowdate = TimeConverter.CurrentDate_jtos();
		String sql = "select count(*) from item where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		int count = jdbcTemplate.queryForObject(sql,Integer.class,seqId,merId,nowdate,nowdate);
		return count;
	}
	public Item getCartitem(int seqId,int merId) {
		String nowdate = TimeConverter.CurrentDate_jtos();
		String sql = "select * from item where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		
		Map<String, Object> tmp = jdbcTemplate.queryForMap(sql,seqId,merId,nowdate,nowdate);
		Item item  = new Item();
		//item.setSequential_id(seqId);
		item.setMerchandise_id((int)tmp.get("merchandise_id"));
		item.setMerchandise_name((String)tmp.get("merchandise_name"));
		item.setPrice((int)tmp.get("price"));
		item.setQuantity((int)tmp.get("quantity"));
		item.setAddDate((Date)tmp.get("addDate"));
		item.setApp_end_date((Date)tmp.get("app_end_date"));
		item.setApp_start_date((Date)tmp.get("app_start_date"));
		return item;
	}
	public int refreshItems(int seqId){
		String nowdate = TimeConverter.CurrentDate_jtos();
		String sql = "update item set app_end_date = ? where sequential_id = ? and app_start_date <= ? and app_end_date > ?";
		
		return jdbcTemplate.update(sql,nowdate,seqId,nowdate,nowdate);
	}
	
	public int addCart(Merchandise mer, int seqId,int num) throws IOException {
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();
		String sql = "insert into item values (?,?,?,?,?,?,?,?)";
		
		return jdbcTemplate.update(sql,seqId,mer.getMerchandise_id(),nowdate,enddate,mer.getPrice(),nowdate,mer.getMerchandise_name(),num);
	}
	
	public void changeCartQuantity(int merId,int seqId,int num) {
		String nowdate = TimeConverter.CurrentDate_jtos();
		System.out.println("商品ID" + merId + "を" + num + "減らします" );
		String sql = "update item set quantity = quantity + ? where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,seqId,merId,nowdate,nowdate);
	
	}
	/*
	public void subtractCartQuantity(int merId,int seqId,int num) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update item set quantity = quantity + ? where sequential_id = ? and merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,seqId,merId,nowdate);
		
	}
	
	*/
	public boolean stockSubtract(int merId,int num) {
		//APIを呼ぶ
		
		//String nowdate = ts.CurrentDate_jtos();
		//String sql = "update merchandise set stock = stock - ? where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		//jdbcTemplate.update(sql,num,merId,nowdate,nowdate);
		return true;
	
	}
	
	public boolean stockadd(int merId,int num) {
		//APIを呼ぶ
		
		/*
		String nowdate = ts.CurrentDate_jtos();
		String sql = "update merchandise set stock = stock + ? where merchandise_id = ? and app_start_date <= ? and app_end_date > ?";
		jdbcTemplate.update(sql,num,merId,nowdate,nowdate);
		*/
		return true;
	}

	public ResCartGet getCart(int seqId) {
		String nowdate = TimeConverter.CurrentDate_jtos();
		List<Item> items = new ArrayList<Item>();
		
		String sql = "select * from item where sequential_id = ? and quantity > 0 and app_start_date <= ? and app_end_date > ?";
		List<Map<String, Object>> ret = jdbcTemplate.queryForList(sql,seqId,nowdate,nowdate);
		for(Map<String, Object> tmp :ret) {
			Item item  = new Item();
			//item.setSequential_id(seqId);
			item.setMerchandise_id((int)tmp.get("merchandise_id"));
			item.setMerchandise_name((String)tmp.get("merchandise_name"));
			item.setPrice((int)tmp.get("price"));
			item.setQuantity((int)tmp.get("quantity"));
			item.setAddDate((Date)tmp.get("addDate"));
			item.setApp_end_date((Date)tmp.get("app_end_date"));
			item.setApp_start_date((Date)tmp.get("app_start_date"));
			
			items.add(item);
		}
		ResCartGet cart = new ResCartGet();
		cart.setItems(items);
		cart.setSequential_id(seqId);
		return cart;
	}
	
}
