import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

        public void sendEmail(String toEmailAddress, String subject, String bodyText) {
                    Properties props = new Properties();
                    props.setProperty("mail.smtp.host", "smtp.gmail.com");
                    props.setProperty("mail.smtp.starttls.enable", "true");
                    props.setProperty("mail.smtp.port","587");
                    props.setProperty("mail.smtp.user", "ejemplo@gmail.com");
                    props.setProperty("mail.smtp.auth", "true");

                    Session session = Session.getDefaultInstance(props);
                    session.setDebug(true);

                    MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress("authorizedmailer@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
                message.setSubject("Hola");
                message.setContent(bodyText,"text/html");

                Transport t = session.getTransport("smtp");

                t.connect("authorizedmailer@gmail.com","xazhqaazawegdfcm");

                t.sendMessage(message,message.getAllRecipients());

                t.sendMessage(message,message.getAllRecipients());
     
            } catch (MessagingException e) {
     
                e.printStackTrace();
            }
            System.out.println("Email sent successfully...");
        }

}