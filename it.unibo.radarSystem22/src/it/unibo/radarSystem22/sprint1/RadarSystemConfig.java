package it.unibo.radarSystem22.sprint1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class RadarSystemConfig {
	public static boolean tracing = false;
	public static boolean testing = false;
	public static int DLIMIT = 70;
	public static boolean RadarGuiRemote = false;
	public static int serverPort;
	public static String hostAddr;

	public static void setTheConfiguration() {
		setTheConfiguration("../RadarSystemConfig.json");
	}

	public static void setTheConfiguration(String resourceName) {
		// Nella distribuzione resourceName � in una dir che include la bin
		FileInputStream fis = null;
		try {
			ColorsOut.out("%%% setTheConfiguration from file:" + resourceName);
			if (fis == null) {
				fis = new FileInputStream(new File(resourceName));
			}
			// JSONTokener richiede un Reader, quindi faccio il wrapper
			InputStreamReader isr = new InputStreamReader(fis);

			JSONTokener tokener = new JSONTokener(isr);
			JSONObject object;

			object = new JSONObject(tokener);

			tracing = object.getBoolean("tracing");
			testing = object.getBoolean("testing");
			RadarGuiRemote = object.getBoolean("RadarGuiRemote");
			DLIMIT = object.getInt("DLIMIT");
			serverPort = object.getInt("serverPort");
			hostAddr = object.getString("hostAddr");

		} catch (FileNotFoundException | JSONException e) {
			ColorsOut.outerr("setTheConfiguration ERROR " + e.getMessage());
		}

	}

}
