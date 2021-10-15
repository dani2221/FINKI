package mk.ukim.finki.aud9_kolokviumski_2.messageSystem;

public class PartitionDoesNotExistException extends Exception{
    public PartitionDoesNotExistException(String topic, Integer partition) {
        super(String.format("The topic %s does not have a partition with number %d", topic, partition));
    }
}
