package com.kv.j8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class SendSms {
	private static URL url;
	private  static HttpURLConnection conn;
	
	public static void main(String... args) throws Exception{
		System.out.println("Starting here....");
		StringBuffer urlParamsStatic = new StringBuffer();
		urlParamsStatic.append("?method=sendMessage");
		urlParamsStatic.append("&userid=").append("2000147894");
		urlParamsStatic.append("&password=").append(URLEncoder.encode("abc123", "UTF-8"));
		urlParamsStatic.append("&v=1.1").append("&msg_typ=TEXT").append("&auth_scheme=PLAIN");
		
		String msg = "We observe that you have filled incomplete details in your DBS Bank Loan application form. Kindly fill the form to get Loan approvals.* T&C Apply.";
		
		StringBuilder sbr = new StringBuilder(urlParamsStatic);
		sbr.append("&msg=").append(URLEncoder.encode(msg, "UTF-8")).append("&send_to=")
				.append(URLEncoder.encode("9999877866", "UTF-8"));

			sbr.append("&mask=").append("DBSSME").append("&");


		url = new URL("http://enterprise.smsgupshup.com/GatewayAPI/rest" + sbr.toString());
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.connect();
		conn.getInputStream();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line).append("\n");
		}
//		if (logger.isDebugEnabled())
			System.out.println(buffer.toString());
		rd.close();

		conn.disconnect();

		System.out.println("Sms Sent !!!");
	}
}
