package com.example.producer.controller;

import com.example.producer.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // ✅ Marks this class as a REST controller (handles HTTP requests)
@RequestMapping("/events") // ✅ Base URL for all endpoints in this controller
@RequiredArgsConstructor // ✅ Generates a constructor for all final fields (Lombok)
public class EventPublisherController {

    // ✅ Spring injects this automatically — used to send messages to RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    /**
     * ✅ Endpoint: POST /events/publish
     * Example request:
     * POST http://localhost:8080/events/publish?eventType=UserRegistered
     * Body:
     * {
     *   "userId": 1,
     *   "email": "test@example.com"
     * }
     */
    @PostMapping("/publish")
    public String publishEvent(@RequestParam String eventType, @RequestBody Map<String, Object> data) {

        // ✅ Construct an event payload combining event type and data
        var event = Map.of(
                "eventType", eventType,
                "data", data
        );

        // ✅ Publish the event to RabbitMQ queue (defined in RabbitConfig)
        // RabbitTemplate automatically converts this map to JSON using the Jackson2JsonMessageConverter
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, event);

        // ✅ Return a confirmation message
        return "Event published with new Testing (notification_queue_Testing): " + event;
    }
}
