package mycustom;

public class LIH_Cell {
	private String value;
	/**
	 * -1 = default 0 = chưa có lớp 1 = đã có lớp
	 */
	private int status;

	public LIH_Cell() {
		
	}
	
	public LIH_Cell(String value, int status) {
		this.setValue(value);
		this.setStatus(status);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
