package greenwatch.client.service;

import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InMemoryStorage {

	private static InMemoryStorage INSTANCE;

	private Map<Integer, PollutionVO> pollutions;
	int idx = 0;

	protected InMemoryStorage() {
		pollutions = new HashMap<Integer, PollutionVO>();
	}

	public static InMemoryStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InMemoryStorage();
		}
		return INSTANCE;
	}

	public void addPollution(PollutionVO pollution) {
		pollution.setId(idx++);
		pollutions.put(pollution.getId(), pollution);
	}

	public List<PollutionVO> getPollutions(double lat, double lng) {
		List<PollutionVO> tmp = new ArrayList<PollutionVO>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(lat + rand.nextDouble());
			pollution.setLongitude(lng + rand.nextDouble());
			pollution.setStatus(PollutionVO.Status.active);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(lat + rand.nextDouble());
			pollution.setLongitude(lng + rand.nextDouble());
			pollution.setStatus(PollutionVO.Status.inactive);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (PollutionVO poll : pollutions.values()) {
			// add on first position
			tmp.add(0, poll);
		}
		return tmp;
	}

	public void updatePollution(PollutionVO pollutionVO) {
		pollutions.put(pollutionVO.getId(), pollutionVO);
	}
}
