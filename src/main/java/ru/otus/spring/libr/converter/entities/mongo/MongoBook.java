package ru.otus.spring.libr.converter.entities.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "comments"})
@ToString(exclude = {"id", "comments"})
@Builder
@Document(collection = "libr")
public class MongoBook {

    @Id
    private String id;

    private String author;
    private String genre;
    private String name;

    private List<String> comments = new ArrayList<>();

}