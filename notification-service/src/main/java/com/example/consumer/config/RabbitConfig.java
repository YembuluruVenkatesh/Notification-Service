package com.example.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ✅ Marks this class as a Spring configuration class
public class RabbitConfig {

    // ✅ Define a constant for the queue name — keeps it consistent across the project
    // This should be the same name your producer is sending messages to.
    public static final String QUEUE_NAME = "notification_queue_Testing";

    /**
     * ✅ Declares a Queue bean.
     *
     * This tells Spring AMQP (and RabbitMQ) to create a queue with the given name
     * when the application starts.
     *
     * Parameters:
     *  - QUEUE_NAME: The name of the queue (must match what producer uses)
     *  - durable = true: Means the queue will survive RabbitMQ restarts (persistent)
     *
     * Without this bean, you might see an error like:
     *   ❌ "no queue 'notification_queue_Testing' in vhost '/'"
     */
    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NAME, true); // true = durable
    }

    /**
     * ✅ Defines a message converter for JSON serialization/deserialization.
     *
     * When messages are received from RabbitMQ, this converter will automatically:
     *  - Convert JSON -> Java Map (or POJO)
     *
     * And when publishing (if used), it does the reverse:
     *  - Convert Java object -> JSON
     *
     * This allows your consumer code to work with clean Java objects/maps instead of raw byte data.
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
