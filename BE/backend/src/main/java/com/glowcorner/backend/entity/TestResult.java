package com.glowcorner.backend.entity;


import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "testresult")


public class TestResult {

    private int testResultID;
    private int userID;
    private Date date;
    private String testResult;

}
