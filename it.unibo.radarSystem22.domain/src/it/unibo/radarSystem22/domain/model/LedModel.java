package it.unibo.radarSystem22.domain.model;

import it.unibo.radarSystem22.domain.concrete.LedConcrete;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;
import it.unibo.radarSystem22.domain.mock.LedMockWithGUI;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class LedModel implements ILed {

	private boolean state = false;

	public static ILed create() {
		ILed led;
		if (DomainSystemConfig.simulation)
			led = createLedMock();
		else
			led = createLedConcrete();
		return led;
	}

	public static ILed createLedMock() {
		ColorsOut.out("DeviceFactory | createLedMock ledGui=" + DomainSystemConfig.ledGui, ColorsOut.GREEN);
		if (DomainSystemConfig.ledGui)
			return new LedMockWithGUI();
		else
			return new LedMock();

	}

	public static ILed createLedConcrete() {
		ColorsOut.out("createLedConcrete", ColorsOut.BLUE);
		return new LedConcrete();
	}

	protected abstract void ledActivate(boolean value);

	protected void setState(boolean value) {
		state = value;
		ledActivate(value);
	}

	@Override
	public void turnOn() {
		setState(true);

	}

	@Override
	public void turnOff() {
		setState(false);
	}

	@Override
	public boolean getState() {
		return state;
	}

}
