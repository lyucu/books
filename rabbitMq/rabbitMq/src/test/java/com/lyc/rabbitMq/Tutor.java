package com.lyc.rabbitMq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import junit.framework.TestCase;

import org.junit.Test;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Tutor extends TestCase {
    private boolean durable = false;

    @Test
    public void testSend() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("demoQueue", durable, false, false, null);
        String message = "hellow world1";
        channel.basicPublish("", "demoQueue", null, message.getBytes());
        channel.close();
        connection.close();
    }

    @Test
    public void testReceive() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("demoQueue", durable, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("begin");
                try {
                    Thread.sleep(4000);
                    System.out.println(message);
                    if (message.contains("1")) {
                        throw new InterruptedException();
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // false 需要ackflag true 自动删除
        channel.basicConsume("demoQueue", false, consumer);
    }

    @Test
    public void testDurable() throws IOException, TimeoutException {
        durable = true;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // make queue durable
        channel.queueDeclare("demoQueue", durable, false, false, null);
        String message = "hellow world1";
        // make message durable
        channel.basicPublish("", "demoQueue", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.close();
        connection.close();
    }

    @Test
    public void testDurableReceive() throws IOException, TimeoutException {
        durable = true;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("demoQueue", durable, false, false, null);

        // 设置consumer能接受的message的数量
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("begin");
                try {
                    Thread.sleep(4000);
                    System.out.println(message);
                    // if (message.contains("1")) {
                    // throw new InterruptedException();
                    // }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // false 需要ackflag true 自动删除
        channel.basicConsume("demoQueue", false, consumer);
    }

    @Test
    public void testExchangeProducer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //direct, topic, headers , fanout for exchange type
        //fanout broadcast message to every queue
        channel.exchangeDeclare("demoExchange", "fanout");
        
        String message = "hellow world1";
        //demoExchange is exchange name
        channel.basicPublish("demoExchange", "", null, message.getBytes());
        channel.close();
        connection.close();

    }
    
    @Test
    public void testExchangeProducerConsumer() throws IOException, TimeoutException{
    	
         ConnectionFactory connectionFactory = new ConnectionFactory();
         connectionFactory.setHost("localhost");
         Connection connection = connectionFactory.newConnection();
         final Channel channel = connection.createChannel();
         
         channel.exchangeDeclare("demoExchange", "fanout");
         
         //creat temp queue
         String queueName = channel.queueDeclare().getQueue();
         // bind the queue and the exchange for consumer
         channel.queueBind(queueName, "demoExchange", "");
         System.out.println(queueName);

         // 设置consumer能接受的message的数量
         channel.basicQos(1);

         Consumer consumer = new DefaultConsumer(channel) {
             @Override
             public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                     throws IOException {
                 String message = new String(body, "UTF-8");
                 System.out.println(message);
             }
         };
         channel.basicConsume(queueName, true, consumer);
         while (true) {
        	 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	 System.out.println("2");
         }
         
    }
    
    
    @Test
    public void testRoutingProducer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //direct, topic, headers , fanout for exchange type
        channel.exchangeDeclare("demoExchange", "direct");
        
        String severity = "sev1";
        String message = "hellow world1";
        //demoExchange is exchange name and severity is route_key
        channel.basicPublish("demoExchange", severity, null, message.getBytes());
        
         severity = "sev2";
         message = "hellow world2";
        //demoExchange is exchange name and severity is route_key
        channel.basicPublish("demoExchange", severity, null, message.getBytes());
        
        channel.close();
        connection.close();
    }
    
    @Test
    public void  testRoutingConsumer () throws IOException, TimeoutException, InterruptedException {
    	ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("demoExchange", "direct");
        
        //creat temp queue
        String queueName = channel.queueDeclare().getQueue();
        String severity = "sev2";
        // bind the queue and the exchange for consumer  severity is route_key 可以用for循环连续绑定
        channel.queueBind(queueName, "demoExchange", severity);
        System.out.println(queueName);

        // 设置consumer能接受的message的数量
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
        while (true) {
        	Thread.sleep(3000);
       	 	System.out.println(severity);
        }
    }
    
    @Test
    public void testTopicProducer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //direct, topic, headers , fanout for exchange type
        channel.exchangeDeclare("demoExchange", "topic");
        
        String routekey = "sev1.test2";
        String message = "hellow world1";
        //routekey is route_key
        channel.basicPublish("demoExchange", routekey, null, message.getBytes());
        
        routekey = "sev2.test1";
         message = "hellow world2";
        //demoExchange is exchange name and severity is route_key
        channel.basicPublish("demoExchange", routekey, null, message.getBytes());
        
        routekey = "sev2.test2";
        message = "hellow world3";
       //demoExchange is exchange name and severity is route_key
       channel.basicPublish("demoExchange", routekey, null, message.getBytes());
        
        channel.close();
        connection.close();
    }
    
    @Test
    public void  testTopicConsumer () throws IOException, TimeoutException, InterruptedException {
    	ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("demoExchange", "topic");
        
        //creat temp queue
        String queueName = channel.queueDeclare().getQueue();
        String severity = "sev1.*";
        // bind the queue and the exchange for consumer  severity is route_key 可以用for循环连续绑定
        channel.queueBind(queueName, "demoExchange", severity);
        
         severity = "*.test1";
        // bind the queue and the exchange for consumer  severity is route_key 可以用for循环连续绑定
        channel.queueBind(queueName, "demoExchange", severity);
        
        System.out.println(queueName);

        // 设置consumer能接受的message的数量
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
        while (true) {
        	Thread.sleep(3000);
       	 	System.out.println(severity);
        }
    }
    
    @Test
    public void testRPCProducer() throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        
        String corrId = java.util.UUID.randomUUID().toString();

        BasicProperties props = new BasicProperties
                                    .Builder()
                                    .correlationId(corrId)
                                    .replyTo(queueName)
                                    .build();

        channel.basicPublish("", "demoQueue", props, "hellow".getBytes());

        while (true) {
        	//consume feedback message
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                  System.out.println(new String(delivery.getBody()));;
                break;
            }
        }

        
        channel.close();
        connection.close();
    }
    
    
    @Test
    public void  testRPCConsumer () throws IOException, TimeoutException, InterruptedException {
    	ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        
        channel.queueDeclare("demoQueue", durable, false, false, null);
        
        // 设置consumer能接受的message的数量
        channel.basicQos(1);
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("demoQueue", false,consumer);
        System.out.println("start");
        
        while (true) {
        	//cunsoume received messge
        	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        	BasicProperties props = delivery.getProperties();
        	BasicProperties replyProps = new BasicProperties
                    .Builder()
                    //返回标示的uuid
                    .correlationId(props.getCorrelationId())
                    .build();
        	System.out.println("receive" + new String(delivery.getBody()));
        	String message = new String(delivery.getBody()) + "resopnse";
        	//将message 返回指 指定的feedback队列
        	channel.basicPublish( "", props.getReplyTo(), replyProps, message.getBytes());

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}
