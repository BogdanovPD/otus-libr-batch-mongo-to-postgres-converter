package ru.otus.spring.libr.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.libr.converter.repositories.mongo.MongoBookRepository;
import ru.otus.spring.libr.converter.repositories.relational.BookRepository;

@EnableMongoRepositories(basePackageClasses = MongoBookRepository.class)
@EnableJpaRepositories(basePackageClasses = BookRepository.class)
@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

}
