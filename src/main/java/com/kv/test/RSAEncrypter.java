package com.kv.test;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.kv.test.Base64Utils;

public class RSAEncrypter {

	public static PublicKey getPublicKeyFromKeyString(String publicKeyString){
		PublicKey publicKey = null;
		try {
			byte[] encodedPublicKey = Base64Utils.getBase64Decoded(publicKeyString.getBytes("ISO-8859-1")); 
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodedPublicKey, "RSA");
			
			publicKey = factory.generatePublic(secretKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static String encrypt(String text, String key) {
		String encryptedText = null;
		try {
			// get an RSA cipher object and print the provider
			PublicKey publicKey = KeyFactory.getInstance("RSA")
						.generatePublic(new X509EncodedKeySpec(Base64Utils.getBase64Decoded(key.getBytes("ISO-8859-1"))));
			final Cipher cipher = Cipher.getInstance("RSA");
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			byte []cipherText = cipher.doFinal(text.getBytes("ISO-8859-1"));
			encryptedText = new String(cipherText, "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedText;
	}
	
	public static void main(String arg[]) throws Exception{
		
		String base64IncText = encrypt("cointribe@123", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKU+5SLFKDEGGnyieow0Kx3p9O0wImTNwvCMHn+c4AynzhC5+BpUVQVDCiJy+CSad2MLGsVvO8/budNDWI8LRa0CAwEAAQ==");
		base64IncText = Base64.getEncoder().encodeToString(base64IncText.getBytes("ISO-8859-1"));
		System.out.println(base64IncText);
		
		String base64Password = "QOgj/9cxmUmTmxMDun1L3G4BTKlMNx5RwSM5WNKsO9jHIllohrJ0bEwpcjW2LRMHR6Eb/jOGqmUJ1vT0XuTzoA==";
		String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEApT7lIsUoMQYafKJ6jDQrHen07TAiZM3C8Iwef5zgDKfOELn4GlRVBUMKInL4JJp3YwsaxW87z9u500NYjwtFrQIDAQABAkEAkH/gZThc/gmlTcnTOhlAfwcgS+1klhusVSdiYBtxfP4xU3cWJUihGCG1D4nCvh3qbnlUDCYMCA0TJ2ZnbskWAQIhAOujqUW5Jb6P5RGjuHUk7CIJOv+haUUXbAkl133fGVqBAiEAs4YgtnhBTqpsM6RC3bKQY3lJ3rabneXfz60mmlf13S0CIEM8c7RdXBO/41DhMmTaoDYmBBuk5vC7+iuyDIbx/b4BAiEAnft2BD21cw3EpHl0IncGW5Ob7zMRpRVrLO46X27yvY0CICvRQDH8Se6FiWqIrpeZhXXmbC6cbYKtmaonwM+uaMu9";
		String decryptedText = RSADecrypter.decrypt(new String(Base64Utils.getBase64Decoded(base64Password)), privateKey);
		System.out.println(decryptedText);
		
//		String loginId ="coinSource";
//		String generatePassword = HashUtil.getHash(loginId + HashUtil.getHash(decryptedText, Constants.HASH_ALGORITHM), Constants.HASH_ALGORITHM);
//		System.out.println(generatePassword);
		
		
	}

}
