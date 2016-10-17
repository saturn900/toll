package jd.toll.server.pages;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by saturn on 18.10.2016.
 */
public class PagesTest {

    private static WicketTester tester;

    @Before
    public void init() {
        getTester();
    }

    WicketTester getTester() {

        if (tester == null) {
            tester = new WicketTester();
        }
        return tester;
    }

    @Test
    public void homePageTest() throws Exception {
        tester.startPage(Home.class);
        tester.assertRenderedPage(Home.class);
    }

    @Test
    public void homeExtenderTest() throws Exception {
        tester.startPage(HomeExtender.class);
        tester.assertRenderedPage(HomeExtender.class);
    }

}