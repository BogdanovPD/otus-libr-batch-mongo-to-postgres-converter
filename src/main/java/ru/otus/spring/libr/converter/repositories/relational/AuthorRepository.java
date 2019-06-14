package ru.otus.spring.libr.converter.repositories.relational;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.libr.converter.entities.relational.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> getAuthorByName(String name);
    List<Author> findAll();

}
