package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
}