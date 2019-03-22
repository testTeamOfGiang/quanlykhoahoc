package exception;

public class DateSaiException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9163447810651249726L;

	@Override
	public String getMessage() {
		return "Định dạng ngày tháng phải là dd/MM/yyyy hoặc yyyy/MM/dd";
	}
	
}
