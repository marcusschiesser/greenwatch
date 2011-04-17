package greenwatch.common.response;

import greenwatch.common.vo.PollutionTO;

public class GetPollutionsResponse extends AbstractResponse {

	public GetPollutionsResponse() {
		super();
		this.pollutions = new PollutionTO[0];
	}

	public GetPollutionsResponse(PollutionTO[] pollutions) {
		this();
		this.pollutions = pollutions;
	}

	private PollutionTO[] pollutions;

	public PollutionTO[] getPollutions() {
		return pollutions;
	}

	public void setPollutions(PollutionTO[] pollutions) {
		this.pollutions = pollutions;
	}
}
