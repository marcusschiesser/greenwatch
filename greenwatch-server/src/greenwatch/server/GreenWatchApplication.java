package greenwatch.server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class GreenWatchApplication extends Application {
    /**
     * When launched as a standalone application.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getClients().add(Protocol.FILE);
        component.getServers().add(Protocol.HTTP, 8080);
        component.getDefaultHost().attach(new GreenWatchApplication());
        component.start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/pollutions", PollutionServerResource.class);

        return router;
    }
}
