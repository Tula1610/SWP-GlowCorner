package com.glowcorner.backend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "role")
public class Role {

    @Id
    private ObjectId roleID;

    private String roleName;

    private String description;

}