package mk.ukim.finki.aud9_kolokviumski_2.messageSystem;

import java.time.LocalDateTime;

public class Message implements Comparable<Message>{
    private LocalDateTime timestamp;
    private String message;
    private Integer partition;
    private String key;

    public Message(LocalDateTime timestamp, String message, Integer partition, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.partition = partition;
        this.key = key;
    }

    public Message(LocalDateTime timestamp, String message, String key) {
        this.timestamp = timestamp;
        this.message = message;
        this.key = key;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPartition() {
        return partition;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int compareTo(Message o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
