package com.whh.patterns.observer;

public class Observer1 implements Observer{
	private Subject subject;

	public Observer1(Subject subject) {
		this.subject = subject;
		subject.registerObserver(this);
	}


	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		System.out.println("observer1 ==="+msg);
	}
	

}
