package id.bootcamp.java310.mipro.tab_profil.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.tab_profil.dto.UserDTO;
import id.bootcamp.java310.mipro.tab_profil.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository ur;

	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return ur.getAllUsers();
	}

	public List<UserDTO> getUserByBiodataID(Long biodataID) {
		// TODO Auto-generated method stub
		return ur.getUserByBiodataID(biodataID);
	}

	public List<UserDTO> getBiodatayUserID(Long id) {
		// TODO Auto-generated method stub
		return ur.getBiodatayUserID(id);
	}

	public Long updateEmail(String email, Long modifyBy, Long biodataID) {
		// TODO Auto-generated method stub
		return ur.updateEmail(email, modifyBy, biodataID);
	}

	public void updatePassword(String oldPassword, String password, String password2, Long modifyBy, Long biodataID) throws Exception {
		// TODO Auto-generated method stub
		Boolean isPasswordEqual = ur.isPasswordEqual(password, biodataID);
		if (isPasswordEqual) {
			throw new Exception("24-Password tidak boleh sama dengan password saat ini!");
		}
		
		if (password.equals("")) {
			throw new Exception("21-Password harus diisi!");
		}

		if (password.length() < 8) {
			throw new Exception("22-Panjang password minimal 8 karakter");
		}

		if (password.length() > 255) {
			throw new Exception("25-Panjang password tidak boleh lebih dari 255 karakter");
		}

		if (!(isContainsLowerCase(password)) && !(isContainsUpperCase(password)) && !(isContainsNumber(password))
				&& !(isContainsSpecChar(password))) {
			throw new Exception(
					"23-Password harus mengandung huruf kecil, huruf kapital, angka, dan special character!");

		} else if (!(isContainsLowerCase(password)) && !(isContainsNumber(password))
				&& !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, angka, dan special character!");
		} else if (!(isContainsUpperCase(password)) && !(isContainsNumber(password))
				&& !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung huruf kapital, angka, dan special character!");
		} else if (!(isContainsLowerCase(password)) && !(isContainsUpperCase(password))
				&& !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, huruf kapital, dan special character!");
		} else if (!(isContainsLowerCase(password)) && !(isContainsUpperCase(password))
				&& !(isContainsNumber(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, huruf kapital, dan angka!");

		} else if (!(isContainsLowerCase(password)) && !(isContainsUpperCase(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, dan huruf kapital!");
		} else if (!(isContainsLowerCase(password)) && !(isContainsNumber(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, dan angka!");
		} else if (!(isContainsLowerCase(password)) && !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil, dan special character!");

		} else if (!(isContainsUpperCase(password)) && !(isContainsNumber(password))) {
			throw new Exception("23-Password harus mengandung huruf kapital, dan angka!");
		} else if (!(isContainsUpperCase(password)) && !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung huruf kapital, dan special character!");

		} else if (!(isContainsNumber(password)) && !(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung angka, dan special character!");

		} else if (!(isContainsLowerCase(password))) {
			throw new Exception("23-Password harus mengandung huruf kecil!");
		} else if (!(isContainsUpperCase(password))) {
			throw new Exception("23-Password harus mengandung huruf kapital!");
		} else if (!(isContainsNumber(password))) {
			throw new Exception("23-Password harus mengandung angka!");
		} else if (!(isContainsSpecChar(password))) {
			throw new Exception("23-Password harus mengandung special character!");
		}

		if (password2.equals("")) {
			throw new Exception("31-Ketik ulang password!");
		}

		if (!(password2.equals(password))) {
			throw new Exception("32-Password tidak sama!");
		}	
		
		ur.updateOldPasswordOnResetPassword(oldPassword, password, modifyBy);
		
		ur.updatePassword(password, modifyBy, biodataID);
	}

	// Update Data Pribadi
	public void updatePersonalData(Long id, String fullname, String dob, String mobile_phone, Long modifyBy)
			throws Exception {
		// TODO Auto-generated method stub
		if (fullname.equals("")) {
			throw new Exception("31-Nama Lengkap harus diisi!");
		}

		if (fullname.length() > 255) {
			throw new Exception("32-Panjang Nama Lengkap tidak boleh lebih dari 255 karakter!");
		}

		if (dob.equals("")) {
			throw new Exception("33-Tanggal Lahir harus diisi!");
		}

		if (!(isValidDateFormat(dob))) {
			throw new Exception("34-Format Tanggal Lahir tidak sesuai!");
		}

		if (mobile_phone.equals("")) {
			throw new Exception("35-Mobile Phone harus diisi!");
		}

		if (mobile_phone.length() > 15) {
			throw new Exception("36-Panjang Mobile Phone tidak boleh lebih dari 255 karakter!");
		}
		
		if (!(isValidPhoneFormat(mobile_phone))) {
			throw new Exception("37-Format Mobile Phone tidak sesuai!");
		}

		Long biodataID = ur.updateBiodata(id, fullname, mobile_phone, modifyBy);

		Locale localeID = new Locale("id", "ID");

		String pattern = "dd MMMM yyyy";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern, localeID);

		Date dobDate = sdf.parse(dob);

		ur.updateCustomer(dobDate, modifyBy, biodataID);
	}

	public boolean isContainsLowerCase(String pw) {
		int count = 0;

		// checking small letters
		for (int i = 97; i <= 122; i++) {

			// type casting
			char c = (char) i;
			String lowerCaseLetter = Character.toString(c);

			if (pw.contains(lowerCaseLetter)) {
				count = 1;
			}
		}
		if (count == 0) {
			return false;
		}

		// if all conditions fails
		return true;
	}

	public boolean isContainsUpperCase(String pw) {
		int count = 0;

		// checking small letters
		for (int i = 65; i <= 90; i++) {

			// type casting
			char c = (char) i;
			String upperCaseLetter = Character.toString(c);

			if (pw.contains(upperCaseLetter)) {
				count = 1;
			}
		}
		if (count == 0) {
			return false;
		}

		// if all conditions fails
		return true;
	}

	public boolean isContainsNumber(String pw) {
		int count = 0;

		// checking small letters
		for (int i = 0; i <= 9; i++) {

			// type casting
			String number = Integer.toString(i);

			if (pw.contains(number)) {
				count = 1;
			}
		}
		if (count == 0) {
			return false;
		}

		// if all conditions fails
		return true;
	}

	public boolean isContainsSpecChar(String pw) {

		if (!(pw.contains("@") || pw.contains("#") || pw.contains("!") || pw.contains("~") || pw.contains("$")
				|| pw.contains("%") || pw.contains("^") || pw.contains("&") || pw.contains("*") || pw.contains("(")
				|| pw.contains(")") || pw.contains("-") || pw.contains("+") || pw.contains("/") || pw.contains(":")
				|| pw.contains(".") || pw.contains(",") || pw.contains("<") || pw.contains(">") || pw.contains("?")
				|| pw.contains("|") || pw.contains("_"))) {
			return false;
		}
		return true;
	}

	public boolean isValidDateFormat(String dob) {
		/*
		 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
		 */
		Locale localeID = new Locale("id", "ID");

		String pattern = "dd MMMM yyyy";

		SimpleDateFormat sdfrmt = new SimpleDateFormat(pattern, localeID);
		sdfrmt.setLenient(false);
		/*
		 * Create Date object parse the string into date
		 */
		try {
			Date dobDate = sdfrmt.parse(dob);
			System.out.println(dobDate + " is valid date format");
		}
		/* Date format is invalid */
		catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		/* Return true if date format is valid */
		return true;
	}

	public boolean isValidPhoneFormat(String mobile_phone) throws ParseException {
		
		String allCountryPhoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		
		String withZeroRegex = "^(\\d{4}[- ]?){2}\\d{4}$";
		
		Pattern pattern = Pattern.compile(allCountryPhoneRegex);

		Pattern pattern1 = Pattern.compile(withZeroRegex);
		
		Matcher matcher = pattern.matcher(mobile_phone);

		Matcher matcher1 = pattern1.matcher(mobile_phone);
		
		if (!(matcher.matches()) && !(matcher1.matches())) {
			return false;
		}
		/* Return true if date format is valid */
		return true;
	}
	
}
