package it.unibo.radarSystem22.domain.interfaces;

public interface ISonarObservable {
	
	public void subscribe(ISonarObserver observer);
		
	public void unsubscribe(ISonarObserver observer);
	
	public void updateAll(IDistance nextValue);

}
