package com.tutorialspoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSms {



        public static String sendsms(String num) {
            try {
                // Construct data
            	String user = "username=" + "srimayiinnovations@gmail.com";
    			String hash = "&hash=" + "cba882cd84209142720f71d8283b0270301d20a732a0790b605def07d6724e9b";
    			String message = "&message=" + "https://play.google.com/store/apps/details?id=com.withvihik.vihik";
    			String sender = "&sender=" + "vihiky";
    			String numbers = "&numbers=" +"91"+num;
    			
    			// Send data
    			HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
    			String data = user + hash + numbers + message + sender;
    			conn.setDoOutput(true);
    			conn.setRequestMethod("POST");
    			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
    			conn.getOutputStream().write(data.getBytes("UTF-8"));
    			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			final StringBuffer stringBuffer = new StringBuffer();
    			String line;
    			while ((line = rd.readLine()) != null) {
    				stringBuffer.append(line);
    			}
    			rd.close();
    			
    			return stringBuffer.toString();
    		} catch (Exception e) {
    			System.out.println("Error SMS "+e);
    			return "Error "+e;
            }
        }
    }
