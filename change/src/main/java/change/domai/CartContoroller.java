package change.domai;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import change.domai.model.AddCartResult;
import change.domai.model.Cart;
import change.domai.model.Item;
import change.domai.model.LoginForm;
import change.domai.model.LoginInfo;
import change.domai.model.Merchandise;
import change.domai.model.User;
import change.domai.model.UserInfo;
import change.domai.repository.user.UserRepository;
import change.domai.service.CartService;
import change.domai.service.SearchService;
import change.domai.service.UserService;


//@RestController
@Controller
@SessionAttributes(types=UserInfo.class)
public class CartContoroller {
	//セッションスコープBeanを利用。347P参照
	//認証済みかチェックをしないと
	@Autowired
	Cart cart;
	@Autowired
	LoginInfo loginInfo;
	@Autowired
	CartService cartService;
	
	@GetMapping("/add-cart")
	@ResponseBody
	//カートに追加して成功かどうかをポップアップするためのコントローラー
    public AddCartResult search(@RequestParam(name = "merchandise_id") int merId,@RequestParam(name = "num") int num,Model model) throws IOException {
	    //SearchService searchService = new SearchService();
		System.out.println("IDは" + merId);
		System.out.println("数量は" + num);
		System.out.println("seqIDは" + loginInfo.getSequentialId());
	    boolean res = cartService.addCart(loginInfo.getSequentialId(),merId,num);
	    JSONObject json = new JSONObject();
	    AddCartResult addres = new AddCartResult();
	    if(res) {
	    	json.put("status","OK");
	    	System.out.println("OK");
	    	System.out.println(json);
	    	addres.setStatus("OK");
	    }else {
	    	json.put("status","NG");
	    	System.out.println(json);
	    	//カートに追加できませんでしたとポップアップ
	    	addres.setStatus("NG");
	    }
	    Iterator<String> keys = json.keys();
	    ///全てのキーを取得

	    while(keys.hasNext()){
	    String key = keys.next();
	    String value = json.getString(key);
	    System.out.println(key +" "+ value);
	}
	    //model.addAttribute("loginInfo",loginInfo);
	    //model.addAttribute("cart",cart);
	    //return "OK";
	    
    	return addres;
	}

	@RequestMapping(value = "/getCart", method = RequestMethod.GET)
	public String getCart(Model model) {
		Cart cart = cartService.getCart(loginInfo.getSequentialId());
		model.addAttribute("cart",cart);
		//model.addAttribute("cartnum",cart.getItems().size());
	    return "cart";
	}
	
}
