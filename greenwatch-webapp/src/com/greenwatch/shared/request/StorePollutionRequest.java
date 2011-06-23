package com.greenwatch.shared.request;

import com.greenwatch.shared.vo.PollutionVO;

public class StorePollutionRequest extends AbstractRequest {

	private static final long serialVersionUID = 2362333159879739801L;

	private PollutionVO pollution;
	private byte[] imageData;

	public StorePollutionRequest() {
		super();
	}

	public StorePollutionRequest(PollutionVO pollution, byte[] imageData) {
		this();
		this.pollution = pollution;
		this.imageData = imageData;
	}

	public PollutionVO getPollution() {
		return pollution;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setPollution(PollutionVO pollution) {
		this.pollution = pollution;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
