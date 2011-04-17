package greenwatch.common.resource;

import greenwatch.common.request.GetFullPollutionRequest;
import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.request.UpdatePollutionRequest;
import greenwatch.common.response.GetFullPollutionResponse;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.response.StorePollutionResponse;
import greenwatch.common.response.UpdatePollutionResponse;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface PollutionResource {

	@Get
	GetPollutionsResponse getPollutions(GetPollutionsRequest request);

	@Get
	GetFullPollutionResponse getFullPollution(GetFullPollutionRequest request);

	@Put
	StorePollutionResponse storePollution(StorePollutionRequest request);

	@Post
	UpdatePollutionResponse updatePollution(UpdatePollutionRequest request);
}
