package com.maia.tutorial;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.LinkedBlockingQueue;

@ApplicationScoped
public class Sender {

    private static final System.Logger LOGGER = System.getLogger(Sender.class.getName());

    private BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    public void add(String message) {
        messages.add(message);
    }

    @Outgoing("data")
    public CompletableFuture<Message<String>> send() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String message = messages.take();
                LOGGER.log(System.Logger.Level.INFO, "Sending message to kafka with the message: " + message);
                return Message.of(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
