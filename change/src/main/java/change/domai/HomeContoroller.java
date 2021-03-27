package change.domai;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

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
public class HomeContoroller {
	//セッションスコープBeanを利用。347P参照
	//認証済みかチェックをしないと
	@Autowired
	Cart cart;
	@Autowired
	LoginInfo loginInfo;
	@Autowired
	SearchService searchService;
	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "/home")
    public String home(Model model) {
		if(loginInfo == null) {
			
		}else {
			//cart = cartService.getCart(loginInfo.getSequentialId());
			System.out.println("homeから"+loginInfo.getFirstName());
			model.addAttribute("loginInfo",loginInfo);
			model.addAttribute("cart",cart);
			
		}
		return "home";
	}
	@GetMapping("/search")
    public String search(@RequestParam(name = "key", required = false) String key,Model model) {
	    System.out.println("abc");
	    //SearchService searchService = new SearchService();
	    List<Merchandise> merlist = searchService.getSearchResult(key);
	    model.addAttribute("merlist",merlist);
	    model.addAttribute("loginInfo",loginInfo);
	    model.addAttribute("cart",cart);
		return "searchResult";
	}
	
	@RequestMapping(value = "/hogehoge", method = RequestMethod.GET)
	public String getMorePage(Model model) {
		System.out.println("ajax来ました");
		String text = "ajax-test";
		model.addAttribute("text",text);
	    return "footer :: footer";
	}
}
