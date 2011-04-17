package greenwatch.client.service;

import greenwatch.common.vo.PollutionStatus;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<PollutionTO> getPollutions(double minLat, double minLng, double maxLat, double maxLng) {
		ArrayList<PollutionTO> tmp = new ArrayList<PollutionTO>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(minLat + rand.nextDouble() * (maxLat-minLat));
			pollution.setLongitude(minLng + rand.nextDouble() * (maxLng-minLng));
			pollution.setStatus(PollutionStatus.active);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (int i = 0; i < 10; i++) {
			PollutionVO pollution = new PollutionVO();
			pollution.setLatitude(minLat + rand.nextDouble() * (maxLat-minLat));
			pollution.setLongitude(minLng + rand.nextDouble() * (maxLng-minLng));
			pollution.setStatus(PollutionStatus.inactive);
			pollution.setId(idx++);
			tmp.add(pollution);
		}
		for (PollutionTO poll : pollutions.values()) {
			// add on first position
			tmp.add(0, poll);
		}
		return tmp;
	}

	public void updatePollution(PollutionTO pollution) {
		pollutions.put(pollution.getId(), pollution);
	}
}
