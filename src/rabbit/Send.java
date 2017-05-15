package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * Created by Musafir on 5/15/2017.
 */
public class Send {
    public static String QUEUE_TO_SEND = "hello";
    public static String QUEUE_TO_RECEIVE = "alo";
    private static Channel channel;

    public static void init() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            System.out.println("Init called for sent channel " + QUEUE_TO_SEND);
            channel.queueDeclare(QUEUE_TO_SEND, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void notifyChange() {
        if (channel == null) {
            init();
        }
        try {
            String message = "update";
            channel.basicPublish("", QUEUE_TO_SEND, null, message.getBytes("UTF-8"));
            System.out.println("notifyChange " + QUEUE_TO_SEND);
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) throws Exception {
        init();
        while (true) {
            new Scanner(System.in).nextLine();
            System.out.println("Fac ceva");
            notifyChange();
        }
    }
}
