package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.Author;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthorOutMapperTest {
    @Test
    public void shouldCreateAuthorOutputMapper() {
        AuthorOutMapper authorOutMapper = new AuthorOutMapper();

        assertThat(authorOutMapper, is(notNullValue()));
    }

    @Test
    public void shouldMapToDomainEntity() {
        UUID authorId = UUID.randomUUID();

        AuthorJpaEntity authorJpaEntity = new AuthorJpaEntity(authorId, "Don", "DeLillo");

        Author author = AuthorOutMapper.mapToDomainEntity(authorJpaEntity);

        assertThat(author.getId(), is(equalTo(authorId)));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldMapToJpaEntity() {
        UUID authorId = UUID.randomUUID();

        Author author = new Author(authorId, "Don", "DeLillo");

        AuthorJpaEntity authorJpaEntity = AuthorOutMapper.mapToJpaEntity(author);

        assertThat(authorJpaEntity.getId(), is(equalTo(authorId)));
        assertThat(authorJpaEntity.getFirstName(), is(equalTo("Don")));
        assertThat(authorJpaEntity.getLastName(), is(equalTo("DeLillo")));
    }
}
