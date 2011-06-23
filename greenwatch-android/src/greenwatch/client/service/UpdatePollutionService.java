package greenwatch.client.service;

import greenwatch.common.request.UpdatePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.UpdatePollutionResponse;
import android.os.AsyncTask;

public class UpdatePollutionService extends AsyncTask<UpdatePollutionRequest, Void, UpdatePollutionResponse> {
	@Override
	protected UpdatePollutionResponse doInBackground(UpdatePollutionRequest... pollutions) {
		PollutionResource resource = ResourceFactory.createPollutionResource();
		return resource.updatePollution(pollutions[0]);
	}

}
