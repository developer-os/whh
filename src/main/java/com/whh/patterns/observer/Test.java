package com.whh.patterns.observer;

public class Test {
	public static void main(String[] args) {
		System.out.println("--------------");
		
		SubjectFor3D objectFor3D = new SubjectFor3D();
		Observer1 observer1 = new Observer1(objectFor3D);
		Observer2 observer2 = new Observer2(objectFor3D);
		objectFor3D.setMsg("1111");
		objectFor3D.setMsg("2222");
		System.out.println("---------------");
		
	}
}
