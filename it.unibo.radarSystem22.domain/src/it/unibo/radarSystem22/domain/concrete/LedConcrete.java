package it.unibo.radarSystem22.domain.concrete;

import java.io.IOException;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.model.LedModel;

public class LedConcrete extends LedModel implements ILed {

	private Runtime r = Runtime.getRuntime();

	@Override
	protected void ledActivate(boolean value) {
		// TODO Auto-generated method stub

		try {
			if (getState())
				r.exec("sudo bash led25Gpio6On.sh");

			else
				r.exec("sudo bash led25Gpio6Off.sh");
		} catch (IOException e) {
			System.err.println("LedConcrete | ERROR " + e.getMessage());
		}
	}

}
