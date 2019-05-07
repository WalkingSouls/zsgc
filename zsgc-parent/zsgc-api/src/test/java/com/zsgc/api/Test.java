package com.zsgc.api;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {
	public static void main(String args[]){
		System.out.println(DigestUtils.sha1Hex("abc12345"));
	}
}
