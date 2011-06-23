package com.greenwatch.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.greenwatch.client.GreetingService;
import com.greenwatch.shared.FieldVerifier;
import com.greenwatch.shared.request.GetPollutionsRequest;
import com.greenwatch.shared.request.StorePollutionRequest;
import com.greenwatch.shared.response.GetPollutionsResponse;
import com.greenwatch.shared.response.StorePollutionResponse;
import com.greenwatch.shared.vo.PollutionIntensity;
import com.greenwatch.shared.vo.PollutionStatus;
import com.greenwatch.shared.vo.PollutionVO;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public StorePollutionResponse doStorePollution(StorePollutionRequest pReq) {
		StorePollutionResponse response = new StorePollutionResponse();

		return response;
	}

	@Override
	public GetPollutionsResponse doGetPollutions(GetPollutionsRequest pReq) {
		GetPollutionsResponse response = new GetPollutionsResponse();
		PollutionVO[] pollutions = new PollutionVO[2];
		pollutions[0] = new PollutionVO(49.287573, 8.446465, PollutionIntensity.high, PollutionStatus.active);
		pollutions[1] = new PollutionVO(49.285518, 8.444269, PollutionIntensity.low, PollutionStatus.active);

		response.setPollutions(pollutions);
		return response;
	}
}
