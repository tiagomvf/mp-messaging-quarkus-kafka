package com.maia.tutorial;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class Receiver {

    private static final System.Logger LOGGER = System.getLogger(Receiver.class.getName());

    @Incoming("kafka")
    public CompletionStage<Void> consume(Message<String> message) {
        String payload = message.getPayload();
        //String key = message.getKey();
        //MessageHeaders headers = message.getHeaders();
        //Integer partition = message.getPartition();
        //Instant timestamp = message.getTimestamp();
        //LOGGER.log(System.Logger.Level.INFO, "received: " + payload + " from topic " + message.getTopic());
        LOGGER.log(System.Logger.Level.INFO, "received: " + payload);
        return message.ack();
    }

}
