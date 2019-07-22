package com.whh.codec;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonCodec {
	
	public static String name(String data) {
		return DigestUtils.md5Hex(data);
	}

	public static void main(String[] args) {
		System.out.println(name("sfdsf"));
		System.out.println(name("sfdsf"));
		
	}
}
