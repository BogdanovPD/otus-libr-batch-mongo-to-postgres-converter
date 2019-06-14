package ru.otus.spring.libr.converter.repositories.relational;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.libr.converter.entities.relational.Book;
import ru.otus.spring.libr.converter.entities.relational.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByBook(Book book);

}
