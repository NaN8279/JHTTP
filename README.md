# HTTPScript

HTTPScript is a branch of the JHTTP server.

You can use HTTPScript to generate HTML pages based on a request, server-side.

Example code:

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Test HTTPScript</title>
    </head>
    <body>
        <httpscript>
            function onRequest(request) {
                return "Your ip is: " + request.getClient().getIP();
            }
        </httpscript>
    </body>
</html>
```

As you can see, using HTTPScript doesn't require you to learn a new language if you already know HTML, CSS and JavaScript.

How HTTPScript works:

Anything inside the `<httpscript>` tag will be executed on the server.

When the user makes a request, the following happens:

First, the server will call the `onRequest(request)` function with information about the request the user made.

Then, the function gets the IP of the client and returns it.

Lastly, the server displays the return value of the function to the user.


To get more information about the `request` parameter passed to the function,
please go [here](https://nan8279.github.io/JHTTP/doc/io/github/nan8279/jhttp/request/Request.html).

This project uses Mozilla Rhino to parse JavaScript, Jsoup to parse HTML and IntelliJ GUI designer for the GUI.
