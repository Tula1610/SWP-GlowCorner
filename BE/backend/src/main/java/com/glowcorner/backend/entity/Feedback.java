package com.glowcorner.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Feedback {

    @Id
    int feedbackID; // Primary key

    int customerID; // Refers to the userID in the User collection (foreign key in SQL)

    int rating; // Rating is represented as an integer

    String comment; // Comment is optional and maps to NVARCHAR(255)

    LocalDate feedbackDate; // Maps to the SQL DATE type
}