package change.domai.service;
import change.domai.common.ErrorMessage;
import change.domai.common.converter.Encrypt;
import change.domai.common.converter.TimeConverter;
import change.domai.common.exception.AlreadyLoginException;
import change.domai.common.exception.InputEssentialException;
import change.domai.common.exception.SomethingWrongException;
import change.domai.model.LoginForm;
import change.domai.model.LoginInfo;
import change.domai.model.User;
import change.domai.model.UserInfo;
import change.domai.model.response.ErrorRes;
import change.domai.model.response.ErrorResponse;
import change.domai.model.response.ErrorTemp;
import change.domai.model.response.ResponseInterface;
import change.domai.model.response.SuccessResponse;
import change.domai.model.RegisterForm;
import change.domai.model.Test_moel;
import change.domai.model.TokenInfo;
import change.domai.repository.user.UserRepository;
import change.domai.service.methods.CommonMethods;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommonMethods cm;
	@Autowired
	SuccessResponse srep;
	@Autowired
	ErrorResponse erep;
	@Autowired
	ErrorRes err;
	private String nowdate;
	@Autowired
	TimeConverter tm;
	/*
	@Autowired
	Environment eivironment;
	
	@Autowired
	Test_moel test;
	*/
/*
	public UserServiceImpl(UserRepository userRepository,CommonMethods cm) {
		this.userRepository = userRepository;
		this.cm = cm;
	}
*/	
	public String getTime() {
		if(nowdate == null) {
		  nowdate = tm.CurrentDate_jtos();
		}
		return nowdate;
	}
	public int getSeqId(String userid) {
		System.out.println("1???????????????????????????");

		int seqid = userRepository.getSeqId(userid);
		return seqid;
	}
	public ResponseInterface getUserInfo(int id) throws IOException {
		UserInfo userinfo = userRepository.getUserInfo(id);
		
		srep.setData(userinfo);
		return srep;
	}
	public ResponseInterface logout(String id) {
		if(userRepository.loginCheck(id)) {
			userRepository.deleteToken(id);
			srep.setStatus("200");
			srep.setMessage("OK");
			srep.setData("");
		}
		return srep;
	}
	@Transactional
	public ResponseInterface opeRegister(String pw,String role) {
		try {
			if(pw == "" || pw == null || role == "" || role == null) {
				String errcode = "20001";
				String errmsg = ErrorMessage.getErrorMsg(errcode);
				ErrorTemp temp = cm.setException(errcode, errmsg);
				
				err.setErep(temp);
				err.setStatus("NG");
				err.setMessage("???????????????");
				return err;
			}else {
				try {
					String preid = userRepository.getPreid("1");
					String opeid = "O"+preid;
					userRepository.opeRegister(opeid,role,pw);
					
					srep.setData(opeid);
					return srep;
				}catch(InputEssentialException e) {
					String errcode = "20001";
					String errmsg = ErrorMessage.getErrorMsg(errcode);
					ErrorTemp temp = cm.setException(errcode, errmsg);
					
					err.setErep(temp);
					err.setStatus("NG");
					err.setMessage("???????????????");
					return err;
					
				}
			}
		}catch(IOException e) {
			ErrorTemp temp = cm.setException("99999", "???????????????????????????????????????");
			err.setErep(temp);
			err.setStatus("NG");
			err.setMessage("???????????????");
			return err;
		}
	}
	
	public ResponseInterface loginProcedure(String id,String pw) {
		System.out.println("???????????????env???");
		try {
			
			//??????????????????
			try {
				cm.inputCheckStr(id);
				cm.inputCheckStr(pw);
			}catch (InputEssentialException e){
				String errcode = e.getCode();
				String errmsg = ErrorMessage.getErrorMsg(errcode);
				//String.format(errmsg, id);
				
				ErrorTemp temp = cm.setException(errcode, errmsg);
				err.setErep(temp);
				err.setStatus("NG");
				err.setStatus("???????????????");
				return err;
			}
	
			//ID,PW?????????
			if(userRepository.IdCount(id)==0){
				String errcode = "10004";
				String errmsg = ErrorMessage.getErrorMsg(errcode);
				ErrorTemp temp = cm.setException(errcode, errmsg);
				
				err.setErep(temp);
				err.setStatus("NG");
				err.setMessage("???????????????");
				return err;
			}else if(!userRepository.getPw(id).equals(Encrypt.generateHash(pw))){
				String errcode = "10003";
				String errmsg = ErrorMessage.getErrorMsg(errcode);
				
				ErrorTemp temp = cm.setException(errcode, errmsg);
				err.setErep(temp);
				err.setStatus("NG");
				err.setMessage("???????????????");
				return err;
			}else {
				//????????????????????????
				try{
					cm.loginCheck(id);
				}catch (AlreadyLoginException e){
					String errcode = e.getCode();
					String errmsg = ErrorMessage.getErrorMsg(errcode);
					System.out.println(errmsg);
					String.format(errmsg+"", id);
					e.printStackTrace();

					ErrorTemp temp = cm.setException(errcode, errmsg);
					err.setErep(temp);
					err.setStatus("NG");
					err.setMessage("???????????????");
					return err;
				}
				
				//id,token,??????????????????
				TokenInfo tokeninfo = new TokenInfo();
				String nowdate = tm.CurrentDate_jtos();
				String expiration_date = tm.addDate_jtos(tm.str_to_date(nowdate),30);
				String token = Encrypt.generateHash(id + nowdate);
				tokeninfo.setId(id);
				tokeninfo.setToken(token);
				tokeninfo.setRegister_date(nowdate);
				tokeninfo.setExpiration_date(expiration_date);
				int res = userRepository.registerToken(tokeninfo);
				if(res == 0) {
					ErrorTemp temp = cm.setException("99999", "???????????????????????????????????????");
					err.setErep(temp);
					err.setStatus("NG");
					err.setMessage("???????????????");
					return err;
				}else {
					srep.setData(tokeninfo);
					return srep;
				}
				
			}
		}catch(IOException | ParseException e) {
			ErrorTemp temp = cm.setException("99999", "???????????????????????????????????????");
			err.setErep(temp);
			err.setStatus("NG");
			err.setMessage("???????????????");
			return err;
		}
	}
	
	
	@Transactional
	public List<String> register(RegisterForm regform,List<String> errlist) throws IOException, ParseException {
		errlist = formcheck(regform,errlist);
		String hashedpass = Encrypt.generateHash(regform.getPassword());
		if(hashedpass == "") {
			errlist.add("?????????????????????????????????");
		}else {
			regform.setPassword(hashedpass);
		}
		if(errlist.size() == 0) {
			//auth??????user?????????????????????????????????
			int result_user = userRepository.InsertUser(regform);
			if(result_user == 0) {
				errlist.add("user?????????????????????????????????????????????");
			}else {
				int seqid = userRepository.getSeqId(regform.getId());
				regform.setSequential_id(seqid);
				int result_userinfo = userRepository.InsertUserInfo(regform);
				if(result_userinfo == 0) {
					errlist.add("userinfo?????????????????????????????????????????????");
				} 
			}
		}
		return errlist;
	}
	
	
	public List<String> idcheck(RegisterForm regform,List<String> errlist) throws IOException {
		String id = regform.getId();
		
		if(userRepository.IdCount(id) > 0) {
			errlist.add("id????????????????????????????????????");
		}
		return errlist;
	}

	public List<String> formcheck(RegisterForm regform,List<String> errlist) throws IOException {
		if(userRepository.IdCount(regform.getId()) > 0) {
			errlist.add("id????????????????????????????????????");
		}
		
		if(regform.getId() == "" || Objects.isNull(regform.getId())) {
			errlist.add("idnull");
		}else {
			if(userRepository.IdCount(regform.getId()) > 0) {
				errlist.add("id????????????????????????????????????");
			}
		}
		
		if(regform.getPassword() == ""|| Objects.isNull(regform.getPassword())) {
			errlist.add("pwnull");
		}
		if(regform.getFirst_name() == ""|| Objects.isNull(regform.getFirst_name())) {
			errlist.add("fnamenull");
		}
		if(regform.getLast_name() == ""|| Objects.isNull(regform.getLast_name())) {
			errlist.add("lnamenull");
		}
		if(regform.getFirst_name_jp()== ""|| Objects.isNull(regform.getFirst_name_jp())) {
			errlist.add("fnameJPnull");
		}
		if(regform.getLast_name_jp() == ""|| Objects.isNull(regform.getLast_name_jp())) {
			errlist.add("lnameJPnull");
		}
		
		if(regform.getAddres() == ""|| Objects.isNull(regform.getAddres())) {
			errlist.add("addnull");
		}

		if(regform.getMail() == ""|| Objects.isNull(regform.getMail())) {
			errlist.add("mailnull");
		}
		
		if(regform.getBirth_year() == ""|| Objects.isNull(regform.getBirth_year())) {
			errlist.add("byearnull");
		}
		if(regform.getBirth_month() == ""|| Objects.isNull(regform.getBirth_day())) {
			errlist.add("bmonthnull");
		}
		if(regform.getBirth_day() == ""|| Objects.isNull(regform.getBirth_day() )){
			errlist.add("bdaynull");
		}
		
		return errlist;
	}

	//id,pw???OK?????????????????????????????????????????????????????????????????????

}
