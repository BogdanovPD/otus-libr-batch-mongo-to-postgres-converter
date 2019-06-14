package ru.otus.spring.libr.converter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.libr.converter.entities.mongo.MongoBook;
import ru.otus.spring.libr.converter.entities.relational.Book;
import ru.otus.spring.libr.converter.listeners.JobNotificationListener;
import ru.otus.spring.libr.converter.processor.BookItemProcessor;
import ru.otus.spring.libr.converter.repositories.mongo.MongoBookRepository;
import ru.otus.spring.libr.converter.repositories.relational.BookRepository;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final MongoBookRepository mongoBookRepository;
    private final BookRepository bookRepository;
    private final BookItemProcessor bookItemProcessor;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public RepositoryItemReader<MongoBook> reader() {
        var reader = new RepositoryItemReader<MongoBook>();
        reader.setRepository(mongoBookRepository);
        reader.setMethodName("findAll");
        return reader;
    }

    @Bean
    public BookItemProcessor processor() {
        return bookItemProcessor;
    }

    @Bean
    public RepositoryItemWriter<Book> writer() {
        var writer = new RepositoryItemWriter<Book>();
        writer.setRepository(bookRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<MongoBook, Book> chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job migrationJob(JobNotificationListener listener, Step step) {
        return jobBuilderFactory.get("migrationJob")
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

}
