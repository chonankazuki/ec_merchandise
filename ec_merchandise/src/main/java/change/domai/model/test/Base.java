package change.domai.model.test;

public abstract class Base implements TestInterface{
	private int num;
	private String test;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
	public abstract String call();
	

}
