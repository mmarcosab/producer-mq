package com.producer.demo.jms;

import com.producer.demo.model.Person;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
@RequiredArgsConstructor
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final Session session;

    @Value("${activemq.topic-name}")
    private String destinationTopic;

    public void send(Person person) throws JMSException {
        Gson gson = new Gson();
        String jsonPerson = gson.toJson(person);
        Message message = session.createTextMessage(jsonPerson);
        message.setBooleanProperty("test", false);
        jmsTemplate.convertAndSend(destinationTopic, jsonPerson);
    }

}
