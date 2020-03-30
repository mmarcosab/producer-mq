package com.producer.demo.jms;

import com.producer.demo.model.Person;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

public class Producer {

    private Session session;
    private Connection connection;
    private InitialContext context;
    private ConnectionFactory factory;

    public void connectToRow(Person person) throws NamingException, JMSException {
        openConnection();
        Destination fila = (Destination) context.lookup("pessoa");
        MessageProducer producer = session.createProducer(fila);
        Gson gson = new Gson();
        String jsonPerson = gson.toJson(person);
        Message mensagem = session.createTextMessage(jsonPerson);
        producer.send(mensagem);
        closeConnection();
    }

    private void openConnection() throws JMSException, NamingException{
        context = new InitialContext();
        factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }


    private void closeConnection() throws JMSException, NamingException {
        session.close();
        connection.close();
        context.close();
    }



}
