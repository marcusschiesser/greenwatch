package greenwatch.common.resource;

import greenwatch.common.vo.PollutionTO;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface PollutionResource {
	@Get
	PollutionTO[] getPollutions(double lat, double lng);

	@Put
	void storePollution(PollutionTO pollution);

	@Post
	void updatePollution(PollutionTO pollution);
}
