package org.registration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Test {

	public static void main(String[] args) throws Exception { 

	    SSLSocketFactoryEx factory = new SSLSocketFactoryEx(null,null,null); 

	    String[] supportedProtocols = factory.getDefaultProtocols(); 
	    System.out.println("Protocols: " + supportedProtocols.length); 
	    for(int i = 0; i < supportedProtocols.length; i++) 
	    { 
	        System.out.println("  " + supportedProtocols[i]); 
	    } 

	    System.out.println(); 

	    String[] supportedCiphers = factory.getDefaultCipherSuites(); 
	    System.out.println("Ciphers: " + supportedCiphers.length); 
	    for(int i = 0; i < supportedCiphers.length; i++) 
	    { 
	        System.out.println("  " + supportedCiphers[i]); 
	    } 

	    System.out.println(); 

	    URL url = new URL("https://www.google.com:443"); 

	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); 
	    connection.setSSLSocketFactory(factory); 
	    connection.setRequestProperty("charset", "utf-8"); 

	    InputStream input = connection.getInputStream(); 
	    InputStreamReader reader = new InputStreamReader(input, "utf-8"); 
	    BufferedReader buffer = new BufferedReader(reader); 

	    String cipher = connection.getCipherSuite(); 
	    System.out.println("Cipher: " + cipher); 
	    System.out.println(); 

	    String line; 
	    while ((line = buffer.readLine()) != null) { 
	        System.out.println(line); 
	    }
	   
	} 
	}
