package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.model.LedModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class LedMock extends LedModel implements ILed {

	@Override
	protected void ledActivate(boolean value) {
		showState();
	}

	protected void showState() {
		ColorsOut.outappl("LedMock state=" + getState(), ColorsOut.MAGENTA);
	}

}
