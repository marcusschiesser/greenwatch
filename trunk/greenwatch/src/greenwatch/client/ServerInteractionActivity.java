package greenwatch.client;

import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionIntensity;
import greenwatch.common.vo.PollutionVO;

import org.restlet.resource.ClientResource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServerInteractionActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Starting Server Interaction Activity.");
		setContentView(R.layout.serverinteraction);
		Button storePollution = (Button) findViewById(R.id.storePollution);
		storePollution.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				System.out.println("Got a click on the store pollution button.");

				// create a sample pollution report
				PollutionVO poll = new PollutionVO();
				poll.setLongitude(12d);
				poll.setLatitude(123d);
				poll.setIntensity(PollutionIntensity.high);

				// StorePollutionService service = new StorePollutionService();
				// service.execute(new PollutionVO[] { poll });
				// System.setProperty("java.net.preferIPv6Addresses", "false");
				ClientResource cr = new ClientResource("http://zagzix.appspot.com:80/rest/pollutions");
				PollutionResource resource = cr.wrap(PollutionResource.class);
				StorePollutionRequest req = new StorePollutionRequest(poll, null);
				Object resp = resource.storePollution(req);
				System.out.println("Got a response." + resp);
			}
		});
	}
}