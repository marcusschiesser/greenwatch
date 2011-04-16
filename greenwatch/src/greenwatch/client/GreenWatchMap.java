package greenwatch.client;



import greenwatch.client.service.GetPollutionService;
import greenwatch.common.vo.PollutionTO;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GreenWatchMap extends MapActivity implements LocationListener {
    private ProgressDialog mProgressDialog;
    private Drawable mMapIcon;
    private List<Overlay> mOverlays;
	private LocationManager locationManager;
	private MapView mapView;
    
	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(getResources().getString(R.string.edit_waiting));
		mProgressDialog.setCancelable(false);
		mProgressDialog.setIndeterminate(true);

		mMapIcon = this.getResources().getDrawable(R.drawable.atom);

		mOverlays = mapView.getOverlays();
		retrieveLocation();
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
	public void onLocationChanged(Location location) {
		updateLocation(location);
		locationManager.removeUpdates(this);
	}
	public void onProviderEnabled(String s){
	}
	public void onProviderDisabled(String s){
	}
	public void onStatusChanged(String s, int i, Bundle b){
	}

	public void retrieveLocation() {
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		String locationProvider = CurrentLocation.getBestLocationProvider(locationManager);
		updateLocation(locationManager.getLastKnownLocation(locationProvider));
		this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
    
	private void updateLocation(Location location) {
		if(location!=null) {
			getPolutions(location.getLatitude(), location.getLongitude());
			mapView.getController().setCenter(new GeoPoint((int)Math.round(location.getLatitude()*1E6), (int)Math.round(location.getLongitude()*1E6)));
		}
	}

	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.main_new_pollution:
			final Intent intentRep = new Intent(this,
					ReportPollutionActivity.class);
			startActivity(intentRep);
		}
	}
	
	public void getPolutions(double lat, double lng) {
		GetPollutionService service = new GetPollutionService() {
    		@Override
    		protected void onPreExecute() {
    			mProgressDialog.show();
    		}
			@Override
			protected void onPostExecute(List<PollutionTO> result) {
				mProgressDialog.dismiss();
				mOverlays.clear();
				
				GreenWatchItemizedOverlay itemizedoverlay = new GreenWatchItemizedOverlay(mMapIcon);
				
				for (PollutionTO pollutionVO : result) {
					GeoPoint point = new GeoPoint((int)Math.round(pollutionVO.getLatitude()*1E6), (int)Math.round(pollutionVO.getLongitude()*1E6));
					OverlayItem overlayitem = new OverlayItem(point, pollutionVO.getComment(), pollutionVO.getComment());
					itemizedoverlay.addOverlay(overlayitem);
				}
				mOverlays.add(itemizedoverlay);
			}
    	};
    	
		service.execute(lat, lng);		
	}
    
    
}