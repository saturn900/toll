package jd.toll.server;

import jd.toll.server.pages.Home;
import jd.toll.server.pages.HomeExtender;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by saturn on 17.10.2016.
 */
public class WicketApplication extends WebApplication implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

    @Override
    protected void init() {
        mountPage("/extender", HomeExtender.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
