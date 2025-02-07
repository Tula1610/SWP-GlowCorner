package com.glowcorner.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Authentication {
    @Id
    private int authenticationTokenID;
    private int userID;
    private String userName;
    private String passwordHash;
}
