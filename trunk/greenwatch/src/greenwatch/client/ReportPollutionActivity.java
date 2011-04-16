package greenwatch.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ReportPollutionActivity  extends Activity {
	
	private static final int POLLUTION_CAMERA_CAPTURE_REQUEST = 20100416; 
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
       
        Intent pollutionCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pollutionCameraIntent, POLLUTION_CAMERA_CAPTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == POLLUTION_CAMERA_CAPTURE_REQUEST) {
    		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
    		ImageView image = (ImageView) findViewById(R.id.pollutionCaptureView); 
    		image.setImageBitmap(thumbnail); 
    	}
    }

}
