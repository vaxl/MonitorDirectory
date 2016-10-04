package ru.vaxl.Client.connections;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ConnectRabbitImpl implements ConnectToServer {
    private final List<String> list = new ArrayList<>();
    private final static String QUEUE_NAME = "hello";
    private Channel channel;
    private Connection connection;
    private QueueingConsumer consumer;


    @Override
    public boolean init(String addr) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(addr);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);
            return true;
        } catch (IOException | TimeoutException  e) {
            return false;
        }
    }

    @Override
    public void close() throws IOException, TimeoutException {
        if (channel != null) channel.close();
        if (connection != null) connection.close();
    }

    @Override
    public String recieve() throws IOException {
        try {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            return new String(delivery.getBody(), "UTF-8");
        } catch (InterruptedException ignore) {
            return "";
        }

    }
}