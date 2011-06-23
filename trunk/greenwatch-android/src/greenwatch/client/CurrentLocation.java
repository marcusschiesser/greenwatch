package greenwatch.client;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

/**
 * Utility class for all location-based services requeired by the application.
 */
public class CurrentLocation {

	/**
	 * Reads the current location of the device using one of the providers (gps,
	 * network, ip).
	 * 
	 * LocationManager can be obtained by calling something similar to this:
	 * <code>locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);</code>
	 * in the Activity.
	 * 
	 * @param locationManager
	 *            Location manager
	 * @return Current location of the device
	 */
	public static Location readCurrentLocation(LocationManager locationManager) {

		String provider = getBestLocationProvider(locationManager);

		// read the current location using the provider
		Location location = locationManager.getLastKnownLocation(provider);

		return location;
	}

	public static String getBestLocationProvider(
			LocationManager locationManager) {
		// setup the criteria for finding location using best provider (gps,
		// ip-based, cell-network based)
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		// search for best provider based on the criteria
		String provider = locationManager.getBestProvider(criteria, true);
		return provider;
	}
}
