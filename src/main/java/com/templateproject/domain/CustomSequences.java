package com.templateproject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sequence")
public class CustomSequences {
    @Id
    private String id;
    private long seq;
}
