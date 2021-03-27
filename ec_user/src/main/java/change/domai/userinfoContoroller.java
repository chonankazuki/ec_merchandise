package change.domai;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import change.domai.common.ErrorMessage;
import change.domai.model.LoginForm;
import change.domai.model.RegisterForm;
import change.domai.model.RegisterOpe;
import change.domai.model.TokenInfo;
import change.domai.model.User;
import change.domai.model.response.ErrorResponse;
import change.domai.model.response.ErrorTemp;
import change.domai.model.response.ResponseInterface;
import change.domai.model.response.SuccessResponse;
import change.domai.repository.user.UserRepository;
import change.domai.service.UserService;


@RestController
public class userinfoContoroller {
	@Autowired
	UserService userService;
	
	@Autowired
	SuccessResponse srep;
	@Autowired
	ErrorResponse erep;
	
	@PostMapping("/v1.0/user/register_ope/")
	public ResponseInterface operaterRegister(@RequestBody RegisterOpe opeform) {
	
		ResponseInterface response = userService.opeRegister(opeform.getPassword(),opeform.getRole());
		return response;
    }
	
	@PostMapping("/v1.0/user/login/")
	public ResponseInterface login(@RequestBody LoginForm logform) {
	
		ResponseInterface response = userService.loginProcedure(logform.getId(),logform.getPassword());
		return response;
    }
	@PostMapping("/v1.0/user/register/")
    public ResponseInterface userRegister(@RequestBody RegisterForm regform) throws IOException, ParseException {
		List<String> errlist = new ArrayList<String>();
		System.out.println(regform);
		errlist = userService.register(regform,errlist);
		List<ErrorTemp> errors = new ArrayList<ErrorTemp>();
		if(errlist.size() == 0) {
			srep.setStatus("OK");
			srep.setMessage("OKです");
			int sequential_id = userService.getSeqId(regform.getId());
			srep.setData(sequential_id);
    		return srep;
    	}else {
    		
    		erep.setStatus("NG");
    		erep.setMessage("入力内容にエラーがあります");
    		for(int i = 0;i<errlist.size();i++) {
    			ErrorTemp errtemp = new ErrorTemp();
    			errtemp.setError_code(errlist.get(i));
    			errors.add(errtemp);
    		}
    		return erep;
    	}
    }
    
	@PostMapping("/v1.0/user/register_check/")
    public ResponseInterface register_conf(@RequestBody RegisterForm regform) throws IOException {
		List<String> errlist = new ArrayList<String>();
		errlist = userService.formcheck(regform,errlist);
		List<ErrorTemp> errors = new ArrayList<ErrorTemp>();
		if(errlist.size() == 0) {
			srep.setStatus("OK");
			srep.setMessage("OKです");
    		return srep;
    	}else {
    		
    		erep.setStatus("NG");
    		erep.setMessage("入力内容にエラーがあります");
    		
    		for(int i = 0;i<errlist.size();i++) {
    			ErrorTemp errtemp = new ErrorTemp();
    			errtemp.setError_code(errlist.get(i));
    			errors.add(errtemp);
    		}
    		erep.setErrors(errors);
    		return erep;
    	}
    }
}
