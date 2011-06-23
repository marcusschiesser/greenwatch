package com.greenwatch.shared.request;

import com.greenwatch.shared.vo.PollutionVO;

public class UpdatePollutionRequest extends AbstractRequest {

	private static final long serialVersionUID = -2139312978755053026L;
	private int id;
	private PollutionVO pollution;

	public UpdatePollutionRequest() {
		super();
	}

	public UpdatePollutionRequest(int id, PollutionVO pollution) {
		this();
		this.id = id;
		this.pollution = pollution;
	}

	public int getId() {
		return id;
	}

	public PollutionVO getPollution() {
		return pollution;
	}
}
