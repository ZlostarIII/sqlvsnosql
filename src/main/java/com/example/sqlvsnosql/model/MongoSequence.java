package com.example.sqlvsnosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_sequences")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MongoSequence {

    @Id
    private String id;

    private String sequence;

}
