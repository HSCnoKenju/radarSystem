package it.unibo.radarSystem22.domain.model;

import it.unibo.radarSystem22.domain.concrete.SonarConcrete;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.SonarMock;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class SonarModel implements ISonar {

	protected boolean stopped = false;
	protected IDistance currentDistance = new Distance(90);
	
	
	public static ISonar create() {
		if( DomainSystemConfig.simulation )  return createSonarMock();
		else  return createSonarConcrete();		
	}

	public static ISonar createSonarMock() {
		ColorsOut.out("createSonarMock", ColorsOut.BLUE);
		return new SonarMock();
	}	
	public static ISonar createSonarConcrete() {
		ColorsOut.out("createSonarConcrete", ColorsOut.BLUE);
		return new SonarConcrete();
	}	

	protected SonarModel() {
		super();
		sonarSetUp();
	}

	protected abstract void sonarSetUp();

	protected abstract void sonarProduce();
	
	protected void updateDistance(int value) {
		currentDistance = new Distance(value);
	}

	@Override
	public void activate() {
		// TODO lol non ho capito
		stopped = false;
		new Thread() {
			public void run() {
				while (!stopped)
					sonarProduce();
			}

		}.start();
	}

	@Override
	public void deactivate() {
		stopped = true;
	}

	@Override
	public IDistance getDistance() {
		return currentDistance;
	}

	@Override
	public boolean isActive() {
		return !stopped;
	}

}
