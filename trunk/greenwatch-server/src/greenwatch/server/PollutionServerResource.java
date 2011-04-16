package greenwatch.server;

import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class PollutionServerResource extends ServerResource implements PollutionResource {

	@Get
	public PollutionTO[] getPollutions(double lat, double lng) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(PollutionDO.class);
			query.setFilter("(latitude>=latLow && latitude<=latHigh) && (longitude>=lngLow && longitude<=lngHigh)");
			query.setOrdering("timestamp desc");
			query.declareParameters("double latLow, double latHigh, double lngLow, double lngHigh");

			query = pm.newQuery(PollutionDO.class);
			query.setOrdering("timestamp desc");
			try {
				double[] arr = new double[] { lat - 0.1d, lat + 0.1d, lng - 0.1d, lng + 0.1d };

				List<PollutionDO> results = (List<PollutionDO>) query.execute();
				if (!results.isEmpty()) {
					return results.toArray(new PollutionDO[results.size()]);
				}
			} finally {
				query.closeAll();
			}
		} finally {
			pm.close();
		}
		// TODO Auto-generated method stub
		return new PollutionTO[0];
	}

	@Put
	public void storePollution(PollutionTO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

	@Post
	public void updatePollution(PollutionTO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

}
