package greenwatch.client.service;

import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.AsyncTask;

public class GetPollutionService extends AsyncTask<Long, Void, List<PollutionVO>> {

	public static final boolean USE_MOCKS = true;

	@Override
	protected List<PollutionVO> doInBackground(Long... params) {
		if (USE_MOCKS) { // TODO: use Resources instead
			try {
				// sleep to simulate async call
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return InMemoryStorage.getInstance().getPollutions(params[0], params[1]);
		} else {
			// TODO implement real service call
			return null;
		}
	}

}
