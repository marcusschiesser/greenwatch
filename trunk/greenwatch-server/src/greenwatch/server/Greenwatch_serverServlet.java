package greenwatch.server;

import greenwatch.common.resource.PollutionResource;
import greenwatch.common.vo.PollutionTO;
import greenwatch.common.vo.PollutionVO;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Greenwatch_serverServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		resp.getWriter().println("I will add a new pollution soon.");
		PollutionDO pollution = new PollutionDO();
		double lat = 12.00d;
		double lng = 15.55d;
		pollution.setLatitude(lat);
		pollution.setLongitude(lng);

		resp.getWriter().println("Pollution:" + pollution);
		PollutionResource db = new PollutionServerResource();
		db.storePollution(pollution);

		resp.getWriter().println("Pollution saved: " + pollution);

		resp.getWriter().println("\n\nQuerying for pollutions...");
		PollutionTO[] pollutions = db.getPollutions(lat, lng);
		for (PollutionTO poll : pollutions) {
			resp.getWriter().println("Pollution found: " + poll);
		}

	}
}
