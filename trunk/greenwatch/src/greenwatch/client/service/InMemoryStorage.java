package greenwatch.client.service;

import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.vo.PollutionStatus;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InMemoryStorage {

	private static InMemoryStorage INSTANCE;

	private Map<Integer, PollutionTO> pollutions;
	int idx = 0;

	protected InMemoryStorage() {
		pollutions = new HashMap<Integer, PollutionTO>();
	}

	public static InMemoryStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InMemoryStorage();
		}
		return INSTANCE;
	}

	public void addPollution(PollutionTO pollution) {
		pollution.setId(idx++);
		pollutions.put(pollution.getId(), pollution);
	}

	public GetPollutionsResponse getPollutions(GetPollutionsRequest req) {
		ArrayList<PollutionTO> tmp = new ArrayList<PollutionTO>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(req.getMinLat() + rand.nextDouble() * (req.getMaxLat() - req.getMinLat()));
			pollution.setLongitude(req.getMinLng() + rand.nextDouble() * (req.getMaxLng() - req.getMinLng()));
			pollution.setStatus(PollutionStatus.active);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(req.getMinLat() + rand.nextDouble() * (req.getMaxLat() - req.getMinLat()));
			pollution.setLongitude(req.getMinLng() + rand.nextDouble() * (req.getMaxLng() - req.getMinLng()));
			pollution.setStatus(PollutionStatus.inactive);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (PollutionTO poll : pollutions.values()) {
			// add on first position
			tmp.add(0, poll);
		}
		return new GetPollutionsResponse(tmp.toArray(new PollutionTO[tmp.size()]));
	}

	public void updatePollution(PollutionTO pollution) {
		pollutions.put(pollution.getId(), pollution);
	}
}
