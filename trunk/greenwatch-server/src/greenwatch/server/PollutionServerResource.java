package greenwatch.server;

import greenwatch.common.request.GetFullPollutionRequest;
import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.request.UpdatePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetFullPollutionResponse;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.response.StorePollutionResponse;
import greenwatch.common.response.UpdatePollutionResponse;
import greenwatch.common.vo.PollutionTO;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import util.DatabaseObjectsUtil;

public class PollutionServerResource extends ServerResource implements PollutionResource {

	@Override
	@Get
	public GetPollutionsResponse getPollutions(GetPollutionsRequest request) {

		GetPollutionsResponse response = new GetPollutionsResponse();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(PollutionBean.class);
			// query.setFilter("geohash >= :geohash_min AND geohash <= :geohash_max");
			// query.setParameter("geohash_min", GeohashUtils.encode(minLat,
			// minLng));
			// query.setParameter("geohash_max", GeohashUtils.encode(maxLat,
			// maxLng));

			query = pm.newQuery(PollutionBean.class);
			query.setOrdering("timestamp desc");
			try {

				List<PollutionBean> results = (List<PollutionBean>) query.execute();
				if (!results.isEmpty()) {
					PollutionTO[] pollutions = DatabaseObjectsUtil.convertBeansToTOs(results);
					response.setPollutions(pollutions);
				}
			} finally {
				query.closeAll();
			}
		} finally {
			pm.close();
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	@Put
	public void storePollution(StorePollutionRequest request) {

		PollutionBean bean = DatabaseObjectsUtil.convertStorePollutionRequestToBean(request);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(bean);
			StorePollutionResponse response = new StorePollutionResponse();
			return ;
		} finally {
			pm.close();
		}
	}

	@Override
	@Get
	public UpdatePollutionResponse updatePollution(UpdatePollutionRequest request) {
		PollutionBean bean = DatabaseObjectsUtil.convertUpdatePollutionRequestToBean(request);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(bean);
			UpdatePollutionResponse response = new UpdatePollutionResponse();
			return response;
		} finally {
			pm.close();
		}
	}

	@Override
	@Get
	public GetFullPollutionResponse getFullPollution(GetFullPollutionRequest request) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			PollutionBean bean = (PollutionBean) pm.getObjectById(request.getId());
			GetFullPollutionResponse response = DatabaseObjectsUtil.convertBeanToGetFullPollutionResponse(bean);
			return response;
		} finally {
			pm.close();
		}
	}

}
