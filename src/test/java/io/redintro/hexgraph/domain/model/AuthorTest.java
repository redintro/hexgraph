package io.redintro.hexgraph.domain.model;

import io.redintro.hexgraph.domain.model.Author;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthorTest {
    @Test
    public void shouldCreateAuthorWithId() {
        Author author = new Author(authorFixture().getId());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(nullValue()));
        assertThat(author.getLastName(), is(nullValue()));
    }

    @Test
    public void shouldCreateAuthorWithFirstAndLastName() {
        Author author = new Author(authorFixture().getFirstName(), authorFixture().getLastName());

        assertThat(author.getId(), is(nullValue()));
        assertThat(author.getFirstName(), is(equalTo(authorFixture().getFirstName())));
        assertThat(author.getLastName(), is(equalTo(authorFixture().getLastName())));
    }

        @Test
    public void shouldCreateAuthor() {
        Author author = authorFixture();

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo(authorFixture().getFirstName())));
        assertThat(author.getLastName(), is(equalTo(authorFixture().getLastName())));
    }

    @Test
    public void shouldToString() {
        Author author = authorFixture();
        String authorToString = "Author {" +
                "id=" + authorFixture().getId() +
                ", firstName='" + authorFixture().getFirstName() + '\'' +
                ", lastName='" + authorFixture().getLastName() + '\'' +
                '}';;

        assertThat(author.toString(), is(equalTo(authorToString)));
    }

    private Author authorFixture() {
        return new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                "Don", "DeLillo");
    }
}
