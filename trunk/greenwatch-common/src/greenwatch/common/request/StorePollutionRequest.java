package greenwatch.common.request;

import greenwatch.common.vo.PollutionVO;

public class StorePollutionRequest {
	
	private PollutionVO pollution;
	private byte[] imageData;
	
	public StorePollutionRequest(PollutionVO pollution, byte[] imageData) {
		super();
		this.pollution = pollution;
		this.imageData = imageData;
	}
	
	public PollutionVO getPollution() {
		return pollution;
	}
	public byte[] getImageData() {
		return imageData;
	}
	
}
