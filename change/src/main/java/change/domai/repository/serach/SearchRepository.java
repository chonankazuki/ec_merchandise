package change.domai.repository.serach;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import change.domai.common.converter.TimeConverter;
import change.domai.model.Item;
import change.domai.model.Merchandise;

@Component
public class SearchRepository {
	@Autowired
	TimeConverter ts;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Merchandise> getAllResult(){
		List<Merchandise> merlist = new ArrayList<Merchandise>();
		
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select * from merchandise where app_start_date <= ? and app_end_date > ?";
		List<Map<String, Object>> ret = jdbcTemplate.queryForList(sql,nowdate,nowdate);
		for(Map<String, Object> goods :ret) {
			Merchandise mer  = new Merchandise();
			mer.setMerchandise_id((int)goods.get("merchandise_id"));
			mer.setApp_start_date((Date)goods.get("app_start_date"));
			mer.setApp_end_date((Date)goods.get("app_end_date"));
			mer.setPrice((int)goods.get("price"));
			mer.setShop_id((int)goods.get("shop_id"));
			mer.setStock((int)goods.get("stock"));
			mer.setMerchandise_name((String)goods.get("merchandise_name"));
			mer.setDetail_info((String)goods.get("detail_info"));
			
			merlist.add(mer);
		}
		
		return merlist;
	}
	public List<Merchandise> getResult(String key){
		List<Merchandise> merlist = new ArrayList<Merchandise>();
		
		String nowdate = ts.CurrentDate_jtos();
		key = "%" + key + "%"; 
		String sql = "select * from merchandise where app_start_date <= ? and app_end_date > ? and merchandise_name like ?";
		//String sql = "select * from merchandise where app_start_date <= ? and app_end_date > ? and merchandise_name = ?";
		List<Map<String, Object>> ret = jdbcTemplate.queryForList(sql,nowdate,nowdate,key);
		for(Map<String, Object> goods :ret) {
			Merchandise mer  = new Merchandise();
			mer.setMerchandise_id((int)goods.get("merchandise_id"));
			mer.setApp_start_date((Date)goods.get("app_start_date"));
			mer.setApp_end_date((Date)goods.get("app_end_date"));
			mer.setPrice((int)goods.get("price"));
			mer.setShop_id((int)goods.get("shop_id"));
			mer.setStock((int)goods.get("stock"));
			mer.setMerchandise_name((String)goods.get("merchandise_name"));
			mer.setDetail_info((String)goods.get("detail_info"));
			
			merlist.add(mer);
		}
		
		return merlist;
	}
	
}
