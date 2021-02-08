package com.knoldus;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Producer
{
    public static void main(String[] args) throws Exception
    {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.UserSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try{
            for(int i = 0; i < 10; i++)
            {
                User user=new User(i,"Bhavya",getRandomNumberUsingNextInt(1,100),"Btech");
                System.out.println("Message " + user.toString() + " sent !!");
                kafkaProducer.send(new ProducerRecord("user", Integer.toString(i), user));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            kafkaProducer.close();
        }
    }

    public static int getRandomNumberUsingNextInt(int minimum, int maximum)
    {
        Random random = new Random();
        return random.nextInt(maximum - minimum) + minimum;
    }
}