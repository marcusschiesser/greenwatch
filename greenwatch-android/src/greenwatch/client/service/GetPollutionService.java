package greenwatch.client.service;

import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetPollutionsResponse;
import android.os.AsyncTask;

public class GetPollutionService extends AsyncTask<GetPollutionsRequest, Void, GetPollutionsResponse> {

	@Override
	protected GetPollutionsResponse doInBackground(GetPollutionsRequest... params) {
		PollutionResource resource = ResourceFactory.createPollutionResource();
		return resource.getPollutions(params[0]);
	}

}
