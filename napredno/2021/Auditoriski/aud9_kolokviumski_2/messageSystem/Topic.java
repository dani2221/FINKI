package mk.ukim.finki.aud9_kolokviumski_2.messageSystem;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Topic {
    private String topicName;
    private int partitionsCount;
    private Map<Integer, TreeSet<Message>> messagesByPartition;

    public Topic(String topicName, int partitionsCount) {
        this.topicName = topicName;
        this.partitionsCount = partitionsCount;
        this.messagesByPartition = new TreeMap<>();
        IntStream.range(1, partitionsCount + 1)
                .forEach(i -> this.messagesByPartition.put(i, new TreeSet<>()));
    }

    public void addMessage(Message message) throws PartitionDoesNotExistException {
        Integer messagePartition = message.getPartition();
        if (messagePartition == null) {
            messagePartition = Math.abs(message.getKey().hashCode() % this.partitionsCount) + 1;
        }
        if (!this.messagesByPartition.containsKey(messagePartition))
            throw new PartitionDoesNotExistException(this.topicName, messagePartition);

        this.messagesByPartition.computeIfPresent(messagePartition, (key, value) -> {
            if (value.size() == MessageBroker.capacityPerTopic)
                value.remove(value.first());
            value.add(message);
            return value;
        });
    }

    public void changeNumberOfPartitions(int newPartitionsNumber) throws UnsupportedOperationException {
        if (newPartitionsNumber < this.partitionsCount)
            throw new UnsupportedOperationException("Partitions number cannot be decreased!");

        int difference = newPartitionsNumber - this.partitionsCount;
        int size = this.messagesByPartition.size();
        IntStream.range(1, difference + 1)
                .forEach(i -> this.messagesByPartition.putIfAbsent(size + i, new TreeSet<>()));
        this.partitionsCount = newPartitionsNumber;
    }

    @Override
    public String toString() {
        return String.format("Topic: %10s Partitions: %5d\n%s",
                this.topicName,
                this.partitionsCount,
                this.messagesByPartition.entrySet()
                        .stream()
                        .map(entry -> String.format("%2d : Count of messages: %5d\n%s",
                                entry.getKey(),
                                entry.getValue().size(),
                                !entry.getValue().isEmpty() ? "Messages:\n" +
                                        entry.getValue()
                                                .stream()
                                                .map(Message::toString)
                                                .collect(Collectors.joining("\n"))
                                        : ""
                        )).collect(Collectors.joining("\n")));
    }
}
