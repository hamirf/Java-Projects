package id.bootcamp.java310.mipro.tab_profil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;
	
	//Mengambil value dari application.properties
	@Value("${spring.mail.username}")
	private String sender;
	
	public void sendEmail(
			String recipient,
			String subject,
			String msgBody) throws Exception {
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom(sender);
			mail.setTo(recipient);
			mail.setSubject(subject);
			mail.setText(msgBody);
			
			jms.send(mail);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("21_Failed to send email");
		}
		
	}
	
}
