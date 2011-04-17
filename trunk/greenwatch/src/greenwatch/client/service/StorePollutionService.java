package greenwatch.client.service;

import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.response.StorePollutionResponse;
import android.os.AsyncTask;

public class StorePollutionService extends AsyncTask<StorePollutionRequest, Void, StorePollutionResponse> {
	@Override
	protected StorePollutionResponse doInBackground(StorePollutionRequest... pollutions) {
		if (GetPollutionService.USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			InMemoryStorage.getInstance().addPollution(pollutions[0].getPollution());
		} else {
			// TODO implement real service call
		}
		return null;
	}
}
