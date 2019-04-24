package utils;

public class CheckPhone {

	public static final String template="^0[0-9]{9}";
	
	public static boolean check(String s) {
		if(s.matches(template))
			return true;
		else
			return false;
	}
	
}
