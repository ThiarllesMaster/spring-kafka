package com.kafka.producer;

import com.kafka.model.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final Logger logger = LoggerFactory.getLogger(UserProducer.class);
    private final String topic;
    private final KafkaTemplate<String, UserData> kafkaTemplate;

    public UserProducer(@Value("${topic.name}") String topic, final KafkaTemplate<String, UserData> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserData userData) {
        kafkaTemplate.send(topic, userData).addCallback(
                success -> logger.info("Message sent".concat(userData.toString())),
                failure -> logger.error("message couldn't not sent".concat(failure.getMessage()))
        );
    }
}
