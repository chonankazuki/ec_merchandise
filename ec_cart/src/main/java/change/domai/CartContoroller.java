package change.domai;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import change.domai.model.CartChangeReq;
import change.domai.model.DummyData;
import change.domai.model.response.ResCartGet;
import change.domai.model.response.ErrorResponse;
import change.domai.model.response.ErrorTemp;
import change.domai.model.response.InstantResponse;
import change.domai.model.response.ResponseTemplate;
import change.domai.model.response.SuccessResponse;
import change.domai.service.CartService;

//responseのクラスを作って、承継？インターフェース？template?させる
//response
  //status
  //message
  //data

//@Controller
//@SessionAttributes(types=UserInfo.class)
@RestController
public class CartContoroller {
	//認証済みかチェックをしないと
	@Autowired
	ResCartGet cart;
	@Autowired
	CartService cartService;
	@Autowired
	SuccessResponse srep;
	@Autowired
	ErrorResponse erep;
	
    @GetMapping("/cart/v1.0/get/{uid}")
	public ResponseTemplate getCart(@PathVariable("uid") int uid) {
		cart = cartService.getCart(uid);
		
		if(cart != null) {
			srep.setStatus("200");
			srep.setMessage("OK");
			srep.setData(cart);
			return srep;
		}else {
			erep.setStatus("400");
			erep.setMessage("NG");
			return erep;
		}
	}
    @GetMapping("/cart/v1.0/refresh/{uid}")
	public ResponseTemplate refreshCart(@PathVariable("uid") int uid) {
    	InstantResponse ires = cartService.refreshCart(uid);
		if(ires.isFlg() == true) {
			srep.setStatus("300");
			srep.setMessage("OKだよ");
			return srep;
		}else {
			//error処理
			erep.setStatus("400");
			erep.setMessage("NG");
			List<ErrorTemp> errors = erep.getErrors();
			errors.add(new ErrorTemp());
			return erep;
		}
		
	}
    @PostMapping("/cart/v1.0/change/{uid}")
	public ResponseTemplate changeCart(@PathVariable("uid") int uid,@RequestBody CartChangeReq req) throws IOException, ParseException {
		
    	int num = req.getNum();
		int mer_id = req.getMerchandise_id();
		InstantResponse ires = new InstantResponse();
		
		System.out.println("haitta " + num);
		
    	if(num > 0) {
    		System.out.println("プラスです");
    		ires = cartService.addCart(uid,mer_id,num);
    	}else {
    		ires = cartService.subtractCart(uid,mer_id,num);
    	}
    	if(ires.isFlg()) {
    		srep.setStatus("300");
			srep.setMessage("OKだよ");
			return srep;
    	}else {
			erep.setStatus("400");
			erep.setMessage("NG");
			return erep;
    	}
    }
    /*
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
	*/
	
}
