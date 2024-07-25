package com.maia.tutorial;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    Sender sender;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response hello(@PathParam("id") String id) {
        sender.add("Hello from Quarkus REST" + id);
        return Response.ok().build();
    }
}
