package com.klaus.security;

import java.security.MessageDigest;


public class SecurityCoder {
	
	public static final String KEY_SHA = "SHA";

	/**
	 * SHAº”√‹
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptSHA(String coderDate) {
		
		if(coderDate==null){
			return null;
		}
		
		try{
			
			byte[] data=coderDate.getBytes();

			MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);

			return sha.digest().toString();
			
//			return new String(sha.digest(),"UTF-8");
			
		}catch(Exception e){}
		
		return null;

	}


}
