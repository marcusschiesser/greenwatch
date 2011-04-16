package greenwatch.common.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import greenwatch.common.vo.PollutionVO;

public interface PollutionResource {
	@Get
	PollutionVO[] getPollutions(double lat, double lng);

	@Put
	void storePollution(PollutionVO pollution);
	
	@Post
	void updatePollution(PollutionVO pollution);
}
