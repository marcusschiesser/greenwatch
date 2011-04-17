package greenwatch.client.service;

import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.vo.PollutionTO;

import java.util.Arrays;
import java.util.List;

import org.restlet.resource.ClientResource;

import android.os.AsyncTask;

public class GetPollutionService extends AsyncTask<Double, Void, List<PollutionTO>> {

	public static final boolean USE_MOCKS = true;

	@Override
	protected List<PollutionTO> doInBackground(Double... params) {
		GetPollutionsRequest req = new GetPollutionsRequest();
		req.setMinLat((int) (params[0] * 1e6));
		req.setMinLng((int) (params[1] * 1e6));
		req.setMaxLat((int) (params[2] * 1e6));
		req.setMaxLng((int) (params[3] * 1e6));

		if (USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return InMemoryStorage.getInstance().getPollutions(req);
		} else {
			ClientResource cr = new ClientResource("http://10.0.2.2:8888/rest/pollutions");
			PollutionResource resource = cr.wrap(PollutionResource.class);
			GetPollutionsResponse resp = resource.getPollutions(req);
			return Arrays.asList(resp.getPollutions());
		}
	}

}
