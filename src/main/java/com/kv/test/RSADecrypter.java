package com.kv.test;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class RSADecrypter {
	public RSADecrypter() {
	}

	public static String decrypt(String text, String key) {
		String decryptedText  = null;
		try {
			PrivateKey privateKey = KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)) );
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			//byte []data = text.getBytes("UTF-8");
			byte []dectyptedTextByte  = cipher.doFinal(text.getBytes("ISO-8859-1"));
			if( dectyptedTextByte != null){
				decryptedText = new String(dectyptedTextByte, "ISO-8859-1");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return decryptedText;
	}

	public static PrivateKey getPrivateKeyFromKeyString(String privateKeyString) {
		RSAPrivateKey privateKey = null;
		try {
			
			byte[] encodedPrivateKey = Base64.getDecoder().decode(privateKeyString); // Base64.getEncoder().encodeToString(privateKeyBytes);// ,
																							// Base64.getUrlDecoder());

			KeyFactory factory = KeyFactory.getInstance("RSA");
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodedPrivateKey, "RSA");
			privateKey = (RSAPrivateKey) factory.generatePrivate(secretKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}
	
	public static void main(String []args) throws Exception{
		
	}

}
