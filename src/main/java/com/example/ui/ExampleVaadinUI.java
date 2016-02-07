package com.example.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

/**
 * @author alejandro@vaadin.com
 **/
@Theme("valo")
@Widgetset("com.example.ui.ExampleVaadinUiWidgetset")
public class ExampleVaadinUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        Button button = new Button("Click me");
        layout.addComponent(button);

        button.addClickListener(e -> layout.addComponent(new Label("Thanks for clicking!")));
    }

    @WebServlet(urlPatterns = {"/example-ui/*", "/VAADIN/*"}, name = "ExampleVaadinUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ExampleVaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
