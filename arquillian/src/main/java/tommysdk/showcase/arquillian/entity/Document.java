package tommysdk.showcase.arquillian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Tommy Tynj&auml;
 */
@Entity
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public Document() {
    }

    public Document(final String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
