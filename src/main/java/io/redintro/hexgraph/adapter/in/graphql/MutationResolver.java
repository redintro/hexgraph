package io.redintro.hexgraph.adapter.in.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import io.redintro.hexgraph.port.in.BookViewPort;
import io.redintro.hexgraph.shared.BookNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    private final AuthorViewPort authorViewPort;
    private final BookViewPort bookViewPort;

    public MutationResolver(AuthorViewPort authorViewPort, BookViewPort bookViewPort) {
        this.authorViewPort = authorViewPort;
        this.bookViewPort = bookViewPort;
    }

    public Author newAuthor(String firstName, String lastName) {
        return authorViewPort.create(new Author(firstName, lastName));
    }

    public Book newBook(String title, String isbn, int pageCount, UUID authorId) {
        return  bookViewPort.create(new Book(title, isbn, pageCount, new Author(authorId)));
    }

    public boolean deleteBook(UUID id) {
        return bookViewPort.delete(id);
    }

    public Book updateBookPageCount(Integer pageCount, UUID id) {
        Book book = bookViewPort.read(id);
        if(book == null) {
            throw new BookNotFoundException("BookJpaEntity not found", id);
        }
        Book updatedBook = new Book(book.getId(), book.getTitle(), book.getIsbn(), pageCount, book.getAuthor());
        return bookViewPort.update(updatedBook);
    }
}
