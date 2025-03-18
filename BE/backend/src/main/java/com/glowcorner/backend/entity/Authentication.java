package com.glowcorner.backend.entity;

import com.glowcorner.backend.entity.mongoDB.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "authentication")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authentication {
    @Id
    String id;

    User userID;

    String username;

    String passwordHash;
}
