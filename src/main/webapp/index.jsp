<html>
    <meta>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </meta>
    <body>
        <div class="container">
            <h2>Vaadin-JSP integration example</h2>
            <p>
                This is a standard JSP web page.
            </p>
            <div class="alert alert-success">
                <%
                    String htmlResponse = "Local time from server: " + java.time.LocalDateTime.now();
                    out.println(htmlResponse);
                %>
            </div>
        </div>
    </body>
</html>
