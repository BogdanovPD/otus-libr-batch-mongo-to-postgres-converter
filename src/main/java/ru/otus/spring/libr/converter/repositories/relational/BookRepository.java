package ru.otus.spring.libr.converter.repositories.relational;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.libr.converter.entities.relational.Author;
import ru.otus.spring.libr.converter.entities.relational.Book;
import ru.otus.spring.libr.converter.entities.relational.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findAllByAuthorAndName(Author author, String name);
    List<Book> findAllByNameAndAuthor_Name(String name, String authorName);

}
