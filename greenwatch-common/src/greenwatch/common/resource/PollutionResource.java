package greenwatch.common.resource;

import greenwatch.common.vo.PollutionVO;

public interface PollutionResource {
	PollutionVO[] getPollutions(double lat, double lng);
	void storePollution(PollutionVO pollution);
	void updatePollution(PollutionVO pollution);
}
