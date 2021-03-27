package change.domai;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
import change.domai.model.test.Base;
import change.domai.model.test.Child;
import change.domai.model.test.TestInterface;
import change.domai.service.SearchService;

@RestController
public class testContoroller {
	//セッションスコープBeanを利用。347P参照
	//認証済みかチェックをしないと

	@GetMapping("/goods/v1.0/test")
    public TestInterface oneSearch() {
		Child child = new Child();
		//Base base = new Base();
		//Base baschil = new Base();
		
		child.setNum(10);
		child.setNumber(100);
		child.setTest("test");
		return child;
	}
}
