package greenwatch.client;



import greenwatch.client.service.GetPollutionService;
import greenwatch.common.vo.PollutionVO;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GreenWatchMap extends Activity {
    private ProgressDialog mProgressDialog;

	/** Called when the activity is first created. */
	// Dieser Kommentar ist sinnfrei
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(getResources().getString(R.string.edit_waiting));
		mProgressDialog.setCancelable(false);
		mProgressDialog.setIndeterminate(true);
        
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