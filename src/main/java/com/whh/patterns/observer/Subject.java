package com.whh.patterns.observer;

/**
 * 
 * @desc:
 */
public interface Subject {
	public void registerObserver(Observer observer) ;
	public void removeObserver(Observer observer) ;
	public void notifyObserver();
}
