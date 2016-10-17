package jd.toll.server.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Created by saturn on 17.10.2016.
 */
public class Home extends WebPage {

    public Home() {
        add(new Label("label", "Thi is label!!!!"));
    }

}
