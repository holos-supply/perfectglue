package org.perfectglue.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnector {
	public static String getRestCall(String RESTpath) {
		  try {
			URL url = new URL(RESTpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			String outputMessage ="";
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				outputMessage.concat(output);
				System.out.println(outputMessage);
			}

			conn.disconnect();

			return outputMessage;
			
		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  
		  return "Something went wrong.";

	}

}
