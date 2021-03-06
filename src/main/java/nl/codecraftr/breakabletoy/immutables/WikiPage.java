package nl.codecraftr.breakabletoy.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
        typeImmutable = "*Value"
)
@JsonSerialize(as = WikiPageValue.class)
@JsonDeserialize(as = WikiPageValue.class)
public interface WikiPage extends Serializable {
    @Value.Parameter(order = 1)
    @Value.Default
    default boolean isActive() {
        return true;
    }

    @Value.Derived
    // Auxiliary excludes the property from generated methods like hashCode, toString and equals
    @Value.Auxiliary
    default boolean isInactive() {
        return !isActive();
    }

    @Value.Parameter(order = 0)
    String author();

    // Downside of this construction with inheritance is that these precondition methods can't be private
    @Value.Check
    default void nonEmptyAuthor() {
        if (author().isBlank()) throw new IllegalArgumentException("Author cannot be blank");
    }

    Optional<String> coauthor();

    // Implicitly gets a default empty collection
    List<String> tags();

    // Lazy is implicitly auxiliary
    @Value.Lazy
    default int countTags() {
        System.out.println("Counting tags...");
        return tags().size();
    }
}
