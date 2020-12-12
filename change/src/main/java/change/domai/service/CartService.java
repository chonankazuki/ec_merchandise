package change.domai.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import change.domai.model.Cart;
import change.domai.model.Item;
import change.domai.model.Merchandise;
import change.domai.repository.cart.CartRepository;
import change.domai.repository.serach.SearchRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	public boolean addCart(int seqId,int merId,int num) throws IOException{
		//在庫チェック
		boolean flg = true;
		int stock = cartRepository.checkStock(merId);
		if(stock < num) {
		  flg = false;	
		}else {
			Merchandise mer = cartRepository.getMerchandise(merId);
			
			//ストックを減らす
			cartRepository.stockSubtract(merId,num);
		    //カートに同じ商品が同じ値段で入っていたら数量を追加、なけばカートに追加する
			if(cartRepository.checkCart(seqId,merId) > 0) {
				cartRepository.addCartQuantity(merId,seqId,num);
			}else {
				cartRepository.addCart(mer,seqId,num);
			}
		}
		return flg;
	}
	public boolean subtractCart(int seqId,int merId,int num) throws IOException{
		boolean flg = true;
		//Merchandise mer = cartRepository.getMerchandise(merId);
		
		//ストックを増やす
		cartRepository.stockadd(merId,num);
	    //カートに同じ商品が同じ値段で入っていたら数量を追加、なけばカートに追加する
		if(cartRepository.checkCart(seqId,merId) > 0) {
			cartRepository.addCartQuantity(merId,seqId,num);
		}
	return flg;
	}
	public Cart getCart(int seqId) {
		Cart cart = cartRepository.getCart(seqId);
		return cart;
	}
}