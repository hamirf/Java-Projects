package id.bootcamp.java310.mipro.tab_profil.services;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.tab_profil.repositories.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tr;

	@Autowired
	private EmailService es;

	// Millis, gonta ganti waktu expired disini
	private final long expiredToken = 1000 * 60 * 3; // 5 menit

	String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	// Compile the ReGex
	Pattern p = Pattern.compile(regex);

	public void sendOTPToEmail(String email) throws Exception {
		// TODO Auto-generated method stub

		// 0. Apakah email kosong
		if (email.equals("")) {
			throw new Exception("11_E-mail harus diisi!");
		}

		// 1. Apakah format email valid
		Matcher m = p.matcher(email);

		if (!(m.matches())) {
			throw new Exception("12_Format e-mail tidak valid!");
		}

		// 2. Validasi apakah email sudah terpakai
		Boolean isEmailExists = tr.isEmailExists(email);
		if (isEmailExists) {
			throw new Exception("13_E-mail sudah terdaftar");
		}

		// 3. Validasi apakah otp sudah mencapai batas maksimal per email
		Boolean isMaxOTP = tr.isMaxOTP(email);
		if (isMaxOTP) {
			throw new Exception("14_OTP sudah mencapai maksimum!");
		}

		Long millisNow = System.currentTimeMillis(); // Ambil waktu sekarang
		Long millisExpired = millisNow + expiredToken; // Waktu sekarang + lama expired token
		Date expiredTime = new Date(millisExpired);

		// 3. Insert OTP ke Tabel
		String generatedToken = getAlphaNumericString(6);

		// 4. Kirim OTP ke email
		String recipient = email;
		String subject = "Request OTP Register POS 310";
		String msgBody = "OTP anda adalah: " + generatedToken;
		es.sendEmail(recipient, subject, msgBody);

		tr.insertOTP(generatedToken, email, "Update Email", expiredTime);
	}

	public void checkOTP(String email, String otp) throws Exception {
		// TODO Auto-generated method stub
		// 5. Validasi apakah otp benar
		Boolean isOTPCorrect = tr.isOTPCorrect(email, otp);
		if (!isOTPCorrect) {
			throw new Exception("15_OTP salah!");
		}

		// 6. Validasi apakah otp expire
		Boolean isOTPExpired = tr.isOTPExpired(email, otp);
		if (isOTPExpired) {
			tr.updateIsExpiredOTP(email, otp);
			throw new Exception("16_Kode OTP kadaluarsa, silahkan kirim ulang OTP");
		}

	}

	String getAlphaNumericString(int n) {

		// choose a Character random from this String
		String AlphaNumericString = "0123456789";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}
