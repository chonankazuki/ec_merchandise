package change.domai.repository.user;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import change.domai.common.Property;
import change.domai.common.converter.TimeConverter;
import change.domai.model.Cart;
import change.domai.model.Item;
import change.domai.model.LoginInfo;
import change.domai.model.User;
import change.domai.model.UserInfo;

@Component
public class UserRepository {
	@Autowired
	TimeConverter ts;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//ゆくゆくはsqlは外だし
	public int IdCount(String id) throws IOException {		
		String nowdate = ts.CurrentDate_jtos();
		String enddate = ts.MaxDate_jtos();

		String sql = "select count(*) from user "
				+ "where id = ? and app_start_date <= ? and app_end_date > ?";
		//String sql1 = "select count(*) from user";
		//System.out.println("IDカウントは");
		//System.out.println(jdbcTemplate.queryForObject(sql,Integer.class,id,nowdate,enddate));
		return jdbcTemplate.queryForObject(sql,Integer.class,id,nowdate,nowdate);
		//int res = jdbcTemplate.queryForObject(sql1,Integer.class);
		//return res;
	}
	
	public int getSeqId(String id) {
		String nowdate = ts.CurrentDate_jtos();
		System.out.println(nowdate);
		String sql = "select sequential_id from user "
				+ "where id = ? and app_start_date <= ? and app_end_date > ?";
		return jdbcTemplate.queryForObject(sql,Integer.class,id,nowdate,nowdate);
	}

	public int InsertUser(UserInfo userinfo) throws IOException {
		String sql = "insert into user(id, password,app_start_date,app_end_date,register_date) "
				+ "values(?,?,?,?,?)";
		//戻り値は処理件数
		String nowdate = ts.CurrentDate_jtos();
		String enddate = ts.MaxDate_jtos();
		return jdbcTemplate.update(sql,userinfo.getId(),userinfo.getPassword(),nowdate,enddate,nowdate);
	}
	
	public int InsertUserInfo(UserInfo userinfo) throws ParseException, IOException {	
		String sql = "insert into user_info(sequential_id,app_start_date,app_end_date,first_name,last_name,first_name_jp,last_name_jp,addres,tel,mail,birth_date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		//戻り値は処理件数
		String nowdate = ts.CurrentDate_jtos();
		String enddate = ts.MaxDate_jtos();
		String birthdate = "1990-09-28 00:00:00";
		return jdbcTemplate.update(sql,userinfo.getSequential_id(),nowdate,enddate,userinfo.getFirst_name(),userinfo.getLast_name(),userinfo.getFirst_name_jp(),userinfo.getLast_name_jp(),userinfo.getAddres(),userinfo.getTel(),userinfo.getMail(),birthdate);
	}
	
	public int userAuth(String id,String pass) {
		boolean flag = false;
		
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select count(*) from user "
				+ "where id = ? and password = ? and app_start_date <= ? and app_end_date > ?";
		
		return jdbcTemplate.queryForObject(sql,Integer.class,id,pass,nowdate,nowdate);
		
	}
	
	public void getUser(LoginInfo loginfo,String usrid, int seqid) {
		String nowdate = ts.CurrentDate_jtos();
		System.out.println(seqid);
		String sql = "select * from user_info where sequential_id = ? and app_start_date <= ? and app_end_date > ?";
		Map<String, Object> ret = jdbcTemplate.queryForMap(sql,seqid,nowdate,nowdate);
		loginfo.setSequentialId(seqid);
		loginfo.setUserId(usrid);
		loginfo.setFirstName((String) ret.get("first_name"));
		loginfo.setLastName((String) ret.get("last_name"));
		
		
	}
	public int  getCartNum(int seqid) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select count(*) from item where sequential_id = ? and app_start_date <= ? and app_end_date > ?";
		List<Map<String, Object>> cart = jdbcTemplate.queryForList(sql,seqid,nowdate,nowdate);
		return cart.size();
		
	}
	
	public void getCart(Cart cart, int seqid) {
		String nowdate = ts.CurrentDate_jtos();
		String sql = "select * from item where sequential_id = ? and app_start_date <= ? and app_end_date > ?";
		List<Map<String, Object>> carts = jdbcTemplate.queryForList(sql,seqid,nowdate,nowdate);
		for(Map<String, Object> goods :carts) {
			Item item = new Item();
			item.setSequential_id(seqid);
			item.setAddDate((Date) goods.get("adddate"));
			item.setApp_end_date((Date) goods.get("app_end_date"));
			item.setApp_start_date((Date) goods.get("app_start_date"));
			item.setPrice((int) goods.get("price"));
			item.setMerchandise_id((int) goods.get("merchandise_id"));
			item.setMerchandise_name((String) goods.get("merchandise_name"));
			item.setQuantity((int) goods.get("quantity"));
			cart.getItems().add(item);
		}	
	}
}
