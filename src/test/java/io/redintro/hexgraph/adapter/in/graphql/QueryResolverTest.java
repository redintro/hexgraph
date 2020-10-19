package io.redintro.hexgraph.adapter.in.graphql;

import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import io.redintro.hexgraph.port.in.BookViewPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QueryResolverTest {

    @Mock
    private AuthorViewPort authorViewPort;

    @Mock
    private BookViewPort bookViewPort;

    @InjectMocks
    QueryResolver queryResolver;

    @Test
    public void findAllBooks() {
        when(bookViewPort.showAll()).thenReturn(List.of(bookFixture()));

        List<Book> books =  queryResolver.findAllBooks();

        assertThat(books.size(), is(equalTo(1)));
    }

    @Test
    public void shouldFindBookById() {
        when(bookViewPort.read(any(UUID.class))).thenReturn(bookFixture());

        Book book = queryResolver.findBookById(bookFixture().getId());

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldFindAllAuthors() {
        when(authorViewPort.showAll()).thenReturn(List.of(authorFixture()));

        List<Author> authors =  queryResolver.findAllAuthors();

        assertThat(authors.size(), is(equalTo(1)));
    }

    @Test
    public void shouldFindAuthorById() {
        when(authorViewPort.read(any(UUID.class))).thenReturn(authorFixture());

        Author author = queryResolver.findAuthorById(authorFixture().getId());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldCountBooks() {
        when(bookViewPort.count()).thenReturn(1L);

        long count = queryResolver.countBooks();

        assertThat(count, is(equalTo(1L)));
    }

    @Test
    public void shouldCountAuthors() {
        when(authorViewPort.count()).thenReturn(1L);

        long count = queryResolver.countAuthors();

        assertThat(count, is(equalTo(1L)));
    }

    private Author authorFixture() {
        return new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                "Don", "DeLillo");
    }

    private Book bookFixture() {
        return new Book(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"),
                "Americana", "0123456789-10", 367,
                new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                        "Don", "DeLillo"));
    }
}
