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

public class BdayEmailTemplate {
	
	public static  String SMTP_SERVER = "smtp.gmail.com";
	public static  String USERNAME = "progymkop@gmail.com";
	public static  String PASSWORD = "pooja@418";

	public static  String EMAIL_FROM = "progymkop@gmail.com";
	public static  String EMAIL_TO = "patilpranav77d@gmail.com";
	public static  String EMAIL_TO_CC = "";
	public static  String EMAIL_SUBJECT ="🎂वाढदिवसाच्या लाख लाख शुभेच्छा🎉,❤️उदंड आयुष्याच्या अनंत शुभेच्छा💪";
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
   
   public static void sendEmail2(String email, String name){
	   Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        MimeMessage msg = new MimeMessage(session);
        

        try {

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));

            msg.setSubject(EMAIL_SUBJECT, "UTF-8");
            
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
			// TEXT email
            //msg.setText(EMAIL_TEXT);

            String text = "<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
					+ "<body style=\"background-color:#e2e1e0;font-family: cursive, sans-serif;font-size:100%;color:#000;\">  "
					+ "<table style=\"max-width:355px;margin:30px auto 10px;background-color:#fff;padding:10px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:40px;-webkit-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);-moz-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24); border-top: solid 3px blue;border-bottom: solid 3px blue;border-left: solid 3px blue;border-right: solid 3px blue;\">    "
					+ "<thead>      "
					+ "<tr>        "
					+ "<th style=\"text-align:left;\"><img style=\"max-width: 100px;\" src=\"https://tavrostechinfo.com/PROGYM/img/pro-gym_email_logo.jpg\" alt=\"Pro gym kop\"></th>"
					+"<th style=\"text-align:center;font-weight:400;font-size:12px;\">"+new SimpleDateFormat("E, dd MMM yyyy").format(new Date())+"</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ "<tr>"
					+ "<td width=\"10px\" style=\"padding:0px; margin:0px font-size:15px;padding:0px 0px 0px 0px;\"></br><h2>Hi "+name+"</h2></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td stype=\"text-align: center; vertical-align: middle;\"><img style=\"max-width: 300px;border-radius:10px\" src=\"https://tavrostechinfo.com/PROGYM/img/birthday_1.jpg\" ></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td stype=\"text-align: center; vertical-align: middle;\"><img style=\"max-width: 300px;border-radius:10px\" src=\"https://tavrostechinfo.com/PROGYM/img/birthday_2.jpg\" ></td>"
					+ "</tr>"
					+ "</tbody>"
					+"    <tfooter>"+
	            	"      <tr>"+
	            	"        <td colspan=\"2\" style=\"font-size:12px;padding:10px 15px 10px 15px;\">"+
	            	"          <strong style=\"display:block;margin:0 0 10px 0;\">Regards</strong> Pro Gym,Kolhapur<br>"+
	            	"          <b>Phone:</b>(8796655176) (0231-2950426)"+
	            	"        </td>"+
	            	"      </tr>"+
	            	"      <tr>"+
	            	"        <td>"+
					"<a href=\"https://www.facebook.com/djpranav77\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://tavrostechinfo.com/PROGYM/img/fb_icon.png\" alt=\"fb_icon\"></a>"+
					"<a href=\"https://www.instagram.com/progymkop/\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://tavrostechinfo.com/PROGYM/img/insta_icon.jpg\" alt=\"insta_icn\"></a>"+
					"<a href=\"https://wa.link/1frr6m\"><img style=\"padding:2px 3px 0px 5px; max-width: 30px;\" src=\"https://tavrostechinfo.com/PROGYM/img/whatsapp_icon.png\" alt=\"whatsapp_icn\"></a></br>"+
	            	"        </td>"+
	            	"      </tr>"+
	            	"      <tr>"+
	            	"        <td align=\"center\" colspan=\"2\" style=\"border: solid 1px #28FF00; font-size:9px;padding:1px 8px 8px 8px;\">"+
	            	"          <b>Software Developed By :<a href=\"https://www.facebook.com/pvbhat7\">Prashant Bhat</a></b>"+
					"<a href=\"tel:8796238220\"><img style=\"padding:2px 3px 0px 7px; max-width: 25px;\" src=\"https://tavrostechinfo.com/PROGYM/img/call_icon.png\" alt=\"call_icon\"></a>"+
					"<a href=\"https://wa.link/socany\"><img style=\"padding:2px 3px 0px 5px; max-width: 30px;\" src=\"https://tavrostechinfo.com/PROGYM/img/whatsapp_icon.png\" alt=\"whatsapp_icon\"></a>"+
					"<a href=\"mailto:bhatprashant1994@gmail.com?subject=Software Development Enquiry&body=Hi , I need more infor about software development , referred by ProGym\"><img style=\"padding:2px 3px 0px 5px; max-width: 25px;\" src=\"https://tavrostechinfo.com/PROGYM/img/email_icon.png\" alt=\"email_icon\">"+
	            	"        </td>"+
	            	"      </tr>"+
	            	"    </tfooter>"+
					"</table>"
					+ "</body>"
					+ "</html>";
            
			// HTML email
            msg.setDataHandler(
            		new DataHandler(
            				new HTMLDataSource(text)
            				)
            		);


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
