package com.whh.patterns.Singleton;

/**
 * 
 * @desc:单例
 * 
 */
public class Singleton4 {
	private static Singleton4 INSTANCE1 = null;

	static Singleton4 getInstance() {
		if (INSTANCE1 == null) {
			synchronized (Singleton4.class) {
				if (INSTANCE1 == null) {
					INSTANCE1 = new Singleton4();
				}
			}
		}
		return INSTANCE1;
	}

}
