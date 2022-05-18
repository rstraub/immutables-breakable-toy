package nl.codecraftr.java.kata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WikiPageTest {
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
        assertThat(true).isFalse();
    }

    @Test
    void shouldSerialize() {
        assertThat(true).isFalse();
    }
}
