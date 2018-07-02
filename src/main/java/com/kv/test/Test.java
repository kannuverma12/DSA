package com.kv.test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;



public class Test {

	public static void main(String [] args) throws Exception {
		
		System.out.println("decode : "+Base64.decodeBase64("Q29pbkAx"));
		//System.out.println("decode : "+Base64.decode("Q29pbkAx"));
		
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        // encrypt the message
        byte [] encrypted = encrypt(privateKey, "ANIL KUMAR");     
        //System.out.println(new String(encrypted));  // <<encrypted message>>
        
        // decrypt the message
        String aa = "U2FsdGVkX18pEUVmE3SF9apwmh2lJPpbBhgBUPG06ks=";
        byte[] arr = aa.getBytes();
        byte[] secret = decrypt(pubKey, arr);                                 
        System.out.println(new String(secret));     // This is a secret message
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }

}
