package it.unibo.radarSystem22.domain.model;

import it.unibo.radarSystem22.domain.interfaces.ILed;

public abstract class LedModel implements ILed {
	
	private boolean state = false; 

	
	protected abstract void ledActivate (boolean value);
	
	protected void setState (boolean value) {
		state = value ;
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
