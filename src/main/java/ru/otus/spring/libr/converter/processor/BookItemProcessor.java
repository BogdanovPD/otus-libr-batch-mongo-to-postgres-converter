package ru.otus.spring.libr.converter.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring.libr.converter.entities.mongo.MongoBook;
import ru.otus.spring.libr.converter.entities.relational.Author;
import ru.otus.spring.libr.converter.entities.relational.Book;
import ru.otus.spring.libr.converter.entities.relational.Comment;
import ru.otus.spring.libr.converter.entities.relational.Genre;
import ru.otus.spring.libr.converter.repositories.relational.AuthorRepository;
import ru.otus.spring.libr.converter.repositories.relational.BookRepository;
import ru.otus.spring.libr.converter.repositories.relational.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookItemProcessor implements ItemProcessor<MongoBook, Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public Book process(MongoBook mongoBook) {
        Author author = getAuthor(mongoBook.getAuthor());
        Genre genre = getGenre(mongoBook.getGenre());
        Book book = getBook(author, mongoBook.getName(), genre);
        if (book.getId() == 0) {
            List<Comment> comments = mongoBook.getComments().stream().map(comment -> Comment.builder()
                    .text(comment).book(book).build()).collect(Collectors.toList());
            book.setComments(comments);
        }
        return book;
    }

    private Author getAuthor(String author) {
        Optional<Author> authorOptional = authorRepository.getAuthorByName(author);
        return authorOptional.orElse(Author.builder()
                .name(author)
                .build());
    }

    private Genre getGenre(String genre) {
        Optional<Genre> genreOptional = genreRepository.getGenreByName(genre);
        return genreOptional.orElse(Genre.builder()
                .name(genre)
                .build());
    }

    private Book getBook(Author author, String bookName, Genre genre) {
        Optional<Book> bookOptional = bookRepository.findAllByNameAndAuthor_Name(bookName, author.getName());
        return bookOptional.orElse(Book.builder()
                .author(author)
                .genre(genre)
                .name(bookName)
                .build());
    }

}
