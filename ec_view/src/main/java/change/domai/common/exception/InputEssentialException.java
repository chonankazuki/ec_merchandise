package change.domai.common.exception;

public class InputEssentialException extends Exception{
	private String code;
	public  InputEssentialException () {
		code = "10002";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
