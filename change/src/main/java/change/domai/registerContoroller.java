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

import change.domai.model.LoginForm;
import change.domai.model.User;
import change.domai.model.UserInfo;
import change.domai.repository.user.UserRepository;
import change.domai.service.UserService;


//@RestController
@Controller
@SessionAttributes(types=UserInfo.class)
public class registerContoroller {
	@Autowired
	UserService userService;
	
	@ModelAttribute("userInfo")
	public UserInfo setUserInfo() {
		return new UserInfo();
	}

    @RequestMapping(value = "/register_form")
    public String getRegisterForm(UserInfo userInfo,Model model) {
    	//setUserInfo();
    	return "register";
    }
    
    @RequestMapping(value = "/register_confirm")
    //画面からリクエストを飛ばされた時に、formオブジェクトとmodelの両方にパラメータが格納されている。modelに改めて追加の必要はなし。
    public String register_conf(@Validated UserInfo userInfo, BindingResult error,  Model model) throws IOException {
    	//実装
    	//入力内容のチェック処理
    	//OKなら確認画面（登録ボタンと戻るボタン）※戻る時のために入力項目を保持
    	//NGならエラ〜メッセージと入力状態の画面を返す
    	
    	if(error.hasErrors()) {
    		return "register";
    	}else{
    		//id重複チェック
    		System.out.println("get*"+userInfo.getId());
    		if(!userService.IdUniqueCheck(userInfo.getId())) {
    			System.out.println("入った2");
    			String message = "idがすでに存在しています。別のidを試してください。";
    			model.addAttribute("idcheck_message",message);
    			return "register";
    		}
    		System.out.println("入った3");
    		return "register_confirm";
    		
        }
    }
    @RequestMapping(value = "/register")
    public String register(UserInfo userInfo, Model model) throws ParseException, IOException {
    	//System.out.println("id_as:"+userInfo.getId());
    	System.out.println(userInfo);
    	if(userService.UserRegister(userInfo)) {
        	return "register_complete";    		
    	}
    	return "regster_error";
    }
}
