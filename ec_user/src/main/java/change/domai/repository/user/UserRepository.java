package change.domai.repository.user;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import change.domai.common.converter.Encrypt;
import change.domai.common.converter.TimeConverter;
import change.domai.common.exception.InputEssentialException;
import change.domai.model.LoginInfo;
import change.domai.model.RegisterForm;
import change.domai.model.TokenInfo;

@Component
public class UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//ゆくゆくはsqlは外だし
	public String getPw(String id) throws IOException {
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();
		String sql = "select password from user "
				+ "where id = ? and app_start_date <= ? and app_end_date > ?";
		String pw = jdbcTemplate.queryForObject(sql,String.class,id,nowdate,nowdate);
		return pw;
		
	}
	public boolean loginCheck(String id) {
		boolean flg = true;
		String nowdate = TimeConverter.CurrentDate_jtos();
		
		String sql = "select count(*) from token "
				+ "where id = ? and expiration_date > ? ";
		int num = jdbcTemplate.queryForObject(sql,Integer.class,id,nowdate);
		if(num == 0) {
			flg = false;
		}
		return flg;
	}
		
	
	public int registerToken(TokenInfo tokeninfo) {
		String nowdate = tokeninfo.getRegister_date();
		String expiration_date = tokeninfo.getExpiration_date();
		String token = tokeninfo.getToken();
		String id = tokeninfo.getId();
		//String logindate = nowdate;
		
		String sql = "insert into token(id,token,publish_date,expiration_date) values(?,?,?,?)";
		int result = jdbcTemplate.update(sql,id,token,nowdate,expiration_date);

		return result;
	}
	
	
	public int IdCount(String id) throws IOException {		
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();

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
		String nowdate = TimeConverter.CurrentDate_jtos();
		System.out.println(nowdate);
		String sql = "select sequential_id from user "
				+ "where id = ? and app_start_date <= ? and app_end_date > ?";
		return jdbcTemplate.queryForObject(sql,Integer.class,id,nowdate,nowdate);
	}

	public int InsertUser(RegisterForm userinfo) throws IOException {
		String sql = "insert into user(id, password,app_start_date,app_end_date,bill_id,register_date) "
				+ "values(?,?,?,?,?,?)";
		//戻り値は処理件数
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();
		return jdbcTemplate.update(sql,userinfo.getId(),userinfo.getPassword(),nowdate,enddate,124,nowdate);
	}
	
	public int InsertUserInfo(RegisterForm userinfo) throws ParseException, IOException {	
		String sql = "insert into user_info(sequential_id,app_start_date,app_end_date,first_name,last_name,first_name_jp,last_name_jp,addres,tel,mail,birth_date) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		//戻り値は処理件数
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();
		String birthdate = userinfo.getBirth_year() + "/" + userinfo.getBirth_month() + "/" +userinfo.getBirth_day();
		birthdate = birthdate+ " 00:00:00";
		return jdbcTemplate.update(sql,userinfo.getSequential_id(),nowdate,enddate,userinfo.getFirst_name(),userinfo.getLast_name(),userinfo.getFirst_name_jp(),userinfo.getLast_name_jp(),userinfo.getAddres(),userinfo.getTel(),userinfo.getMail(),birthdate);
	}
	
	public int userAuth(String id,String pass) {
		boolean flag = false;
		
		String nowdate = TimeConverter.CurrentDate_jtos();
		String sql = "select count(*) from user "
				+ "where id = ? and password = ? and app_start_date <= ? and app_end_date > ?";
		
		return jdbcTemplate.queryForObject(sql,Integer.class,id,pass,nowdate,nowdate);
		
	}
	
	public void getUser(LoginInfo loginfo,String usrid, int seqid) {
		String nowdate = TimeConverter.CurrentDate_jtos();
		System.out.println(seqid);
		String sql = "select * from user_info where sequential_id = ? and app_start_date <= ? and app_end_date > ?";
		Map<String, Object> ret = jdbcTemplate.queryForMap(sql,seqid,nowdate,nowdate);
		loginfo.setSequentialId(seqid);
		loginfo.setUserId(usrid);
		loginfo.setFirstName((String) ret.get("first_name"));
		loginfo.setLastName((String) ret.get("last_name"));	
	}
	
	public String getPreid(String status) throws InputEssentialException {
		String nowdate = TimeConverter.CurrentDate_jtos();
		String sql = "insert into opeid_mgt(publish_date,status) "
				+ "values(?,?,?)";
		int c = jdbcTemplate.update(sql,nowdate,status);
		int id;
		
		if(c > 0) {
			sql = "SELECT last_insert_id() FROM opeid_mgt";
			id = jdbcTemplate.queryForObject(sql,Integer.class);
			return Integer.toString(id);
		}else {
			throw new InputEssentialException();
		}
	}
	public void opeRegister(String opeid,String role,String pw) throws InputEssentialException, IOException {
		String nowdate = TimeConverter.CurrentDate_jtos();
		String enddate = TimeConverter.MaxDate_jtos();
		
		String sql = "insert into opeid_mgt(ope_id,password,role_id,app_start_date,app_end_date,password_change_date,register_date,lastlogin_date) "
				+ "values(?,?,?,?,?,?,?,?)";
		int c = jdbcTemplate.update(sql,opeid,pw,role,nowdate,enddate,nowdate,nowdate,nowdate);
	}

}
