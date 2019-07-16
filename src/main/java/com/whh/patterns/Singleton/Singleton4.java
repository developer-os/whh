package com.whh.patterns.Singleton;

/**
 * 
 * @desc:单例
 *  非 lazy init
 *  这里final不是必须的.

static为我们提供了保证(正确的被创建, 创建的对象是完整的) 可以参考:JSR 133 (Java Memory Model) FAQ
 */
public class Singleton4 {
	private static final Singleton4 INSTANCE1 = new Singleton4();
	static Singleton4 getInstance() {
		return INSTANCE1;
	}
	
}
