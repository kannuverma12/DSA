package com.kv.test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Base64Utils {
	static String CHAR_ENCODING = "ISO-8859-1";

	public static String getBase64Encoded(String toEncode) throws UnsupportedEncodingException{
		return new String(Base64.getEncoder().encode(toEncode.getBytes(CHAR_ENCODING)), CHAR_ENCODING);
	}
	
	public static String getBase64Decoded(String toDecode) throws UnsupportedEncodingException{
		return new String(Base64.getDecoder().decode(toDecode.getBytes(CHAR_ENCODING)), CHAR_ENCODING);
	}
	
	public static byte[] getBase64Encoded(byte [] toEncode) throws UnsupportedEncodingException{
		return Base64.getEncoder().encode(toEncode);
	}
	
	public static byte[] getBase64Decoded(byte[] toDecode) throws UnsupportedEncodingException{
		return Base64.getDecoder().decode(toDecode);
	}
	
	public static String getBase64Encoded(String toEncode,String charEncoding) throws UnsupportedEncodingException{
		return new String(Base64.getEncoder().encode(toEncode.getBytes(charEncoding)), charEncoding);
	}
	
	public static String getBase64Decoded(String toDecode,String charEncoding) throws UnsupportedEncodingException{
		return new String(Base64.getDecoder().decode(toDecode.getBytes(charEncoding)), charEncoding);
	}
	
	public static String getBase64Encoded(byte [] toEncode,String charEncoding) throws UnsupportedEncodingException{
		return new String(Base64.getEncoder().encode(toEncode), charEncoding);
	}
	
	public static String getBase64Decoded(byte[] toDecode, String charEncoding) throws UnsupportedEncodingException{
		return new String(Base64.getDecoder().decode(toDecode), charEncoding);
	}
	
	
}
