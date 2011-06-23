package greenwatch.client.service;

import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.StorePollutionResponse;
import android.os.AsyncTask;

public class StorePollutionService extends AsyncTask<StorePollutionRequest, Void, StorePollutionResponse> {
	@Override
	protected StorePollutionResponse doInBackground(StorePollutionRequest... pollutions) {
		PollutionResource resource = ResourceFactory.createPollutionResource();
		resource.storePollution(pollutions[0]);
		return new StorePollutionResponse();
	}
}
