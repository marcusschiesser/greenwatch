package util;

import java.util.List;

import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.request.UpdatePollutionRequest;
import greenwatch.common.response.GetFullPollutionResponse;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;
import greenwatch.server.PollutionBean;

public class DatabaseObjectsUtil {

	public static PollutionBean convertStorePollutionRequestToBean(StorePollutionRequest request) {
		PollutionBean bean = new PollutionBean();
		if (request != null) {
			bean.setLatitude(request.getPollution().getLatitude());
			bean.setLongitude(request.getPollution().getLongitude());
			bean.setComment(request.getPollution().getComment());
			bean.setIntensity(request.getPollution().getIntensity());
			bean.setTimestamp(request.getPollution().getTimestamp());
		}
		return bean;
	}

	public static PollutionBean convertUpdatePollutionRequestToBean(UpdatePollutionRequest request) {
		PollutionBean bean = new PollutionBean();
		if (request != null) {
			bean.setId(request.getId());
		}
		return bean;
	}

	public static GetFullPollutionResponse convertBeanToGetFullPollutionResponse(PollutionBean bean) {
		GetFullPollutionResponse response = new GetFullPollutionResponse();

		return response;
	}

	public static PollutionTO[] convertBeansToTOs(List<PollutionBean> results) {
		PollutionTO[] ret = new PollutionTO[results.size()];
		for (int i = 0; i < results.size(); i++) {
			PollutionBean bean = results.get(i);
			PollutionTO vo = new PollutionVO();

			vo.setId(bean.getId());
			vo.setLatitude(bean.getLatitude());
			vo.setLongitude(bean.getLongitude());

			ret[i] = vo;
		}
		return ret;
	}

}
