package greenwatch.server;

import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class JDOPollutionResource implements PollutionResource {

	@Override
	public List<PollutionVO> getPollutions(double lat, double lng) {

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
					return results;
				}
			} finally {
				query.closeAll();
			}
		} finally {
			pm.close();
		}
		// TODO Auto-generated method stub
		return new ArrayList<PollutionVO>();
	}

	@Override
	public void storePollution(PollutionVO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

	@Override
	public void updatePollution(PollutionVO pollution) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pollution);
		} finally {
			pm.close();
		}
	}

}
