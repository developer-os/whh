package com.whh.patterns;

/**
 * 
 * @desc:单例
 * 双重验证
 */
public class Singleton3 {
	private static  Singleton3 INSTANCE1;
	static Singleton3 getInstance() {
		if(INSTANCE1==null) {
			synchronized (Singleton3.class) {
				if(INSTANCE1==null) {
					INSTANCE1=new Singleton3();
				}
			}
		}
		return INSTANCE1;
	}
	
}
