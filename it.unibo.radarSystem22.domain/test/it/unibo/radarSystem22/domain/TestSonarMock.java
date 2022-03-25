package it.unibo.radarSystem22.domain;

import org.junit.Test;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class TestSonarMock {

	@Test
	public void testSonarMock() {
	  DomainSystemConfig.simulation = true;
	  DomainSystemConfig.sonarDelay = 10; //quite fast generation...
	  int delta = 1;

	  ISonar sonar = DeviceFactory.createSonar();
	  new SonarConsumerForTesting( sonar, delta ).start();
	  sonar.activate();
	  while( sonar.isActive() ) {BasicUtils.delay(100);}  //avoid premature exit
	}

}
