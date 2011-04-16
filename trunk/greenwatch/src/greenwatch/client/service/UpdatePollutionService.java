package greenwatch.client.service;

import greenwatch.common.vo.PollutionVO;
import android.os.AsyncTask;

public class UpdatePollutionService extends AsyncTask<PollutionVO, Void, Void> {
	@Override
	protected Void doInBackground(PollutionVO... pollutions) {
		if (GetPollutionService.USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			InMemoryStorage.getInstance().updatePollution(pollutions[0]);
		} else {
			// TODO implement real service call
		}
		return null;
	}

	private void storePollution(PollutionVO pollution) {
		InMemoryStorage.getInstance().addPollution(pollution);
	}
}
