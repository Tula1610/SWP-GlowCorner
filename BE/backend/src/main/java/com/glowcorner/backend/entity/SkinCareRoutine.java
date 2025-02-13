package com.glowcorner.backend.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skincareroutine")

public class SkinCareRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routineID;
    private String skinType;
    private String routineName;
    private String Description;
}
