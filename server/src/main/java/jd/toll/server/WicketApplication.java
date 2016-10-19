package jd.toll.server;

import jd.toll.server.pages.Home;
import jd.toll.server.pages.HomeExtender;
import jd.toll.server.pages.LoginPage;
import jd.toll.server.pages.SecureWebSession;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by saturn on 17.10.2016.
 */
public class WicketApplication extends AuthenticatedWebApplication implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

    @Override
    protected void init() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("/extender", HomeExtender.class);
        mountPage("/login", LoginPage.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecureWebSession.class;
    }
}
