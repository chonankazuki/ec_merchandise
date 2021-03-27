package change.domai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import change.domai.model.Merchandise;
import change.domai.repository.serach.SearchRepository;

@Service
public class SearchService {
	@Autowired
	SearchRepository searchRepository;
	
	public List<Merchandise> getSearchResult_NoKey(int limit,int offset){
		List<Merchandise> merlist;
		merlist = searchRepository.getAllResult(limit,offset);
		
		return merlist;
		
	}
	
	public Merchandise getOneResult(int merId){
		Merchandise mer;
		mer = searchRepository.getOneItem(merId);
		
		return mer;
		
	}
}
