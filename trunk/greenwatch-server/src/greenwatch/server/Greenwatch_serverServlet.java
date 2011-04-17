package greenwatch.server;

import greenwatch.common.request.GetPollutionsRequest;
import greenwatch.common.request.StorePollutionRequest;
import greenwatch.common.resource.PollutionResource;
import greenwatch.common.response.GetPollutionsResponse;
import greenwatch.common.response.StorePollutionResponse;
import greenwatch.common.vo.PollutionIntensity;
import greenwatch.common.vo.PollutionTO;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Greenwatch_serverServlet extends HttpServlet {
	private static final int MARGIN = 100;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		// ADDING A POLLUTION
		resp.getWriter().println("I will add a new pollution soon.");
		StorePollutionRequest request = new StorePollutionRequest();
		int lat = (int) (12.00d * 1e6);
		int lng = (int) (15.55d * 1e6);
		request.setLatitude(lat);
		request.setLongitude(lng);
		request.setIntensity(PollutionIntensity.high);
		request.setComment("some comment");
		request.setTimestamp(new Date());

		resp.getWriter().println("StorePollutionRequest: " + request);
		PollutionResource db = new PollutionServerResource();
		StorePollutionResponse storePollutionResponse = db.storePollution(request);

		resp.getWriter().println("Pollution saved\n");

		// LISTING POLLUTIONS
		resp.getWriter().println("\n\nQuerying for pollutions...");
		GetPollutionsRequest getPollutionsRequest = new GetPollutionsRequest();
		getPollutionsRequest.setMinLat(lat - MARGIN);
		getPollutionsRequest.setMaxLat(lat + MARGIN);
		getPollutionsRequest.setMinLng(lng - MARGIN);
		getPollutionsRequest.setMaxLng(lng + MARGIN);
		GetPollutionsResponse getPollutionsResponse = db.getPollutions(getPollutionsRequest);
		for (PollutionTO poll : getPollutionsResponse.getPollutions()) {
			resp.getWriter().println("Pollution found: " + poll);
		}

	}
}
