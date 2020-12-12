package change.domai.service;
import change.domai.model.Cart;
import change.domai.model.LoginForm;
import change.domai.model.LoginInfo;
import change.domai.model.User;
import change.domai.model.UserInfo;
import change.domai.repository.user.UserRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	//idの重複チェック。すでに登録済みのidと合致した場合はflaseを返す。
	public boolean IdUniqueCheck(String id) throws IOException {
		boolean flag = true;
		//id検索
	
		if(userRepository.IdCount(id) > 0) {
			flag = false;
		}
		return flag;
	}
	
	public boolean UserRegister(UserInfo userinfo) throws ParseException, IOException {
		boolean flag = false;
		//id検索
		System.out.println("id:"+userinfo.getId());
		System.out.println("id:"+userinfo.getPassword());
		if(userRepository.InsertUser(userinfo) >0) {
			int seqid = userRepository.getSeqId(userinfo.getId());
			userinfo.setSequential_id(seqid);
			if(userRepository.InsertUserInfo(userinfo)>0){
				flag = true;
			}
		}

		return flag;
	}
	public boolean login_auth(LoginForm form) {
		boolean flag = false;
		if(userRepository.userAuth(form.getId(),form.getPassword()) == 1) {
			flag = true;
		}
		return flag;
	}
	
	//id,pwがOKならセッションにユーザー情報とカートを格納する
	public void loginProc(String id,LoginInfo loginfo,Cart cart) {
		int seqId = userRepository.getSeqId(id);
		//LoginInfo loginfo = new LoginInfo();
		userRepository.getUser(loginfo,id,seqId);
		System.out.println(loginfo.getFirstName());
		System.out.println(loginfo.getLastName());
		System.out.println(loginfo.getUserId());

		//List<Cart> cartlist = new ArrayList<Cart>();
		if(cart.getItems().isEmpty()) {
			System.out.println("カートは空です");
		}
		
		if(userRepository.getCartNum(seqId)> 0){
			userRepository.getCart(cart,seqId);
		}
	}
}
