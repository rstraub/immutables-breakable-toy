package nl.codecraftr.java.kata;

import java.util.List;
import org.immutables.builder.Builder;

class WikiPageTagger {
    @Builder.Factory
    static WikiPage taggedPage(WikiPage page, String tag) {
        return WikiPageValue
                .builder()
                .from(page)
                .tags(List.of(tag))
                .build();
    }
}
