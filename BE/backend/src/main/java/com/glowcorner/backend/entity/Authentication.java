package com.glowcorner.backend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "authentication")
public class Authentication {
    @Id
    private ObjectId authenticationTokenID;

    private ObjectId userID;

    private String username;

    private String passwordHash;
}
