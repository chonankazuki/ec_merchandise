package change.domai.service.methods;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import change.domai.common.exception.AlreadyLoginException;
import change.domai.common.exception.InputEssentialException;
import change.domai.model.response.ErrorTemp;
import change.domai.repository.user.UserRepository;

@Component
public class CommonMethods {
	
	@Autowired
	UserRepository userRepository;
	public boolean loginCheck(String id) throws AlreadyLoginException {
		if(userRepository.loginCheck(id)) {
			throw new AlreadyLoginException();
		}else {
			return true;
		}
	}
	
	
	public boolean validatePassword(String id,String pw) throws AlreadyLoginException {
		if(userRepository.loginCheck(id)) {
			return true;
			
		}else {
			
			throw new AlreadyLoginException();
		}
		
	}
	
	public ErrorTemp setException(String errcode,String errmsg) {
		ErrorTemp temp = new ErrorTemp();
		temp.setError_code(errcode);
		temp.setMessage(errmsg);
		
		return temp;
	}
	
	public void inputCheckStr(String val) throws InputEssentialException {
		
		if(val == ""|| Objects.isNull(val)) {
			throw new InputEssentialException();
		}
		
	}
}
