package ru.vaxl.Server.connections;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectRabbitImpl implements Connect {

    private final static String QUEUE_NAME = "monitor";
    private Channel channel;
    private Connection connection;

    @Override
    public void close() throws IOException,TimeoutException{
            if (channel != null) channel.close();
            if (connection != null) connection.close();
    }

    @Override
    public boolean send(String message) {
        try{
            if (channel==null) return false;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            return true;
        }catch (IOException e ){return false;}
    }

    public boolean init(String addr) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(addr);
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            return true;
        }catch (IOException | TimeoutException e) { return false;}
    }
}
