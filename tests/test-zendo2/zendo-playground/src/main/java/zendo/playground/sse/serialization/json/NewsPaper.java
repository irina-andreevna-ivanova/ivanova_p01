package zendo.playground.sse.serialization.json;

import java.util.HashSet;
import java.util.Set;

public class NewsPaper {

    private String title;
    private int issueNr;
    private Set<NewsItem> items = new HashSet<NewsItem>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "NewsPaper [title=" + title + ", issueNr=" + issueNr + ", items=" + items + "]";
    }

}
