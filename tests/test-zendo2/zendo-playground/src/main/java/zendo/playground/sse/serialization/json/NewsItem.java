package zendo.playground.sse.serialization.json;

import java.util.Date;

public class NewsItem {

    private long id;
    private String title;
    private String content;
    private Date date;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "NewsItem [id=" + id + ", title=" + title + ", content=" + content + ", date=" + date + "]";
    }

}
