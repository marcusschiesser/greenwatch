package greenwatch.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ReportPollutionActivity extends Activity {

	private static final int POLLUTION_CAMERA_CAPTURE_REQUEST = 20100416;

	public static final String LEVEL = "level";
	public static final String COMMENT = "comment";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		Intent pollutionCameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(pollutionCameraIntent,
				POLLUTION_CAMERA_CAPTURE_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == POLLUTION_CAMERA_CAPTURE_REQUEST) {
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
			ImageView image = (ImageView) findViewById(R.id.pollutionCaptureView);
			image.setImageBitmap(thumbnail);
		}
	}

	// myClickHandler_b_report
	public void myClickHandler_b_report(View view) {
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

}
