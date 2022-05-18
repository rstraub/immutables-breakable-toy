package nl.codecraftr.java.kata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WikiPageTest {

    @Test
    void shouldCreateImmutableUsingBuilder() {
        var result = ImmutableWikiPage.builder()
                .author("henk")
                .isActive(true)
                .build();

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldOfferCopyMethods() {
        var result = ImmutableWikiPage.builder()
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
        var initial = ImmutableWikiPage.builder()
                .author("henk")
                .isActive(true)
                .build();

        var result = ImmutableWikiPage.builder().from(initial).build();

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }

    @Test
    void shouldHaveToStringImplementation() {
        var result = ImmutableWikiPage.builder()
                .author("henk")
                .isActive(true)
                .build();

        assertThat(result.toString()).isEqualTo("WikiPage{isActive=true, author=henk}");
    }

    @Test
    void shouldAllowOverridingBuilderState() {
        var builder = ImmutableWikiPage.builder()
                .author("henk")
                .isActive(true);

        var one = builder.build();
        var two = builder.isActive(false).build();

        assertThat(one.isActive()).isTrue();
        assertThat(two.isActive()).isFalse();
    }

    @Test
    void shouldAllowConstructorWithParameterAnnotation() {
        var result = ImmutableWikiPage.of("henk", true);

        assertThat(result.author()).isEqualTo("henk");
        assertThat(result.isActive()).isTrue();
    }
}
