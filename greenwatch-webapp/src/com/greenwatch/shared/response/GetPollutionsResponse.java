package com.greenwatch.shared.response;

import com.greenwatch.shared.vo.PollutionTO;

public class GetPollutionsResponse extends AbstractResponse {

	private static final long serialVersionUID = -2270398065661564292L;

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
