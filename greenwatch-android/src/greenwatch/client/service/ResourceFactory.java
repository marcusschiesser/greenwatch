package greenwatch.client.service;

import greenwatch.common.resource.PollutionResource;

import org.restlet.resource.ClientResource;

public class ResourceFactory {

	public static PollutionResource createPollutionResource() {
		PollutionResource resource = null;
		if (ResourceFactory.USE_MOCKS) { // TODO: use Resources instead
			resource = InMemoryStorage.getInstance();
		} else {
			ClientResource cr = new ClientResource("http://10.0.2.2:8888/rest/pollutions");
			resource = cr.wrap(PollutionResource.class);
		}
		return resource;
	}

	public static final boolean USE_MOCKS = true;

}
