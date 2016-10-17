package jd.toll.server;

import jd.toll.server.pages.Home;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Created by saturn on 17.10.2016.
 */
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }
}
