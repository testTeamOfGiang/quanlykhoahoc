package utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import exception.DateSaiException;

public class DateSQL {

	/**
	 * Chuyển string sang kiểu date SQL
	 * 
	 * @param input String định dạng dd/MM/yyyy or yyyy/MM/dd 
	 * 		  or dd-MM-yyyy or yyyy-MM-dd
	 * @return SQLDate
	 * @throws DateSaiException
	 * @throws NhapLungTungException
	 */
	public static Date parseDate(String input) throws DateSaiException {
		String[] data;
		if (input.indexOf('/') != -1) {
			data = input.split("/");
		} else {
			data = input.split("-");
		}
		if (data.length != 3)
			throw new DateSaiException();

		try {
			int year = 0, month = 0, day = 0;
			if (data[0].length() == 4) {
				year = Integer.parseInt(data[0]);
				month = Integer.parseInt(data[1]);
				day = Integer.parseInt(data[2]);
			} else if (data[2].length() == 4) {
				year = Integer.parseInt(data[2]);
				month = Integer.parseInt(data[1]);
				day = Integer.parseInt(data[0]);
			}

			if (year < 1900)
				throw new DateSaiException();

			// Check ngày, tháng
			switch (month) {
			case 1:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 2:
				if (day < 1)
					throw new DateSaiException();
				else if ((year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) && day > 28)
					throw new DateSaiException();
				else if (day > 29)
					throw new DateSaiException();
				else
					break;
			case 3:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 4:
				if (day > 30 || day < 1)
					throw new DateSaiException();
				break;
			case 5:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 6:
				if (day > 30 || day < 1)
					throw new DateSaiException();
				break;
			case 7:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 8:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 9:
				if (day > 30 || day < 1)
					throw new DateSaiException();
				break;
			case 10:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			case 11:
				if (day > 30 || day < 1)
					throw new DateSaiException();
				break;
			case 12:
				if (day > 31 || day < 1)
					throw new DateSaiException();
				break;
			default:
				throw new DateSaiException();
			}

			SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date date = fm.parse(year + "/" + month + "/" + day);
			java.sql.Date d = new java.sql.Date(date.getTime());
			return d;
		} catch (NumberFormatException e) {
			throw new DateSaiException();
		} catch (ParseException e) {
			throw new DateSaiException();
		}
	}
}
