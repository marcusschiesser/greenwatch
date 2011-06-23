package greenwatch.client.utils;


import com.google.android.maps.GeoPoint;

public class GeoUtils {

	public static GeoPoint createGeoPoint(double lat, double lng) {
		return new GeoPoint(GeoUtils.convertGeoDbl2Int(lat), GeoUtils.convertGeoDbl2Int(lng));
	}

	public static int convertGeoDbl2Int(double dbl) {
		return (int)(dbl*1E6);
	}

	public static double convertGeoInt2Dbl(int i) {
		return new Integer(i).doubleValue() / 1E6;
	}

}
