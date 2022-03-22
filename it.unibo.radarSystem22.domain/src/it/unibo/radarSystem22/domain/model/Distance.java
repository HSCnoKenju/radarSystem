package it.unibo.radarSystem22.domain.model;

import it.unibo.radarSystem22.domain.interfaces.IDistance;

public class Distance implements IDistance {

	private int value;

	public Distance(int value) {
		super();
		this.value = value;
	}

	@Override
	public int getVal() {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}

}
