package com.tutorialspoint;

import java.io.ByteArrayOutputStream;
import java.net.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.simple.JSONObject;

public class GGcoder {
	  private static final String URL         = "http://maps.google.com/maps/geo?output=json";
	  private static final String DEFAULT_KEY = "YOUR_GOOGLE_API_KEY"; 
	  
	  public static GAddress geocode(String address, String key) throws Exception {
	    URL url = new URL(URL + "&q=" + URLEncoder.encode(address, "UTF-8")
	        + "&key=" + key);
	    URLConnection conn = url.openConnection();
	    ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
	    IOUtils.copy(conn.getInputStream(), output);
	    output.close();

	    GAddress gaddr = new GAddress();
	    JSONObject json = JSONObject.fromString(output.toString());
	    JSONObject placemark = (JSONObject) query(json, "Placemark[0]");

	    final String commonId = "AddressDetails.Country.AdministrativeArea";

	    gaddr.setFullAddress(query(placemark, "address").toString());
	    gaddr.setZipCode(query(placemark,
	        commonId + ".SubAdministrativeArea.Locality.PostalCode.PostalCodeNumber")
	        .toString());
	    gaddr.setAddress(query(placemark,
	        commonId + ".SubAdministrativeArea.Locality.Thoroughfare.ThoroughfareName")
	        .toString());
	    gaddr.setCity(query(placemark,
	        commonId + ".SubAdministrativeArea.SubAdministrativeAreaName").toString());
	    gaddr.setState(query(placemark, commonId + ".AdministrativeAreaName").toString());
	    gaddr.setLat(Double.parseDouble(query(placemark, "Point.coordinates[1]")
	        .toString()));
	    gaddr.setLng(Double.parseDouble(query(placemark, "Point.coordinates[0]")
	        .toString()));
	    return gaddr;
	  }

	  public static GAddress geocode(String address) throws Exception {
	    return geocode(address, DEFAULT_KEY);
	  }

	  /* allow query for json nested objects, ie. Placemark[0].address */
	  private static Object query(JSONObject jo, String query) {
	    try {
	      String[] keys = query.split("\\.");
	      Object r = queryHelper(jo, keys[0]);
	      for (int i = 1; i < keys.length; i++) {
	        r = queryHelper(jo.fromObject(r), keys[i]);
	      }
	      return r;
	    } catch (JSONException e) {
	      return "";
	    }
	  }

	  /* help in query array objects: Placemark[0] */
	  private static Object queryHelper(JSONObject jo, String query) {
	    int openIndex = query.indexOf('[');
	    int endIndex = query.indexOf(']');
	    if (openIndex > 0) {
	      String key = query.substring(0, openIndex);
	      int index = Integer.parseInt(query.substring(openIndex + 1, endIndex));
	      return jo.getJSONArray(key).get(index);
	    }
	    return jo.get(query);
	  }
}
