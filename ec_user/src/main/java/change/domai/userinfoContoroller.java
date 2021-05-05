package change.domai;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import change.domai.common.ErrorMessage;
import change.domai.common.converter.TimeConverter;
import change.domai.model.LoginForm;
import change.domai.model.RegisterForm;
import change.domai.model.RegisterOpe;
import change.domai.model.Test_moel;
import change.domai.model.TokenInfo;
import change.domai.model.User;
import change.domai.model.response.ErrorResponse;
import change.domai.model.response.ErrorTemp;
import change.domai.model.response.ResponseInterface;
import change.domai.model.response.SuccessResponse;
import change.domai.repository.user.UserRepository;
import change.domai.service.UserService;
import change.domai.service.UserServiceImpl;
import change.domai.service.methods.CommonMethods;


@RestController
public class userinfoContoroller {
	@Autowired
	UserService userService;
	@Autowired
	TimeConverter tm;
	/*
	public userinfoContoroller() {
		CommonMethods cm = new CommonMethods();
		UserRepository userRepository = new UserRepository();
		UserService userService = new UserServiceImpl(userRepository,cm);
		this.userService = userService;
	}
	*/
	@Autowired
	SuccessResponse srep;
	@Autowired
	ErrorResponse erep;

	@GetMapping("/v1.0/user/test/")
	public String testTime() throws InterruptedException {
		
		System.out.println("処理スタート"+ tm.CurrentDate_jtos());
		
		System.out.println("処理終了"+ tm.CurrentDate_jtos());
		
		
		return "test";
    }

	@PostMapping("/v1.0/user/register_ope/")
	public ResponseInterface operaterRegister(@RequestBody RegisterOpe opeform) {
		
		ResponseInterface response = userService.opeRegister(opeform.getPassword(),opeform.getRole());
		return response;
    }
	
	@PostMapping("/v1.0/user/login/")
	@CrossOrigin(origins = {"http://localhost:8096"})
	public ResponseInterface login(@RequestBody LoginForm logform,HttpServletResponse httpResopnse) {

		System.out.println("login入った");
		System.out.println(logform.getId());
		System.out.println(logform.getPassword());
		ResponseInterface response = userService.loginProcedure(logform.getId(),logform.getPassword());
		Cookie cookie = new Cookie("token", "12345678");
		cookie.setPath("/");
		httpResopnse.setHeader("Authorization", "12345678");
		httpResopnse.setHeader("Access-Control-Allow-Origin","http://localhost:8096");
		httpResopnse.setHeader("Access-Control-Allow-Credentials","true");
		httpResopnse.addCookie(cookie);
		System.out.println("gogogogo");
		return response;
    }
	
	@PostMapping("/v1.0/user/logout/")
	@CrossOrigin(origins = {"http://localhost:8096"})
	public ResponseInterface login(@RequestBody String st_id) {
		JSONObject jsonObject = new JSONObject(st_id);
		String id = jsonObject.getString("id");
		System.out.println(id);
		System.out.println("ログアウト処理開始");
		ResponseInterface response = userService.logout(id);
		return response;
    }
	
	@GetMapping("/v1.0/user/get_userinfo/{id}")
	public ResponseInterface getInfo(@PathVariable("id") int id) throws IOException {
		ResponseInterface response = userService.getUserInfo(id);
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
