package com.example.consumer.listener;

import com.example.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleEvent(Map<String, Object> event) {
        String eventType = (String) event.get("eventType");
        Map<String, Object> data = (Map<String, Object>) event.get("data");

        System.out.println("🎯 Received Event: " + eventType + " -> " + data);

        switch (eventType) {
            case "UserRegistered" ->
                    System.out.println("📧 Sending welcome email to " + data.get("email"));
            case "OrderPlaced" ->
                    System.out.println("📱 Sending order confirmation for Order ID: " + data.get("orderId"));
            default ->
                    System.out.println("⚠️ Unknown event type: " + eventType);
        }
    }
}
