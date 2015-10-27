package tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "mTitle")
    private String title;
    private String content;

    public Message() {
    }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
