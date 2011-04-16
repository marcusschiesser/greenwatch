package greenwatch.common.resource;

import greenwatch.common.vo.PollutionVO;

import java.util.List;

public interface PollutionResource {
	List<PollutionVO> getPollutions(long lat, long lng);
	void storePollution(PollutionVO pollution);
	void updatePollution(PollutionVO pollution);
}
