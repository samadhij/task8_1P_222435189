package web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {
		// Match a fixed user name and password.
		//
		if ("ahsan".equals(username) && "ahsan_pass".equals(password) && "1996-07-27".equals(dob)) {
            return true;
        }
		return false;
	}
	
	public static boolean validateDob(String dob) {
        try {
            LocalDate date = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
            return !date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
	
}
