package mk.ukim.finki.aud9_kolokviumski_2.messageSystem;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MessageBroker {
    private static LocalDateTime minimumDate;
    public static Integer capacityPerTopic;
    private Map<String, Topic> topicMap;

    public MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic) {
        MessageBroker.minimumDate = minimumDate;
        MessageBroker.capacityPerTopic = capacityPerTopic;
        this.topicMap = new TreeMap<>();
    }

    public void addTopic(String topic, int partitionsCount) {
        this.topicMap.put(topic, new Topic(topic, partitionsCount));
    }

    public void addMessage(String topic, Message message) throws PartitionDoesNotExistException, UnsupportedOperationException {
        if(message.getTimestamp().isBefore(minimumDate)) return;
        this.topicMap.get(topic).addMessage(message);
    }

    public void changeTopicSettings (String topic, int partitionsCount) throws UnsupportedOperationException {
        this.topicMap.get(topic).changeNumberOfPartitions(partitionsCount);
    }

    @Override
    public String toString() {
        return String.format("Broker with %2d topics:\n%s",
                this.topicMap.size(),
                this.topicMap.values().stream().map(Topic::toString).collect(Collectors.joining("\n")));
    }
}
