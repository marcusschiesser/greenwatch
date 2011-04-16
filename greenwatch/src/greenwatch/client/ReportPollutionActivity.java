package greenwatch.client;

import greenwatch.common.vo.PollutionVO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportPollutionActivity  extends Activity implements LocationListener {
	
	private static final int POLLUTION_CAMERA_CAPTURE_REQUEST = 20100416;
	public static final String LEVEL = "level";
	public static final String COMMENT = "comment";
	
	private PollutionVO mPollutionVO = new PollutionVO();

	private LocationManager locationManager;
	private Location currentLocation = null;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        retrieveLocation();
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner_level);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       
        Intent pollutionCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pollutionCameraIntent, POLLUTION_CAMERA_CAPTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == POLLUTION_CAMERA_CAPTURE_REQUEST) {
    		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
    		if (thumbnail == null) return;
    		mPollutionVO.setTimestamp(new Date());
    		ImageView image = (ImageView) findViewById(R.id.pollutionCaptureView); 
    		image.setImageBitmap(thumbnail);
    	}
    }

	private void updateLocationText(String type, Location currentLocation) {

		mPollutionVO.setLongitude(currentLocation.getLongitude());
		mPollutionVO.setLatitude(currentLocation.getLatitude());
		this.currentLocation = currentLocation; 
		
		// prepare the location text information
		String txt = "current location ("+type+"):";
		txt += " [LNG: " + currentLocation.getLongitude() + "]";
		txt += " [LAT: " + currentLocation.getLatitude() + "] "
		 + getAddresse();

		setLocationText(txt);
	}

	private void setLocationText(String txt) {
		// get the label 
		TextView label = (TextView) findViewById(R.id.currentLocationTextView);
		// update the label
		label.setText(txt);
	}
    
    // myClickHandler_b_report
	public void myClickHandler_b_report(View view) {
		
		if (view.getId() == R.id.b_report) {
			fillVO();
			Toast.makeText(this, mPollutionVO.getTimestamp().toLocaleString(), 2000).show();
			getAddresse();
			Toast.makeText(this, mPollutionVO.getComment(), 2000).show();
		}
		
		switch (view.getId()) {
		case R.id.b_report:
			//TO DO: Create new Activity 
/*			final Intent intent = new Intent(this,
					ReportPollutionActivity.class);
			
			EditText txtComment = (EditText) findViewById(R.id.edit_report);
			Spinner spinner = (Spinner) findViewById(R.id.spinner_level);
			
	        intent.putExtra(LEVEL, spinner.getSelectedItem().toString());
	        intent.putExtra(COMMENT, txtComment.getText().toString());
	        
			startActivity(intent);*/
		}
	}
	
	private void fillVO() {
		// copy all view values to VO
		mPollutionVO.setComment(getView(EditText.class, R.id.edit_report).getText().toString());
	}
	
	private String getAddresse() {
		if (currentLocation != null) {
			List<Address> addresses;
			try {
				addresses = new Geocoder(this).getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
				if (!addresses.isEmpty()) {
					Address address = addresses.get(0);
					String simpleAddress = address.getAddressLine(0) + " " + address.getAddressLine(1) + address.getAddressLine(2);
					Toast.makeText(this, simpleAddress, 2000).show();
					return simpleAddress;
				}
			} catch (IOException e) {
			}
		}
		Toast.makeText(this, "NO LOCATION", 2000).show();
		return "NO LOCATION";
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getView(Class<T> returnClass, int viewId) {
		return (T)findViewById(viewId);
	}
	
	public void onLocationChanged(Location location) {
		updateLocationText("GPS",location);
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
		updateLocationText("cache",locationManager.getLastKnownLocation(locationProvider));
		this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	public void setPollutionVO(PollutionVO pollutionVO) {
		this.mPollutionVO = pollutionVO;
	}

	public PollutionVO getPollutionVO() {
		return mPollutionVO;
	}


}
