package com.maia.tutorial;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

import static java.lang.System.Logger.Level.INFO;

@ApplicationScoped
public class Receiver {

    private static final System.Logger LOGGER = System.getLogger(Receiver.class.getName());

    @Incoming("kafka")
    public CompletionStage<Void> consume(Message<String> message) {
        String payload = message.getPayload();
        LOGGER.log(INFO, "received: " + payload);
        return message.ack();
    }

}
