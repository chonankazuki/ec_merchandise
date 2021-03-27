package change.domai.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import change.domai.common.Property;
import change.domai.model.Item;
import change.domai.model.Merchandise;
import change.domai.model.response.ResCartGet;
import change.domai.model.response.InstantResponse;
import change.domai.repository.cart.CallApi;
import change.domai.repository.cart.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	InstantResponse ires;
	@Autowired
	CallApi callapi;
	
	public InstantResponse addCart(int seqId,int merId,int num) throws IOException, ParseException{
		ires.setFlg(true);
		//在庫チェック
		Merchandise mer = callapi.getItem("api","search_item", merId);
		int stock = mer.getStock(); 
		System.out.println("商品IDは" + mer.getMerchandise_id());		
		System.out.println("在庫数は" + stock);		
		
		if(stock < num) {
			ires.setFlg(false);
		}else {
			//ストックを減らす
			cartRepository.stockSubtract(merId,num);
		    //カートに同じ商品が同じ値段で入っていたら数量を追加、なけばカートに追加する
			if(cartRepository.checkCart(seqId,merId) > 0) {
				cartRepository.changeCartQuantity(merId,seqId,num);
				System.out.println("たすよ");
			}else {
				cartRepository.addCart(mer,seqId,num);
			}
		}
		return ires;
	}
	public InstantResponse subtractCart(int seqId,int merId,int num) throws IOException{
		ires.setFlg(true);
		//Merchandise mer = cartRepository.getMerchandise(merId);
		
		if(cartRepository.checkCart(seqId,merId) > 0) {
			Item item = cartRepository.getCartitem(seqId,merId);
			if(item.getQuantity() + num>=0 ) {
				cartRepository.changeCartQuantity(merId,seqId,num);
				//ストックを増やす
				ires.setFlg(cartRepository.stockadd(merId,num));
				System.out.println(" " + num +"引くよ");
			}else {
				ires.setFlg(false);
				
				//ires.setMsgid(msgid);
			}
		}else {
			ires.setFlg(false);
			//ires.setMsgid(msgid);
		}
		return ires;
	}
	
	public InstantResponse refreshCart(int seqId) {
		ires.setFlg(true);
		int refnum = cartRepository.refreshItems(seqId);
		ResCartGet cart = cartRepository.getCart(seqId);
		if(cart.getItems().size() > 0) {
			ires.setFlg(false);
		}
		return ires;
	}
	public ResCartGet getCart(int seqId) {
		ResCartGet cart = cartRepository.getCart(seqId);
		return cart;
	}
}