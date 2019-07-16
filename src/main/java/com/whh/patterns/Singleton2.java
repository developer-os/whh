package com.whh.patterns;

/**
 * 
 * @desc:单例
 * 性能较差
 */
public class Singleton2 {
	private static Singleton2 INSTANCE1;
	static synchronized Singleton2 getInstance() {
		if(INSTANCE1==null) {
			INSTANCE1=new Singleton2();
		}
		return INSTANCE1;
	}
	
}
