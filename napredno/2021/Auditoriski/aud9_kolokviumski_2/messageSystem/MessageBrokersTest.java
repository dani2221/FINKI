package mk.ukim.finki.aud9_kolokviumski_2.messageSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageBrokersTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String date = sc.nextLine();
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        Integer partitionsLimit = Integer.parseInt(sc.nextLine());
        MessageBroker broker = new MessageBroker(localDateTime, partitionsLimit);
        int topicsCount = Integer.parseInt(sc.nextLine());

        //Adding topics
        for (int i = 0; i < topicsCount; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String topicName = parts[0];
            int partitionsCount = Integer.parseInt(parts[1]);
            broker.addTopic(topicName, partitionsCount);
        }

        //Reading messages
        int messagesCount = Integer.parseInt(sc.nextLine());
        System.out.println("===ADDING MESSAGES TO TOPICS===");
        for (int i = 0; i < messagesCount; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length == 4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp, message, key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp, message, partition, key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER ADDITION OF MESSAGES===");
        System.out.println(broker);

        System.out.println("===CHANGE OF TOPICS CONFIGURATION===");
        //topics changes
        int changesCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < changesCount; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String topicName = parts[0];
            Integer partitions = Integer.parseInt(parts[1]);
            try {
                broker.changeTopicSettings(topicName, partitions);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("===ADDING NEW MESSAGES TO TOPICS===");
        messagesCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < messagesCount; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length == 4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp, message, key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp, message, partition, key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("===BROKER STATE AFTER CONFIGURATION CHANGE===");
        System.out.println(broker);
    }
}
