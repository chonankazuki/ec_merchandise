package change.domai.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import change.domai.model.RegisterForm;
import change.domai.model.response.ResponseInterface;

public interface UserService {
	
	int getSeqId(String userid);
	
	String getTime();
	
	ResponseInterface opeRegister(String pw,String role);
	
	ResponseInterface loginProcedure(String id,String pw);
	
	ResponseInterface logout(String id);
	
	ResponseInterface getUserInfo(int id) throws IOException;
	
	List<String> register(RegisterForm regform,List<String> errlist) throws IOException, ParseException;
	
	List<String> idcheck(RegisterForm regform,List<String> errlist) throws IOException;
	
	List<String> formcheck(RegisterForm regform,List<String> errlist) throws IOException;

}
