package io.redintro.hexgraph.adapter.out.persistence;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthorJpaEntityTest {
    @Test
    public void shouldCreateAuthorJpaEntityWithNoArgs() {
        AuthorJpaEntity authorJpaEntity = new AuthorJpaEntity();

        assertThat(authorJpaEntity, is(notNullValue()));
        assertThat(authorJpaEntity.getId(), is(nullValue()));
        assertThat(authorJpaEntity.getFirstName(), is(nullValue()));
        assertThat(authorJpaEntity.getLastName(), is(nullValue()));
        assertThat(authorJpaEntity.getBooks(), is(nullValue()));
    }

    @Test
    public void shouldCreateAuthorJpaEntityWithoutId() {
        AuthorJpaEntity authorJpaEntity = new AuthorJpaEntity(authorJpaEntityFixture().getFirstName(),
                authorJpaEntityFixture().getLastName());

        assertThat(authorJpaEntity.getId(), is(nullValue()));
        assertThat(authorJpaEntity.getFirstName(), is(equalTo(authorJpaEntityFixture().getFirstName())));
        assertThat(authorJpaEntity.getLastName(), is(equalTo(authorJpaEntityFixture().getLastName())));
        assertThat(authorJpaEntity.getBooks(), is(nullValue()));
    }

    @Test
    public void shouldCreateAuthorJpaEntity() {
        AuthorJpaEntity authorJpaEntity = authorJpaEntityFixture();

        assertThat(authorJpaEntity.getId(), is(equalTo(authorJpaEntityFixture().getId())));
        assertThat(authorJpaEntity.getFirstName(), is(equalTo(authorJpaEntityFixture().getFirstName())));
        assertThat(authorJpaEntity.getLastName(), is(equalTo(authorJpaEntityFixture().getLastName())));
        assertThat(authorJpaEntity.getBooks(), is(nullValue()));
    }

    private AuthorJpaEntity authorJpaEntityFixture() {
        return new AuthorJpaEntity(UUID.fromString("ffefef7e-0ff3-4951-84c4-2dabfefc8213"), "Don", "DeLillo");
    }
}
