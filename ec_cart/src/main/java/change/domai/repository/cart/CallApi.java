package change.domai.repository.cart;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import change.domai.common.Property;
import change.domai.common.converter.TimeConverter;
import change.domai.model.Item;
import change.domai.model.Merchandise;
import change.domai.model.response.ResponseTemplate;
import change.domai.model.response.SuccessResponse;

@Component
public class CallApi {
	
	RestTemplate restTemplate = new RestTemplate();
	
	//@Autowired
	//Property prop;
	
	public Merchandise getItem(String prop_type,String prop_key,int merId) throws IOException, ParseException {
		String url = Property.getProperty(prop_type,prop_key);
		//url = url + merId;
		System.out.println(url);
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(merId));
	    
	    SuccessResponse resp = new SuccessResponse();
	    resp.setData(new Merchandise());
	    
		resp = restTemplate.getForObject(url, SuccessResponse.class,params);
		Object data = resp.getData();
		String[] tmp = data.toString().split(",",-1);
		Merchandise mer = new Merchandise();
		String val[] = new String[8];
		String e[] = new String[2];
		for(int i= 0;i<tmp.length;i++) {
			e = tmp[i].split("=");
			val[i] = e[1];
		}
		//TimeConverter tc = new TimeConverter();
		mer.setMerchandise_id(Integer.parseInt(val[0]));
		mer.setApp_start_date(TimeConverter.str_to_date(val[1]+" 00:00:00"));
		mer.setApp_end_date(TimeConverter.str_to_date(val[2]+" 00:00:00"));
		mer.setPrice(Integer.parseInt(val[3]));
		mer.setShop_id(Integer.parseInt(val[4]));
		mer.setStock(Integer.parseInt(val[5]));
		mer.setMerchandise_name(val[6]);
		mer.setDetail_info(val[7]);
		return mer;
	}

}
