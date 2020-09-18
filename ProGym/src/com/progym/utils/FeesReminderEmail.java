package com.progym.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.progym.model.EmailDataObject;
import com.sun.mail.smtp.SMTPTransport;

public class FeesReminderEmail {
	
	public static  String SMTP_SERVER = "smtp.gmail.com";
	public static  String USERNAME = "progymkop@gmail.com";
	public static  String PASSWORD = "pooja@418";

	public static  String EMAIL_FROM = "progymkop@gmail.com";
	public static  String EMAIL_TO = "patilpranav77d@gmail.com";
	public static  String EMAIL_TO_CC = "";
	public static  String EMAIL_SUBJECT = "Payment Received";
	public static String CLIENT_NAME ="";
	public static String PACKAGE_NAME ="";
	public static String DURATION ="";
	public static String PAYMENT_DATE ="";
	public static String PAID_AMOUNT ="";
	public static String REMAINING_AMOUNT ="";
	
	static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null!");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }
   
   public static void sendEmail(String email , String subject , String clientName , String packageName , String packageDuration , String daysLeft , String paidFees , String pendingFees , String totalPackageFees , String messageLine){
	   Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));

            msg.setSubject(subject);

			// TEXT email
            //msg.setText(EMAIL_TEXT);

			// HTML email
            msg.setDataHandler(new DataHandler(new HTMLDataSource("<html>"+
            		
            	""+
            	"<body style=\"background-color:#e2e1e0;font-family: cursive, sans-serif;font-size:100%;font-weight:300;line-height:1.4;color:#000;\">"+
            	"  <table style=\"max-width:450px;margin:30px auto 10px;background-color:#fff;padding:20px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:20px;-webkit-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);-moz-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24); border-top: solid 3px blue;border-bottom: solid 3px blue;border-left: solid 3px blue;border-right: solid 3px blue;\">"+
            	"    <thead>"+
            	"      <tr>"+
            	"        <th style=\"text-align:left;\"><img style=\"max-width: 100px;\" src=\"https://i.ibb.co/RzLBKYq/pro-gym.jpg\" alt=\"Pro Gym Kolhapur\"></th>"+
            	"        <th style=\"text-align:center;font-weight:400;font-size:12px;\">"+new SimpleDateFormat("E, dd MMM yyyy").format(new Date())+"</th>"+
            	"      </tr>"+
            	"    </thead>"+
            	"    <tbody>"+
            	"      <tr>"+
            	"        <td style=\"height:15px;font-size:12px;font-weight:400;padding:0px 0px 0px 0px;\"></br>Hi "+clientName+"</td>		"+
            	"      </tr>"+
            	"	  <tr>"+
            	"        <td style=\"height:15px;font-weight:400;font-size:12px;padding:0px 0px 5px;\"></br>"+messageLine+"</td>		"+
            	"      </tr>"+
            	"	  "+
            	"      <tr>"+
            	"        <td colspan=\"2\" style=\"border: solid 1px #FFAD00; padding:10px 10px;\">"+
            	"          <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Package Name</span>"+packageName+"</p>"+
            	"          <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Duration</span>"+packageDuration+"</p>"+
            	"		  <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Days Left</span>"+daysLeft+"</p>"+
            	"		  <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Paid Fees</span>Rs. "+paidFees+"</p>"+
            	"		  <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Remaining Fees</span>Rs. "+pendingFees+"</p>"+
            	"		  <p style=\"font-size:10px;margin:0 0 6px 0;\"><span style=\"font-weight:bold;display:inline-block;min-width:100px\">Total Fees</span>Rs. "+totalPackageFees+"</p>"+
            	"        </td>"+
            	"      </tr>"+
            	"     "+
            	"     "+
            	"    </tbody>"+
            	"    <tfooter>"+
            	"      <tr>"+
            	"        <td colspan=\"2\" style=\"font-size:12px;padding:10px 15px 10px 15px;\">"+
            	"          <strong style=\"display:block;margin:0 0 10px 0;\">Regards</strong> Pro Gym,Kolhapur<br>"+
            	"          <b>Phone:</b>(8796655176) (0231-2950426)"+
            	"        </td>"+
            	"      </tr>"+
            	"      <tr>"+
            	"        <td>"+
            	"<a href=\"https://www.facebook.com/djpranav77\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://www.linkpicture.com/q/fb_icn.png\" alt=\"fb_icon\"></a>"+
            	"<a href=\"https://www.instagram.com/progymkop/\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://www.linkpicture.com/q/insta_icn.jpg\" alt=\"insta_icn\"></a>"+
            	"<a href=\"https://wa.link/1frr6m\"><img style=\"padding:2px 3px 0px 5px; max-width: 30px;\" src=\"https://www.linkpicture.com/q/waff.png\" alt=\"whatsapp_icn\"></a></br>"+
            	"        </td>"+
            	"      </tr>"+
            	"      <tr>"+
            	"        <td align=\"center\" colspan=\"2\" style=\"border: solid 1px #28FF00; font-size:9px;padding:1px 8px 8px 8px;\">"+
            	"          <b>Software Developed By :<a href=\"https://www.facebook.com/pvbhat7\">Prashant Bhat</a></b>"+
            	"<a href=\"tel:8796238220\"><img style=\"padding:2px 3px 0px 7px; max-width: 25px;\" src=\"https://www.linkpicture.com/q/call_icn.png\" alt=\"call_icon\"></a>"+
            	"<a href=\"https://wa.link/socany\"><img style=\"padding:2px 3px 0px 5px; max-width: 30px;\" src=\"https://www.linkpicture.com/q/waff.png\" alt=\"whatsapp_icon\"></a>"+
            	"<a href=\"mailto:bhatprashant1994@gmail.com?subject=Software Development Enquiry&body=Hi , I need more infor about software development , referred by ProGym\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://www.linkpicture.com/q/eml.png\" alt=\"email_icon\">"+
            	"        </td>"+
            	"      </tr>"+
            	"    </tfooter>"+
            	"  </table>"+
            	"</body>"+
            	""+
            	"</html>")));


			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
   }
}
