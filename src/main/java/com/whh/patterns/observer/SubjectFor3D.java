package com.whh.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectFor3D implements Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	    notifyObserver();
	}

	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		int index = observers.indexOf(observer);
		if(index>0) {
			observers.remove(index);
		}
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(Observer ob:observers) {
			ob.update(msg);
		}
	}
	
	

}
