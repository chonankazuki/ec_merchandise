package change.domai.model.response;

import org.springframework.stereotype.Component;

@Component
public class InstantResponse {
	
	private boolean flg;
	private String msgid;
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public boolean isFlg() {
		return flg;
	}
	public void setFlg(boolean flg) {
		this.flg = flg;
	}

}
