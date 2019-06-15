package ru.otus.spring.libr.converter.repositories.relational;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.libr.converter.entities.relational.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findAllByNameAndAuthor_Name(String name, String authorName);

}
