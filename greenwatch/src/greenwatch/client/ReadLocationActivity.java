package greenwatch.client;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ReadLocationActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readlocation);

		// get the button that triggers the operation
		Button readLocationButton = (Button) findViewById(R.id.readLocationButton);

		// add click listener
		readLocationButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// get the location manager
				LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				// read the location
				Location currentLocation = CurrentLocation.readCurrentLocation(locationManager);
				
				// get the label 
				TextView label = (TextView) findViewById(R.id.currentLocationTextView);
				
				// prepare the location text information
				String txt = "Your current location is:";
				txt += " [LNG: " + currentLocation.getLongitude() + "]";
				txt += " [LAT: " + currentLocation.getLatitude() + "]";
				
				// update the label
				label.setText(txt);
			}
		});
	}
}
