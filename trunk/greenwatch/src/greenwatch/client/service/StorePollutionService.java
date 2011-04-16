package greenwatch.client.service;

import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.AsyncTask;

public class StorePollutionService extends AsyncTask<PollutionVO, Void, Void> {
	@Override
	protected Void doInBackground(PollutionVO... pollutions) {
		if (GetPollutionService.USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			InMemoryStorage.getInstance().addPollution(pollutions[0]);
		} else {
			// TODO implement real service call
		}
		return null;
	}
}
