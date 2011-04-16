package greenwatch.server;

import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionVO;

import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class PollutionServerResource extends ServerResource implements PollutionResource {

	@Get
	public PollutionVO[] getPollutions(double lat, double lng) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(PollutionVO.class);
			query.setFilter("(latitude >= latLow && latitude <= latHigh) && (longitude >= lngLow && longitude <= lngHigh)");
			query.setOrdering("timestamp desc");
			query.declareParameters("double latLow double latHigh double lngLow double lngHigh");

			try {
				List<Double> arg = Arrays.asList(lat - 0.1d, lat + 0.1d, lng - 0.1d, lng + 0.1d);
				List<PollutionVO> results = (List<PollutionVO>) query.execute(arg);
				if (!results.isEmpty()) {
					return results.toArray(new PollutionVO[results.size()]);
				}
			} finally {
				query.closeAll();
			}
		} finally {
			pm.close();
		}
		return new PollutionVO[0];
	}

	@Put
	public void storePollution(PollutionVO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

	@Post
	public void updatePollution(PollutionVO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

}
