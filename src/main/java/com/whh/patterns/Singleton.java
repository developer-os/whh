package com.whh.patterns;

/**
 * 
 * @desc:单例
 *  多线程环境下有问题
 */
public class Singleton {
	private static Singleton INSTANCE1;
	static Singleton getInstance() {
		if(INSTANCE1==null) {
			INSTANCE1=new Singleton();
		}
		return INSTANCE1;
	}
	
}
