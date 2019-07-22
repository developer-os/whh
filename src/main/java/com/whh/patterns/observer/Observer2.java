package com.whh.patterns.observer;

public class Observer2 implements Observer{
	private Subject subject;

	public Observer2(Subject subject) {
		this.subject = subject;
		subject.registerObserver(this);
	}


	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		System.out.println("observer2 ==="+msg);
	}
	

}
