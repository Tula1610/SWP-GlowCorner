package com.glowcorner.backend.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;


import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestResult {

    @Id
    String id;

    String userID;

    Date testDate;
    String testResult;

}
