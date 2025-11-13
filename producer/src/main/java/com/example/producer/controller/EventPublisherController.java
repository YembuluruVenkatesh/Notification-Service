package com.example.producer.controller;

import com.example.producer.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPublisherController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/publish")
    public String publishEvent(@RequestParam String eventType, @RequestBody Map<String, Object> data) {
        var event = Map.of("eventType", eventType, "data", data);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, event);
        return "Event published with new Testing: " + event;
    }
}
