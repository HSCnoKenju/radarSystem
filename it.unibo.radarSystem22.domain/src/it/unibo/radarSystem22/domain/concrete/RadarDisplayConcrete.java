package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radar.common.radarSupport;

public class RadarDisplayConcrete implements IRadarDisplay {
	private String curDistance = "0";
	private static RadarDisplayConcrete display = null; // singleton
	// Factory method

	public static RadarDisplayConcrete getRadarDisplay() {
		if (display == null) {
			display = new RadarDisplayConcrete();
		}
		return display;
	}

	protected RadarDisplayConcrete() {
		radarSupport.setUpRadarGui();
	}

	@Override
	public void update(String distance, String angle) {
		// Colors.out("RadarDisplay | update distance="+distance);
		curDistance = distance;
		radarSupport.update(distance, angle);
	}

	@Override
	public int getCurrentDistance() {
		ColorsOut.out("RadarDisplay | getCurDistance=" + curDistance);
		return Integer.parseInt(curDistance);
	}

}
