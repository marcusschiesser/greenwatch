package greenwatch.client;



import greenwatch.client.service.GetPollutionService;
import greenwatch.common.vo.PollutionVO;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

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
	
	public void getPolutions(long lat, long lng) {
		GetPollutionService service = new GetPollutionService() {
    		@Override
    		protected void onPreExecute() {
    			mProgressDialog.show();
    		}
			@Override
			protected void onPostExecute(List<PollutionVO> result) {
				mProgressDialog.dismiss();
				
				// TODO do something with the pollution!!!
			}
    	};
    	
		service.execute(lat, lng);		
	}
    
    
}