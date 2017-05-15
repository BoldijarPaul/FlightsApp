package rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static rabbit.Send.QUEUE_TO_RECEIVE;
import static rabbit.Send.QUEUE_TO_SEND;

/**
 * Created by Musafir on 5/15/2017.
 */
public class Recv {
    private static Channel channel;

    public static void init(OnMessage onMessage) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            System.out.println("Receive init : register to" + QUEUE_TO_RECEIVE);
            channel.queueDeclare(QUEUE_TO_RECEIVE, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    onMessage.onMessage(message);
                }
            };
            channel.basicConsume(QUEUE_TO_RECEIVE, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public interface OnMessage {
        void onMessage(String message);
    }

    public static void main(String[] argv) throws Exception {
        Recv.init(message -> System.out.println("Checheraut am primit " + message));
    }
}
