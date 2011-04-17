package greenwatch.common.request;

import greenwatch.common.vo.PollutionVO;

public class StorePollutionRequest {
	
	private PollutionVO pollution;
	private int[] imageData;
	
	public StorePollutionRequest(PollutionVO pollution, int[] imageData) {
		super();
		this.pollution = pollution;
		this.imageData = imageData;
	}
	
	public PollutionVO getPollution() {
		return pollution;
	}
	public int[] getImageData() {
		return imageData;
	}
	
}
