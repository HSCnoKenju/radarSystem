package it.unibo.radarSystem22.domain.observable;

import java.util.HashSet;
import java.util.Set;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.interfaces.ISonarObserver;
import it.unibo.radarSystem22.domain.model.Distance;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarObservable implements ISonarObservable {

	// non ho gestito la concorrenza

	Set<ISonarObserver> subscribers;

	IDistance lastSentDistance;

	protected SonarObservable() {
		super();
		subscribers = new HashSet<ISonarObserver>();
		lastSentDistance = new Distance(
				DomainSystemConfig.sonarDistanceMax + DomainSystemConfig.sonarObserverSensibility);
	}

	@Override
	public void subscribe(ISonarObserver observer) {
		subscribers.add(observer);

	}

	@Override
	public void unsubscribe(ISonarObserver observer) {
		subscribers.remove(observer);

	}

	@Override
	public void updateAll(IDistance nexDistance) {

		if (Math.abs(nexDistance.getVal() - lastSentDistance.getVal()) >= DomainSystemConfig.sonarObserverSensibility) {
			
			subscribers.forEach( o -> o.update(nexDistance.getVal()));
		}
	}

}
