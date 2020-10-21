package io.redintro.hexgraph.domain.model;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * An example Immutables class that exposes static factory methods rather than
 * builders for new instance creation.
 */
@Value.Immutable(builder = false)
public abstract class Film {
    @Nullable
    @Value.Parameter
    public abstract UUID getId();

    @Value.Parameter
    public abstract String getTitle();

    @Value.Parameter
    public abstract Optional<String> getGenre();

    @Value.Parameter
    public abstract int getLength();

    public static Film getInstance(UUID id, String title, String genre, int length) {
        return ImmutableFilm.of(id, title, Optional.of(genre), length);
    }

    public static Film getInstance(String title, String genre, int length) {
        return ImmutableFilm.of(null, title, Optional.of(genre), length);
    }
}
