package greenwatch.client.service;

import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetPollutionsResponse;

import org.restlet.resource.ClientResource;

import android.os.AsyncTask;

public class GetPollutionService extends AsyncTask<GetPollutionsRequest, Void, GetPollutionsResponse> {

	public static final boolean USE_MOCKS = true;

	@Override
	protected GetPollutionsResponse doInBackground(GetPollutionsRequest... params) {

		if (USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return InMemoryStorage.getInstance().getPollutions(params[0]);
		} else {
			ClientResource cr = new ClientResource("http://10.0.2.2:8888/rest/pollutions");
			PollutionResource resource = cr.wrap(PollutionResource.class);
			return resource.getPollutions(params[0]);
		}
	}

}
