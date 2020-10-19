package io.redintro.hexgraph.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FilmTest {
    @Test
    void shouldConstructFilm() {
        Film film = Film.getInstance(
                UUID.fromString("897b9a8f-4254-4593-a118-97f657a8e069"),
                "One Flew Over The Cuckoos Nest",
                "Noir",
                185);

        assertThat(film.getId(), is(equalTo(UUID.fromString("897b9a8f-4254-4593-a118-97f657a8e069"))));
        assertThat(film.getTitle(), is(equalTo("One Flew Over The Cuckoos Nest")));
        assertThat(film.getGenre(), is(equalTo(Optional.of("Noir"))));
        assertThat(film.getLength(), is(equalTo(185)));
    }

    @Test
    void shouldConstructFilmWithoutId() {
        Film film = Film.getInstance(
                "One Flew Over The Cuckoos Nest",
                "Noir",
                185);

        assertThat(film.getId(), is(nullValue()));
        assertThat(film.getTitle(), is(equalTo("One Flew Over The Cuckoos Nest")));
        assertThat(film.getGenre(), is(equalTo(Optional.of("Noir"))));
        assertThat(film.getLength(), is(equalTo(185)));
    }
}
