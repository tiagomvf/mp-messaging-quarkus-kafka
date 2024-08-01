package com.maia.tutorial;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    Sender sender;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String messagem = "Hello from Quarkus REST";
        sender.add(messagem);
        return messagem;
    }
}
