package per.pqr.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class RestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String post(String str) {
        return str;
    }
}