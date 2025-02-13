package com.glowcorner.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "feedback") // Maps this entity to the "feedbacks" MongoDB collection
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackID; // Primary key

    private int customerID; // Refers to the userID in the User collection (foreign key in SQL)

    private int rating; // Rating is represented as an integer

    private String comment; // Comment is optional and maps to NVARCHAR(255)

    private LocalDate feedbackDate; // Maps to the SQL DATE type
}