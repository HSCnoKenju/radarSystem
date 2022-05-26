package it.unibo.radarSystem22.domain.observable;

import it.unibo.radarSystem22.domain.interfaces.ISonarObserver;

public class SonarObserver implements ISonarObserver {

	private SonarObservable source;
	
	private int currentValue;

	protected SonarObserver(SonarObservable observable) {
		super();
		this.source = observable;
		observable.subscribe(this);
	}

	@Override
	public void update(int value) {
		currentValue = value;

	}

	@Override
	public void unsubscribe() {
		source.unsubscribe(this);
		source = null;
	}

	public int getCurrentValue() {
		return currentValue;
	}

}
