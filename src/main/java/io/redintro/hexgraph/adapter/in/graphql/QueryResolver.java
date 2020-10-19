package io.redintro.hexgraph.adapter.in.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import io.redintro.hexgraph.port.in.BookViewPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryResolver.class);

    private final AuthorViewPort authorViewPort;
    private final BookViewPort bookViewPort;

    public QueryResolver(AuthorViewPort authorViewPort, BookViewPort bookViewPort) {
        this.authorViewPort = authorViewPort;
        this.bookViewPort = bookViewPort;
    }

    @Transactional
    public List<Book> findAllBooks() {
        return bookViewPort.showAll();
    }

    public Book findBookById(UUID id) {
        return bookViewPort.read(id);
    }

    public List<Author> findAllAuthors() {
        return authorViewPort.showAll();
    }

    public Author findAuthorById(UUID id) {
        return authorViewPort.read(id);
    }

    public long countBooks() {
        return bookViewPort.count();
    }

    public long countAuthors() {
        return authorViewPort.count();
    }
}
