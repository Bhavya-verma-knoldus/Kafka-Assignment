package com.knoldus;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer
{
    public static void main(String[] args) throws Exception
    {
        ConsumerListener consumerListener = new ConsumerListener();
        Thread thread = new Thread(consumerListener);
        thread.start();
    }

    public static void consumer()
    {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.knoldus.UserDeserializer");
        properties.put("group.id", "test-group");

        String message = null;
        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("user");
        kafkaConsumer.subscribe(topics);

        try{
            while (true)
            {
                ConsumerRecords<String, User> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, User> record: records)
                {
                    System.out.println("Message received " + record.value().toString());
                    message = new String(record.value().toString())+"\n";
                    writeDataToFile(message);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            kafkaConsumer.close();
        }
    }

    private static void writeDataToFile(String completeMessage) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter("filename.txt", true));
        writer.append(completeMessage);
        writer.close();
    }
}

class ConsumerListener implements Runnable
{
    public void run() {
        Consumer.consumer();
    }
}