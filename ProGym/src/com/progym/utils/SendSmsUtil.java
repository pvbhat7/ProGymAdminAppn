package com.progym.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
public class SendSmsUtil {
	
	public static String triggerSms(String number,String body) {
		try {
			// Construct data
			String apiKey = "apikey=" + URLEncoder.encode("Th+VD0mruAY-xgh49ME0FIbGsYYGmCb5JCANn4KeOt", "UTF-8");
			String message = "&message=" + URLEncoder.encode(body, "UTF-8");
			String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
			String numbers = "&numbers=" + URLEncoder.encode(number, "UTF-8");
			
			/// Send data
			String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
			System.out.println(data);
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult="";
			while ((line = rd.readLine()) != null) {
			// Process line...
				sResult=sResult+line+" ";
			}
			rd.close();
			
			return sResult;
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}