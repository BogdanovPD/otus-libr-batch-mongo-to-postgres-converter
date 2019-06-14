package ru.otus.spring.libr.converter.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.libr.converter.entities.relational.Book;

import java.util.List;

public interface MongoBookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByNameAndAuthor(String name, String author);
    List<Book> findAllByNameAndGenre(String name, String genre);
    List<Book> findAllByAuthorAndGenre(String author, String genre);
    List<Book> findAllByNameAndAuthorAndGenre(String name, String author, String genre);
    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByGenre(String genre);
}
