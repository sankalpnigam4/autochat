package com.tutorialspoint;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail {

	
	public static void sendm(String fname,String lname,String from,String subject,String messagef) {    
	      
	      String to = from;
	      
	      Properties props = new Properties();
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.port", "465");
         // props.put("mail.stmp.user" , "support@vihik.com");
          //props.put("mail.smtp.password", "Srimayi23*");
          props.put("mail.smtp.auth", "true");
         // props.put("mail.debug", "false");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.ssl.enable", "true");
          
          Authenticator auth = new Authenticator() {
              //override the getPasswordAuthentication method
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication("support@vihik.com", "Srimayi23*");
              }
          };
	      
 	      Session session = Session.getDefaultInstance(props,auth);

	      try {
	           MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress("support@vihik.com"));
     	       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
          	   message.setSubject(subject);
          	   message.setText(fname+" "+lname+"\n"+messagef);
          	   Transport.send(message);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
}
