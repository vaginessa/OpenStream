package com.openstream.samueltulach.openstream;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.util.ServerRunner;

public class Server extends NanoHTTPD {

    public String ipadress;
    public String video;

    public void runserver() {
        ServerRunner.run(Server.class);
    }

    public Server() {
        super(8080);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        System.out.println(method + " '" + uri + "' ");

        String msg = video;

        Response response = newFixedLengthResponse(msg);
        response.addHeader("Access-Control-Allow-Origin", "*");

        return response;
    }
}
