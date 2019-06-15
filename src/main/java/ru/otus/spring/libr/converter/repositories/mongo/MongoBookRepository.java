package ru.otus.spring.libr.converter.repositories.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.libr.converter.entities.mongo.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {
    Page<MongoBook> findAll(Pageable pageable);
}
