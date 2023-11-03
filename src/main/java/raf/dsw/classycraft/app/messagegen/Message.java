package raf.dsw.classycraft.app.messagegen;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message{

    public String text;
    public MessageType type;
    public LocalDateTime timestamp;


    public Message(MessageType messageType, LocalDateTime timestamp, String message) {
        this.type= messageType;
        this.timestamp = timestamp;
        this.text = message;
    }

    @Override
    public String toString() {
        return "["+type.toString() +"] [" + timestamp + "] [" + text + "]";
    }

}
