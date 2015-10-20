package com.tile.map;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class DECApp {
	public static char[] Constant = new char[] { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
		'w', 'x', 'y', 'z'
	};
	private static Random Rd = new Random();
	public static void main(String[] args) {
		try {//////YTE2ekJGRUJGQkZGMDAwMjA2NTVhMTh6V0RDIFdEMzIwMExQVlgtMjJWMFRUMCBBVEEgRGV2aWNl
			start("YTE2ekJGRUJGQkZGMDAwMjA2NTVhMTh6V0RDIFdEMzIwMExQVlgtMjJWMFRUMCBBVEEgRGV2aWNl","-1");
		} catch (UnsupportedEncodingException e) {

		}
	}


	public static void start(String txtMcode,String data) throws UnsupportedEncodingException {

		byte[] b64=Base64.decodeBase64(txtMcode.getBytes());

		String str=new String(b64,"UTF8");
		str=str.replace("a5z", "amjz").replace("a9z", "a7z").replace("a6z", "a4z").replace("a8z", "a7z").replace("a16z", "a17z").replace("a18z", "a19z");		
		String s= MctoR1(str);		
		String strtt = GenerateRandom(0x24) + GenerateRandom(0x24) + s + GenerateRandom(0x24) + GenerateRandom(0x24);
		String datatt = Encode(data, ToMd5("KEY_64"), ToMd5("IV_64"));

		str = strtt + "#" + datatt;

	}

	private static String MctoR1(String s)
	{
		StringBuilder builder = new StringBuilder(0x20);		
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(s.getBytes("UTF-8"));

			int num2 = hash.length - 1;
			String stmp = "";
			for (int i = 0; i <= num2; i++)
			{
				stmp = (Integer.toHexString(hash[i] & 0XFF));
				if (stmp.length() == 1){
					builder.append("0" + stmp);
				}else{
					builder.append(stmp);
				}			
			}
		}catch (Exception e)
		{

		}

		return builder.toString();

	}

	private static String GenerateRandom(int length)
	{
		StringBuilder builder = new StringBuilder(0x24);
		int num2 = length - 1;
		for (int i = 0; i <= num2; i++)
		{
			builder.append(Constant[Rd.nextInt(0x24)]);
		}
		return builder.toString();
	}

	public static String ToMd5(String key)
	{
		String digest = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(key.getBytes());
			StringBuilder sb = new StringBuilder(2*hash.length);

			for(byte b : hash)
			{
				sb.append(String.format("%02x", b&0xff));
			}

			digest = sb.toString().substring(0,8).toUpperCase();
		}
		catch (Exception e)
		{

		}

		return digest;

	}

	public static String Encode(String data, String key64, String iv64) throws UnsupportedEncodingException
	{
		key64 = ToMd5(key64);
		iv64 = ToMd5(iv64);
		byte[] rgbKey = key64.getBytes("ASCII");
		byte[] rgbIV = iv64.getBytes("ASCII");
		String str;

		try {
			str = encrypt2(data,rgbKey,rgbIV);

		} catch (Exception e) {
			str=null;

		}		

		return str;

	}

	public static String encrypt(String data,byte[] rgbKey, byte[] rgbIV) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(rgbKey);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(rgbIV);
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
		byte[] datadd=data.getBytes("ASCII");

		byte[] bytes = cipher.doFinal(datadd);//data.getBytes()	


		return new String(bytes,"ASCII");

		
	}

	
	public static String encrypt2(String data,byte[] rgbKey, byte[] rgbIV) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");		
		SecretKey secretKey = new SecretKeySpec(rgbKey, "DES");
		IvParameterSpec iv = new IvParameterSpec(rgbIV);
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
		byte[] datadd=data.getBytes("ASCII");

		byte[] bytes = cipher.doFinal(datadd);//data.getBytes()	


		//return new String(bytes,"ASCII");

		return Base64.encodeBase64String(bytes);
	}
	
	public static byte[] convertHexString(String ss)    
	{    
		byte digest[] = new byte[ss.length() / 2];    
		for(int i = 0; i < digest.length; i++)    
		{    
			String byteString = ss.substring(2 * i, 2 * i + 2);    
			int byteValue = Integer.parseInt(byteString, 16);    
			digest[i] = (byte)byteValue;    
		}    
		return digest;    
	}   

	public static String toHexString(byte b[]) {   
		StringBuffer hexString = new StringBuffer();   
		for (int i = 0; i < b.length; i++) {   
			String plainText = Integer.toHexString(0xff & b[i]);   
			if (plainText.length() < 2)   
				plainText = "0" + plainText;   
			hexString.append(plainText);   
		}      
		return hexString.toString();   
	}  

}
