package change.domai.common.exception;

public class SomethingWrongException extends Exception{
	private String code;
	public  SomethingWrongException () {
		code = "99999";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
