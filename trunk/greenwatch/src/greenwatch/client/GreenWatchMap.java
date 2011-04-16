package greenwatch.client;



import greenwatch.client.service.GetPollutionService;
import greenwatch.common.vo.PollutionTO;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GreenWatchMap extends MapActivity {
    private ProgressDialog mProgressDialog;

	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(getResources().getString(R.string.edit_waiting));
		mProgressDialog.setCancelable(false);
		mProgressDialog.setIndeterminate(true);
		
		// draw POI on the map
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.atom);
		GreenWatchItemizedOverlay itemizedoverlay = new GreenWatchItemizedOverlay(drawable);
		
		// Test point
		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
        
		GeoPoint point2 = new GeoPoint(35410000, 139460000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
		
		itemizedoverlay.addOverlay(overlayitem);
		itemizedoverlay.addOverlay(overlayitem2);
		mapOverlays.add(itemizedoverlay);
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.main_new_pollution:
			final Intent intentRep = new Intent(this,
					ReportPollutionActivity.class);
			startActivity(intentRep);
		}
	}
	
	public void getPolutions(Double lat, Double lng) {
		GetPollutionService service = new GetPollutionService() {
    		@Override
    		protected void onPreExecute() {
    			mProgressDialog.show();
    		}
			@Override
			protected void onPostExecute(List<PollutionTO> result) {
				mProgressDialog.dismiss();
				
				// TODO do something with the pollution!!!
			}
    	};
    	
		service.execute(lat, lng);		
	}
    
    
}