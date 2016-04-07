# vaadin-jsp-integration-example

## Check [this add-on](https://vaadin.com/directory#!addon/jsp-integration) to automate the rendering of Vaadin UIs in JSP files.

This example shows how to add a Vaadin UI into a JSP page.

![Alt text](/screenshot.png?raw=true "Screenshot")

## Steps
To include a new Vaadin UI into an existing JSP page, follow these steps:

**1. Add Vaadin dependencies:**

 * By downloading the JAR files from https://vaadin.com/download
 * or including the dependencies using your dependency management tool. Example in Maven: https://github.com/alejandro-du/vaadin-jsp-integration-example/commit/5abe74a9b650fe35b6f806ed4ab7a87f3c23225d#diff-600376dffeb79835ede4a0b285078036

**2. Implement a new Vaadin UI:**

 * Add the Vaadin UI class:

```
@Theme("valo")
public class ExampleVaadinUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("This is a Vaadin UI");
    }
}
```

 * Add a Vaadin Servlet mapped to a custom url. This can be done using annotations, for example:

```
...
public class ExampleVaadinUI extends UI {
    ...
    
    @WebServlet(urlPatterns = {"/example-ui/*", "/VAADIN/*"}, name = "ExampleVaadinUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ExampleVaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
```

 * Add a GWT module definition file. For example, you can add the file [ExampleVaadinUIWidgetset.gwt.xml](/src/main/resources/com/example/ui/ExampleVaadinUiWidgetset.gwt.xml) in the `src/main/resources/com/example/ui` directory.
 * Annotate the Vaadin UI implementation class with `@Widgetset` and the appropriate widgetset name. For example:

```
...
@Widgetset("com.example.ui.ExampleVaadinUiWidgetset")
public class ExampleVaadinUI extends UI {
    ...
}
```

 * Compile the widgetset. If you are using Maven, run the following:
 
```
mvn vaadin:compile
````

 * Deploy the application and check that you can see the Vaadin UI. Remember to add the correct url according to what you configured as `urlMappings` in the `VaadinServlet`. For the previous example, it could be something like <http://localhost:8080/example-ui>.

**3. Add the Vaadin application into the JSP page:**

 * Add an HTML `div` element to be used as target for the Vaadin UI. Example:

```
<div id="example-vaadin-ui" class="v-app valo example-vaadin-ui" style="height: 320px">
    <div class="v-app-loading"></div>
    <noscript>
        You have to enable javascript in your browser to use an application built with Vaadin.
    </noscript>
</div>
```

 * Include Vaadin's bootstrap script:

```
<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js?v=7.6.2"></script>
```

 * Call the `vaadin.initApplication()` function. Example:

```
<script type="text/javascript">//<![CDATA[
    vaadin.initApplication("example-vaadin-ui", {
        "theme": "valo",
        "versionInfo": {
            "vaadinVersion": "7.6.2"
        },
        "widgetset": "com.example.ui.ExampleVaadinUiWidgetset",
        "comErrMsg": {
            "caption": "Communication problem",
            "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
            "url": null
        },
        "authErrMsg": {
            "caption": "Authentication problem",
            "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
            "url": null
        },
        "sessExpMsg": {
            "caption": "Session Expired",
            "message": "Take note of any unsaved data, and <u>click here</u> or press ESC key to continue.",
            "url": null
        },
        "vaadinDir": "./VAADIN/",
        "debug": true,
        "standalone": false,
        "heartbeatInterval": 300,
        "browserDetailsUrl": "/example-ui",
        "serviceUrl": "/example-ui"
    });
//]]></script>
```

**For more information about embedded Vaadin UIs see https://vaadin.com/docs/-/part/framework/advanced/advanced-embedding.html.**
