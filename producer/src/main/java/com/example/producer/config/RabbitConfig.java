package com.example.producer.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ✅ Marks this class as a Spring configuration class
public class RabbitConfig {

    // ✅ Queue name constant used across the producer and consumer apps.
    // This helps keep the queue name consistent and avoid hardcoding it in multiple places.
    // You can easily change the queue name here when needed.
    public static final String QUEUE_NAME = "notification_queue_Testing";

    /**
     * ✅ Defines a Bean for converting messages to/from JSON.
     *
     * By default, RabbitTemplate (used for sending messages)
     * and RabbitListener (used for consuming messages)
     * work with raw byte arrays or Strings.
     *
     * This Jackson2JsonMessageConverter allows automatic serialization
     * of Java objects into JSON when sending, and automatic deserialization
     * back into Java objects when receiving.
     *
     * It’s essential when you’re sending structured data like Maps or POJOs.
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
