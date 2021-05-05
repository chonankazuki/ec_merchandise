package change.domai;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import change.domai.model.Merchandise;
import change.domai.model.response.ResponseInterface;
import change.domai.model.response.ErrorResponse;
import change.domai.model.response.SuccessResponse;
import change.domai.service.SearchService;

@RestController
public class ItemSearchContoroller {
	//セッションスコープBeanを利用。347P参照
	//認証済みかチェックをしないと
	
	@Autowired
	SearchService searchService;
	@Autowired
	SuccessResponse srep;
	@Autowired
	ErrorResponse erep;
	
	@GetMapping("/goods/v1.0/get/merchandiseid={merid}")
    public ResponseInterface oneSearch(@PathVariable("merid") int merid) {
		Merchandise mer = searchService.getOneResult(merid);
		SuccessResponse srep = new SuccessResponse();
		if(mer == null) {
			erep.setStatus("400");
			erep.setMessage("商品なかったよ");
			return erep;

		}else {
			System.out.println("mer側のidは"+ mer.getMerchandise_id());
			srep.setStatus("200");
			srep.setMessage("OK");
			srep.setData(mer);
			return srep;
		}
	}
	@GetMapping("/goods/v1.0/search/all")
	@CrossOrigin(origins = {"http://localhost:8096"})
    public ResponseInterface allSearch_nokey(@RequestParam Map<String, String> params) {
		Logger logger= LogManager.getLogger();
		logger.info("This is info level log.");
		logger.warn("This is info level log.");
		logger.error("This is info level log.");
		logger.fatal("This is info level log.");
		System.out.println("logテスト");
		int limit;
		if(params.get("limit") == null) {
			limit = 50;
		}else {
			limit = Integer.parseInt(params.get("limit"));
		}
		
		int offset;
		if(params.get("offset") == null) {
			offset = 0;
		}else {
			offset = Integer.parseInt(params.get("offset"));
		}
		
		List<Merchandise> merlist = searchService.getSearchResult_NoKey(limit,offset);
		srep.setStatus("300");
		srep.setMessage("OKだよ");	
		srep.setData(merlist);
		return srep;
	}
	
	@GetMapping("/goods/v1.0/search")
	@CrossOrigin(origins = {"http://localhost:8096"})
    public ResponseInterface Search_key(@RequestParam Map<String, String> params) {
		int limit;
		if(params.get("limit") == null) {
			limit = 50;
		}else {
			limit = Integer.parseInt(params.get("limit"));
		}
		
		int offset;
		if(params.get("offset") == null) {
			offset = 0;
		}else {
			offset = Integer.parseInt(params.get("offset"));
		}
			
		String key;
		if(params.get("key") == null) {
			erep.setStatus("500");
			erep.setMessage("key必須だよ");	
			return erep;
		}else {
			key = params.get("key");
			String[] test = key.split(" ",-1);
			System.out.println(test[0]);
			System.out.println(test[1]);
			List<Merchandise> merlist = searchService.getSearchResult_NoKey(limit,offset);
			srep.setStatus("300");
			srep.setMessage("OKだよ");
			srep.setData(merlist);
			return srep;
		}
	}
	@RequestMapping(value = "/hogehoge", method = RequestMethod.GET)
	public String getMorePage(Model model) {
		System.out.println("ajax来ました");
		String text = "ajax-test";
		model.addAttribute("text",text);
	    return "footer :: footer";
	}
}
