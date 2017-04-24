package com.tutorialspoint;
import java.net.*;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Locationapi {

	
	public static String getlocation(String address) throws IOException {
		StringBuilder content = new StringBuilder();
		String faddr=address.replaceAll(" ","+");
		String str1="http://maps.googleapis.com/maps/api/geocode/json?address="+faddr;
	
	//	System.out.println(str1);
		  try
		    {
			  URL url = new URL(str1);

			  URLConnection urlConnection = url.openConnection();

			  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			  String line;

	      while ((line = bufferedReader.readLine()) != null)
	      {
	    	  content.append(line + "\n");
	      }
	      		bufferedReader.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		  
		 // JSONParser parser = new JSONParser();
		  
		  /*Object obj = parser.parse(content.toString());
		 JSONObject jsonObject = (JSONObject) obj;  
		String name = (String) jsonObject.get(0);  
		System.out.println(name);*/
		 
		 //System.out.println(obj.getJSONArray("results").getJSONObject(0));
		  JSONObject obj;
		try {
		//	System.out.println("\n\n");
			obj = new JSONObject(content.toString());
			 JSONObject first = obj.getJSONArray("results").getJSONObject(0);
			String second= first.getString("formatted_address");
			// System.out.println(second.toString());
			 
			 return second;
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		 
		
	   // return content.toString();
	
		
	}
	
}
