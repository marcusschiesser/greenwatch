package com.greenwatch.shared.request;

public class GetFullPollutionRequest extends AbstractRequest {

	private static final long serialVersionUID = 4317484508914783787L;
	private int id;

	public GetFullPollutionRequest() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
