package com.example.performance.Controller;

import com.example.performance.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Random;
import java.util.UUID;

@RestController
public class MainController {
    private final KafkaProducer kafkaProducer;

    @GetMapping("/tab")
    public String controllerTab(@RequestParam(value = "name", defaultValue = "default") String name) {
        return "Tab has been opened " + name;
    }

    @GetMapping("/home")
    public String controllerHomePage(@RequestParam(value = "name", defaultValue = "default") String name) {
        System.out.println(name);
        System.out.println("Home page has been opened");
        return "Home page has been opened";
    }

    @PostMapping("/create")
    public String createUuid(@RequestBody String body) {
        System.out.println(body);
        return "UUID has been created";
    }

    @PostMapping("/kafka")
    public String kafka(@RequestBody String body) {
        return "kafka";
    }

    @Autowired
    public MainController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/money_transfer")
    public String kafkaSend(@RequestBody String body) {
        String randomUUID = UUID.randomUUID().toString();
        //String randomBody = generateRandomString(10);
        kafkaProducer.sendMessage("in", randomUUID, body);
        return "Message has been sent";
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
