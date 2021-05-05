package change.domai.common.exception;

public class AlreadyLoginException extends Exception{
	private String code;
	public  AlreadyLoginException () {
		code = "10001";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
