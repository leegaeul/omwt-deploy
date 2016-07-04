package com.olleh.webtoon.http;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpRequest {

	@SuppressWarnings("deprecation")
	public String call(String url, boolean isGetMethod, HttpParameters httpParams) {

		HttpGet httpGet = null;
		HttpPost httpPost = null;
		HttpResponse response = null;
		HttpClient client = HttpClientBuilder.create().build();
		
		try {
			
			if(isGetMethod) {
				httpGet = new HttpGet(url);
				response = client.execute(httpGet);
			}
			else {
				httpPost = new HttpPost(url);
				if(httpParams != null && !httpParams.isEmpty()){
					UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(httpParams);						
					httpPost.setEntity(reqEntity);
				}
				
				response = client.execute(httpPost);
			}
			
			if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {
				
				BufferedReader bufferedReader = null;
				
				try {
					
					bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

					StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = bufferedReader.readLine()) != null) {
						result.append(line);
					}
					
					return result.toString();
					
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					if(bufferedReader != null) bufferedReader.close();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		
		return null;
	}
}