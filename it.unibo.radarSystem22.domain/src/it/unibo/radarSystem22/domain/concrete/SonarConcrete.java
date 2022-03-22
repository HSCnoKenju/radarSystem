package it.unibo.radarSystem22.domain.concrete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.model.Distance;
import it.unibo.radarSystem22.domain.model.SonarModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarConcrete extends SonarModel implements ISonar {

	private BufferedReader reader;
	private Process p;

	@Override
	protected void sonarSetUp() {
		currentDistance = new Distance(90);

	}

	@Override
	protected void sonarProduce() {

		/*
		 * l'output di SonarAlone.c mi genera per ogni riga un valore intero Lo astraggo
		 * ad un Reader
		 */

		try {
			String data = reader.readLine();
			if (data == null)
				return;
			// else
			int upcomingValue = Integer.parseInt(data);

			/*
			 * filtro le distanze fuori scala (causati da possibili glitch?), inoltre non ha
			 * senso aggiornare se la distanza è la stessa
			 */

			if (currentDistance.getVal() != upcomingValue && upcomingValue < DomainSystemConfig.sonarDistanceMax) {
				updateDistance(upcomingValue);
			}

		} catch (IOException e) {
			ColorsOut.outerr("SonarConcrete | ERROR " + e.getMessage());
//			System.err.println("SonarConcrete | ERROR " + e.getMessage());
		}

	}

	@Override
	public void activate() {

		/*
		 * l'attivazione prevede quindi l'esecuzione di SonarAlone, e la predisposizione
		 * di un reader da cui il metodo sonarProduce prende i dati
		 * 
		 */

		if (p == null) {
			try {
				p = Runtime.getRuntime().exec("sudo ./SonarAlone"); // potrei mettere i nomi dei file come DomainSystemConfig?
				reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			} catch (IOException e) {
				ColorsOut.outerr("SonarConcrete | ERROR " + e.getMessage());
			}
		}

		super.activate();
	}

	@Override
	public void deactivate() {

		/*
		 * in maniera speculare a activate, termino il processo che esegue SonarAlone
		 */

		if (p != null) {
			p.destroy();
			p = null;
		}
		currentDistance = new Distance(90); // perchè devo ripristinare?
		super.deactivate();
	}

}
