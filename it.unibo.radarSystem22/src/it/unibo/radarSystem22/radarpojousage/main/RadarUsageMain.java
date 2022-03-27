package it.unibo.radarSystem22.radarpojousage.main;

import it.unibo.radar.common.radarSupport;

public class RadarUsageMain {

	public void doJob() {
		System.out.println("start");
		radarSupport.setUpRadarGui();
		radarSupport.update( "40", "60");
	}
	public static void main(String[] args) {
            new RadarUsageMain().doJob();
    }

}
