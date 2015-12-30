package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Produce {
    @Id
    @Basic
    private Integer id;
    @Basic
    private String sku;
    @Basic
    private String name;
    @Basic
    @Column(name = "NOTES")
    private String description;

    @Enumerated(EnumType.STRING)
    public Tp tp;

    @Enumerated(EnumType.ORDINAL)
    public Tp tp1;
}

enum Tp {
    ONE(1), TWO(2);
    private final int count;

    private Tp(int count) {
        this.count = count;
    }

}