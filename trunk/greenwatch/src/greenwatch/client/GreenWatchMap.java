package greenwatch.client;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GreenWatchMap extends Activity {
    /** Called when the activity is first created. */
	// Dieser Kommentar ist sinnfrei
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.main_new_pollution:
			final Intent intentRep = new Intent(this,
					ReportPollutionActivity.class);
			startActivity(intentRep);
		}
	}
    
    
}