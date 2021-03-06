package nl.codecraftr.breakabletoy.immutables;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImmutableWikiPageTest {
    private WikiPageValue.Builder A_PAGE;

    @BeforeEach
    void setUp() {
        A_PAGE = WikiPageValue.builder().author("anonymous");
    }

    @Test
    void shouldCreateImmutableUsingBuilder() {
        var result = WikiPageValue.builder()
                .author("henk")
                .isActive(true)
                .build();

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldOfferCopyMethods() {
        var result = WikiPageValue.builder()
                .author("henk")
                .isActive(true)
                .build()
                .withAuthor("jan")
                .withIsActive(false);

        assertThat(result.author()).isEqualTo("jan");
        assertThat(result.isActive()).isFalse();
    }

    @Test
    void shouldPopulateBuilderWithFromMethod() {
        var initial = WikiPageValue.builder()
                .author("henk")
                .isActive(true)
                .build();

        var result = WikiPageValue.builder().from(initial).build();

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldHaveToStringImplementation() {
        var result = WikiPageValue.builder()
                .author("henk")
                .isActive(true)
                .build();

        assertThat(result.toString()).isEqualTo("WikiPage{isActive=true, author=henk, tags=[]}");
    }

    @Test
    void shouldAllowOverridingBuilderState() {
        var builder = A_PAGE
                .isActive(true);

        var one = builder.build();
        var two = builder.isActive(false).build();

        assertThat(one.isActive()).isTrue();
        assertThat(two.isActive()).isFalse();
    }

    @Test
    void shouldAllowConstructorWithParameterAnnotation() {
        var result = WikiPageValue.of("henk", true);

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldThrowExceptionGivenPartialInitialization() {
        assertThatThrownBy(() -> WikiPageValue.builder()
                .isActive(true)
                .build())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldAllowDefaultAttributes() {
        var result = WikiPageValue.builder().author("henk").build();

        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldAllowDerivedAttributes() {
        var result = A_PAGE.isActive(true).build();

        assertThat(result.isInactive()).isFalse();
    }

    @Test
    void shouldAllowCollections() {
        var result = A_PAGE
                .addTags("tech", "books")
                .build();

        assertThat(result.tags()).contains("tech", "books");
    }

    @Test
    void shouldAllowOptionals() {
        var result = A_PAGE.coauthor("daisy").build();

        assertThat(result.coauthor()).hasValue("daisy");
    }

    @Test
    void shouldHaveEmptyOptionalByDefault() {
        var result = A_PAGE.build();

        assertThat(result.coauthor()).isEmpty();
    }

    @Test
    void shouldAllowLazyAttributes() {
        var result = A_PAGE
                .addTags("a", "b", "c")
                .build();

        // Print statement shows the value is calculated once, even though we call the method twice.
        assertThat(result.countTags()).isNotNegative();
        assertThat(result.countTags()).isEqualTo(3);
    }

    @Test
    void shouldSerialize() throws IOException, ClassNotFoundException {
        var wrotePage = WikiPageValue.builder()
                .author("henk")
                .isActive(true)
                .addTags("1", "2")
                .build();

        FileOutputStream fileOutputStream
                = new FileOutputStream("./wikipage.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(wrotePage);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("./wikipage.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        var readPage = (WikiPageValue) objectInputStream.readObject();
        objectInputStream.close();

        assertThat(readPage).isEqualTo(wrotePage);
    }

    @Test
    void shouldAllowInvariantProtection() {
        assertThatThrownBy(() -> A_PAGE.author("").build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Author cannot be blank");
    }

    @Test
    void shouldAllowFactoryMethods() {
        var taggedPage = new TaggedPageBuilder()
                .page(A_PAGE.build().withAuthor("Johnny Tag"));

        var one = taggedPage.tag("1").build();
        var two = taggedPage.tag("2").build();

        assertThat(one.tags()).containsExactly("1");
        assertThat(two.tags()).containsExactly("2");
    }

    @Test
    void shouldBeMorePowerfulThanRecords() {
        var rec = new WikiPageRecord("henk", true, emptyList());

        // Records offer equals, hashCode, toString and some other stuff.
        // They DO NOT offer copy methods unlike case classes, data classes, or Immutables implementation.
        // This makes them unwieldy in a functional programming style
        assertThat(rec.toString()).isEqualTo("WikiPageRecord[author=henk, isActive=true, tags=[]]");
    }

    @Test
    void shouldAllowPatternStyleObjects() {
        var authors = AuthorsTuple.of("Henk", "Jan");

        assertThat(authors.author()).isEqualTo("Henk");
        assertThat(authors.coauthor()).isEqualTo("Jan");
    }

    @Test
    void shouldSerializeJson() throws JsonProcessingException {
        var page = WikiPageValue.builder()
                .author("John")
                .isActive(true)
                .addTags("1", "2")
                .build();

        var objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(page);

        assertThat(json).isEqualTo("""
                {
                    "author":"John",
                    "coauthor":{"empty":true,"present":false},
                    "tags":["1","2"],
                    "isActive":true,
                    "isInactive":false
                 }
                """.trim().replace("\n", "").replace(" ", ""));
    }
}
