package greenwatch.client.service;

import greenwatch.common.request.GetFullPollutionRequest;
import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.request.UpdatePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetFullPollutionResponse;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.response.UpdatePollutionResponse;
import greenwatch.common.vo.PollutionStatus;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InMemoryStorage implements PollutionResource {

	private static InMemoryStorage INSTANCE;

	private Map<Integer, PollutionTO> pollutions;
	int idx = 0;

	private Random random;

	protected InMemoryStorage() {
		pollutions = new HashMap<Integer, PollutionTO>();
		random = new Random();
	}

	public static InMemoryStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InMemoryStorage();
		}
		return INSTANCE;
	}

	private void delay() {
		try {
			// sleep to simulate async call
			Thread.sleep(random.nextInt(2000) + 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public GetPollutionsResponse getPollutions(GetPollutionsRequest req) {
		delay();
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

	public void storePollution(StorePollutionRequest request) {
		delay();
		request.getPollution().setId(idx++);
		pollutions.put(request.getPollution().getId(), request.getPollution());
		return ;//new StorePollutionResponse();
	}

	public UpdatePollutionResponse updatePollution(UpdatePollutionRequest request) {
		delay();
		pollutions.put(request.getId(), request.getPollution());
		return new UpdatePollutionResponse();
	}

	public GetFullPollutionResponse getFullPollution(GetFullPollutionRequest request) {
		return null;
	}
}
