import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendFileEmail {
	public int sendEmail(String fromEmail, String toEmail, String fileNames,
			String subject, String body) {

		// Recipient's email ID needs to be mentioned.
		String from = fromEmail;

		// Sender's email ID needs to be mentioned
		String to = toEmail;

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.user", from); // User name
		properties.put("mail.smtp.password", "Certificate2015"); // password
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			// nyit.certificate.auth@gmail.com Certificate2015
			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			String filenames[] = fileNames.split(",");

			for (int i = 0; i < filenames.length; i++) {
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(filenames[i]);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filenames[i]);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", from, "Certificate2015");
			// Transport.send(message);
			InternetAddress i = new InternetAddress(to);
			Address[] a = new Address[1];
			a[0] = i;
			transport.sendMessage(message, a);
			transport.close();
			return 1;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return 0;
		}
	}
}