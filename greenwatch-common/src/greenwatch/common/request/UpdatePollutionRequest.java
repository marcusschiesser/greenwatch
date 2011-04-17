package greenwatch.common.request;

import greenwatch.common.vo.PollutionVO;

public class UpdatePollutionRequest extends AbstractRequest {

	private int id;
	private PollutionVO pollution;

	public UpdatePollutionRequest(int id, PollutionVO pollution) {
		super();
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
