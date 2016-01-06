package _15nativesql;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;

//@Entity
@Table(name = "book")
@SQLInsert(sql = "inset into book (id) values (?)")
@SQLUpdate(sql = "update book set title = ?")
@SQLDelete(sql = "delete book where id = ?")
@SQLDeleteAll(sql = "delete book")
@Loader(namedQuery = "chaos")
public class CustomerSql {

    @Id
    private int id;

    @OneToMany
    @JoinColumn(name = "fd_fl")
    @SQLInsert(sql = "update tempSet set cha = ?")
    @SQLDelete(sql = "update tempSet set cha= null")
    @Loader(namedQuery = "chaos")
    private Set<Temp> tempSet = new HashSet<Temp>();
}

class Temp {
}
