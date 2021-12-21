package MessageSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class PartitionAssigner {
    public static Integer assignPartition (Message message, int partitionsCount) {
        return (Math.abs(message.key.hashCode())  % partitionsCount) + 1;
    }
}

public class MessageBrokersTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String date = sc.nextLine();
        LocalDateTime localDateTime =LocalDateTime.parse(date);
        Integer partitionsLimit = Integer.parseInt(sc.nextLine());
        MessageBroker broker = new MessageBroker(localDateTime, partitionsLimit);
        int topicsCount = Integer.parseInt(sc.nextLine());

        //Adding topics
        for (int i=0;i<topicsCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topicName = parts[0];
            int partitionsCount = Integer.parseInt(parts[1]);
            broker.addTopic(topicName, partitionsCount);
        }

        //Reading messages
        int messagesCount = Integer.parseInt(sc.nextLine());

        System.out.println("===ADDING MESSAGES TO TOPICS===");
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
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
        for (int i=0;i<changesCount;i++){
            String line = sc.nextLine();
            String [] parts = line.split(";");
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
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
                } catch (UnsupportedOperationException | PartitionDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER CONFIGURATION CHANGE===");
        System.out.println(broker);
    }
}

class Message implements Comparable<Message>{
    private LocalDateTime timestamp;
    private String content;
    private Integer partition;
    public String key;

    public Message(LocalDateTime timestamp, String content, String key) {
        this.timestamp = timestamp;
        this.content = content;
        this.key = key;
    }

    public Message(LocalDateTime timestamp, String content, Integer partition, String key) {
        this.timestamp = timestamp;
        this.content = content;
        this.partition = partition;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timestamp.equals(message.timestamp) &&
                content.equals(message.content) &&
                Objects.equals(partition, message.partition) &&
                key.equals(message.key);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public Integer getPartition() {
        return partition;
    }

    public String getKey() {
        return key;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", message='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(Message o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}

class Topic{
    private String name;
    private Map<Integer,TreeSet<Message>> partitions;

    public String getName() {
        return name;
    }

    public Topic(String name, int partitionCount) {
        this.name = name;
        this.partitions = new TreeMap<Integer, TreeSet<Message>>();
        for(int i=1;i<=partitionCount;i++){
            partitions.put(i,new TreeSet<Message>());
        }
    }
    public void addMessage (Message message) throws PartitionDoesNotExistException, UnsupportedOperationException {
        if(message.getPartition()!=null && !partitions.keySet().contains(message.getPartition())) throw new PartitionDoesNotExistException(name,message.getPartition());
        if(message.getPartition()==null) message.setPartition(PartitionAssigner.assignPartition(message,partitions.keySet().size()));
        if(message.getTimestamp().isBefore(MessageBroker.minimumDate)) return;
        partitions.compute(message.getPartition(),
                (k,v)->{
                    if(v.size()>=MessageBroker.limit) {
                        Message m = v.iterator().next();
                        v.remove(m);
                    }
                    v.add(message);
                    return v;
                }
        );
    }
    public void changeNumberOfPartitions (int newPartitionsNumber) throws UnsupportedOperationException {
        if(newPartitionsNumber<partitions.keySet().size()) throw new UnsupportedOperationException();
        for(int i=partitions.keySet().size()+1;i<=newPartitionsNumber;i++){
            partitions.put(i,new TreeSet<Message>());
        }
    }

    @Override
    public String toString() {
        String str = String.format("Topic: %10s Partitions: %5d\n",name,partitions.keySet().size());
        for (Integer x : partitions.keySet()) {
            TreeSet<Message> value = partitions.get(x);
            String instr = String.format("%2d : Count of messages:%6d\nMessages:\n",x,value.size());
            for (Message message : value) {
                instr+=message+"\n";
            }
            str +=instr;
        }
        return str;
    }
}

class MessageBroker{
    static LocalDateTime minimumDate;
    static int limit;

    private List<Topic> topics;

    public MessageBroker(LocalDateTime minimumDate, int limit) {
        this.minimumDate = minimumDate;
        this.limit = limit;
        topics = new ArrayList<Topic>();
    }

    public void addTopic (String topic, int partitionsCount){
        Topic c = new Topic(topic,partitionsCount);
        if(topics.stream().filter(x -> x.getName()==topic).collect(Collectors.toList()).size()>0) return;
        topics.add(c);
    }

    public void addMessage (String topic, Message message) throws PartitionDoesNotExistException, UnsupportedOperationException {
        getTopic(topic).addMessage(message);
    }

    public void changeTopicSettings (String topic, int partitionsCount) throws UnsupportedOperationException {
        getTopic(topic).changeNumberOfPartitions(partitionsCount);
    }

    private Topic getTopic(String topic){
        return topics.stream()
                .filter(x -> x.getName().equals(topic))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public String toString() {
        StringBuilder bl = new StringBuilder();
        String str = String.format("Broker with %2d topics:\n",topics.size());
        bl.append(str);
        topics.stream().sorted(Comparator.comparing(Topic::getName)).forEach(x -> bl.append(x));
        return bl.toString().substring(0,bl.toString().length()-1);
    }
}

class PartitionDoesNotExistException extends Exception{
    public PartitionDoesNotExistException(String topic, int partition) {
        super("The topic "+topic+" does not have a partition with number "+partition);
    }
}
class UnsupportedOperationException extends Exception{
    public UnsupportedOperationException() {
        super();
    }
}
