package change.domai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import change.domai.model.Item;
import change.domai.model.Merchandise;
import change.domai.repository.serach.SearchRepository;

@Service
public class SearchService {
	@Autowired
	SearchRepository searchRepository;
	
	public List<Merchandise> getSearchResult(String key){
		List<Merchandise> merlist;
		if(key == "") {
			merlist = searchRepository.getAllResult();
		}else {
			merlist = searchRepository.getResult(key);
		}
		return merlist;
		
	}
}
