package greenwatch.common.resource;

import greenwatch.common.vo.PollutionVO;

import java.util.List;

public interface PollutionResource {
	List<PollutionVO> getPollutions(double lat, double lng);
	void storePollution(PollutionVO pollution);
	void updatePollution(PollutionVO pollution);
}
