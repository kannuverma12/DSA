package com.kv.test;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;



public class RSAKeyGenerator {
	
	public static String getEncoded(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static Object[] generateKey(String algorithm) {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance(algorithm);
		
			keyGen.initialize(512);

			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();
			
			Object key[] = { pub, priv };
			return key;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		Object[] keys = RSAKeyGenerator.generateKey("RSA");
		System.out.println("public = "+RSAKeyGenerator.getEncoded((Key)keys[0]));
		System.out.println("private = "+RSAKeyGenerator.getEncoded((Key)keys[1]));
		
		
//		GenerateKeys gk;
//		try {
//			gk = new GenerateKeys(1024);
//			gk.generateKey();
//			gk.writeToFile("/Users/karan.verma/Downloads/password_rsapublicKey", gk.getPublicKey().toString().getBytes());
//			gk.writeToFile("/Users/karan.verma/Downloads/password_rsaprivateKey", gk.getPrivateKey().toString().getBytes());
//		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//			System.err.println(e.getMessage());
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}

	}
}
