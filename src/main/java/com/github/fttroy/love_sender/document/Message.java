package com.github.fttroy.love_sender.document;

import com.github.fttroy.love_sender.model.Text;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "message")
public class Message {
    @Id
    private ObjectId id;
    private List<Text> texts;
    private String emailAdress;
}
