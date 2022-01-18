package com.kafka.consumer;

import com.kafka.model.UserData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserDataConsumer.class);

    @Value("${spring.kafka.group-id}")
    private String userGroupConsumer;

    @Value("${topic.name}")
    private String topic;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "userKafkaListenerContainerFactory")
    public void listenTopicUser(ConsumerRecord<String, UserData> record) {
        logger.info("Received message partition: {}", record.partition(), record.toString());
    }
}
