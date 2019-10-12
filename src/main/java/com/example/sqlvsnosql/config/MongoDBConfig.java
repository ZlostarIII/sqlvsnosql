package com.example.sqlvsnosql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.example.sqlvsnosql.repository.mongo")
public class MongoDBConfig {
}
