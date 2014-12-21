
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail {

	public static void send(String to,String sub,String msg,final String user,final String pass)
	{
		Properties pop=new Properties();
		pop.put("mail.smtp.host", "smtp.gmail.com");
		pop.put("mail.smtp.port", "587");
		pop.put("mail.smtp.auth", "true");
		pop.put("mail.smtp.starttls.enable", "true");
		Session session=Session.getInstance(pop,new javax.mail.Authenticator(){
			protected  PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(user, pass);
			}
		});
		
		try{
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			Transport.send(message);
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
