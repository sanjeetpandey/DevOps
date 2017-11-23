package com.ri.hatest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class HATestClient {
 
	private int successCount = 0;
	
	private int failureCount = 0;
	
	public static void main(String[] args) throws Exception {
 
		int numberOfRequests = 1000;
		
		
		if( args.length != 0 ){
			try{
				numberOfRequests = Integer.parseInt(args[0]);
			}catch(Exception e){
				System.out.println("Invalid Input for Number of requests. Using default value: " + numberOfRequests);
			}
		}
		
		HATestClient haTestClient = new HATestClient();
 
		System.out.println("About to start Sending Requests...");
		for(int i=1; i<=numberOfRequests; i++){
			try{
			haTestClient.sendRequest(i);
			}catch(Exception e){
				e.printStackTrace();
				haTestClient.failureCount++;
			}
		}
		

		System.out.println("### Completed Sending Requests ###");
		
		System.out.println("Total Success Requests: " + haTestClient.successCount);
		System.out.println("Total Failed Requests: " + haTestClient.failureCount);
		
 
 
	}
 
	private void sendRequest(int requestId) throws Exception {
 
		String url = "http://host:8085/ha-testapp/hatest?requestId="+requestId;
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending request to URL : " + url);
		System.out.println("Response Code is : " + responseCode);
 
		if(responseCode == 200){
			successCount++;
		}else{
			failureCount++;
		}
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close(); 
		//print result
		System.out.println(response.toString());
		
	} 
}