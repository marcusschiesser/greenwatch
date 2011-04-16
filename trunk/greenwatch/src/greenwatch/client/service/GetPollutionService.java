package greenwatch.client.service;

import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.AsyncTask;

public class GetPollutionService extends AsyncTask<Long, Void, List<PollutionVO>>{
	@Override
	protected List<PollutionVO> doInBackground(Long... params) {
		if(true) { // TODO: use Resources instead
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getPollutions(params[0], params[1]);
		} else {
			// TODO implement real service call
			return null;
		}
	}
	 
	private List<PollutionVO> getPollutions(Long lat, Long lng) {
		List<PollutionVO> pollutions = new ArrayList<PollutionVO>();
		Random rand = new Random();
			for (int i = 0; i < 10; i++) {
				PollutionVO pollution = new PollutionVO();
				pollution.setLat(lat+rand.nextInt(1000));
				pollution.setLng(lng+rand.nextInt(1000));
				pollution.setStatus(PollutionVO.Status.active);
				pollutions.add(pollution);				
			}
			for (int i = 0; i < 10; i++) {
				PollutionVO pollution = new PollutionVO();
				pollution.setLat(lat+rand.nextInt(1000));
				pollution.setLng(lng+rand.nextInt(1000));
				pollution.setStatus(PollutionVO.Status.inactive);
				pollutions.add(pollution);				
			}
			return pollutions;
	}
}
