package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.model.Distance;
import it.unibo.radarSystem22.domain.model.SonarModel;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarMock extends SonarModel implements ISonar {

	// Un Mock-sonar che produce valori di distanza da 90 a 0

	private int delta = 1;

	@Override
	protected void sonarSetUp() {
		currentDistance = new Distance(90);
	}

	@Override
	protected void sonarProduce() {
		// fase testing, il sonar produce UN UNICO dato NOTO
		if (DomainSystemConfig.testing) {
			updateDistance(DomainSystemConfig.testingDistance);
			stopped = true;
		} else {
			// fase simulazione, il sonar produce valori da 90 a 0, poi si stoppa

			updateDistance(currentDistance.getVal() - delta);
			if (getDistance().getVal() <= 0)
				stopped = true;

			BasicUtils.delay(DomainSystemConfig.sonarDelay); // tempo di attesa tra due produzioni consecutive
		}

	}

}
