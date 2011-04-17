package greenwatch.client.service;

import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionTO;

import java.util.Arrays;
import java.util.List;

import org.restlet.resource.ClientResource;

import android.os.AsyncTask;

public class GetPollutionService extends
		AsyncTask<Double, Void, List<PollutionTO>> {

	public static final boolean USE_MOCKS = true;

	@Override
	protected List<PollutionTO> doInBackground(Double... params) {
		if (USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return InMemoryStorage.getInstance().getPollutions(params[0],
					params[1], params[2], params[3]);
		} else {
			ClientResource cr = new ClientResource(
					"http://10.0.2.2:8888/rest/pollutions");
			PollutionResource resource = cr.wrap(PollutionResource.class);
			return Arrays.asList(resource.getPollutions(params[0], params[1]));
		}
	}

}
