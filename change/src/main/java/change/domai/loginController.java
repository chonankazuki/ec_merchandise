package change.domai;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import change.domai.model.Cart;
import change.domai.model.LoginForm;
import change.domai.model.LoginInfo;
import change.domai.model.Test;
import change.domai.service.CartService;
import change.domai.service.UserService;


//@RestController
@Controller
//@SessionAttributes(types=LoginInfo.class)
public class loginController {
	@Autowired
	UserService userService;
	@Autowired
	Cart cart;
	@Autowired
	LoginInfo loginInfo;
	@Autowired
	CartService cartService;

	//@ModelAttribute("loginform")
	//public LoginForm setLoginForm() {
	//	return new LoginForm();
	//}
	
	//login画面を返す
    @RequestMapping(value = "/login_form")
    //public String get_loginForm(LoginForm loginform, Model model) {
    public String get_loginform(Model model) {
    	//LoginForm loginForm = new LoginForm();
    	LoginForm loginForm = new LoginForm();
    	model.addAttribute("loginForm",loginForm);
     	return "login";
    }
    
    //認証処理を書く
    @RequestMapping(value = "/login")
    //Model,BindingResultの中でオブジェクト名が自動でloginFormになるため、controller,thymeleafでobject名を
    //「loginform」とかで設定すると、Modelに格納されている名称と違うため、取得できない
    //おそらくformクラスの頭を小文字にした名称でmodelに格納される
    //オブジェクトの値だけならaddAttrributeすればいいが、bindingresultも同じ名称(formの頭文字小文字)で自動で入る
    public String login(@Validated LoginForm loginForm, BindingResult error, Model model) {
    	
    	System.out.println("idは"+loginForm.getId());
    	if(error.hasErrors()) {
    		return "login";
    	}else{
    		//id/pwチェック
    		if(!userService.login_auth(loginForm)){
    			String message = "idまたはpwが間違っています";
    			model.addAttribute("idcheck_message",message);
    			return "login";
    		}
    		
    		//クッキーを取得して買い物カゴ(cartオブジェクトを取得)
    		//いろんな情報を取得、セッションアトリビュートのオブジェクトに格納
    		userService.loginProc(loginForm.getId(),loginInfo,cart);
    		model.addAttribute("loginInfo",loginInfo);
    		cart = cartService.getCart(loginInfo.getSequentialId());
    		model.addAttribute("cart",cart);
    		return "home";
    	}
    }
}
