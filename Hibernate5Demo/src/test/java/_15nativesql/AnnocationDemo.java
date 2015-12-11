package _15nativesql;

import java.awt.print.Book;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@NamedNativeQuery(name = "queryName", query = "select * from Book book where book.id = :id", resultSetMapping = "joinMapping")
@SqlResultSetMapping(name = "joinMapping", entities = { @EntityResult(entityClass = Book.class, fields = {
        @FieldResult(column = "id", name = "id"), @FieldResult(column = "title", name = "title"),
        @FieldResult(column = "columnName", name = ".auther.id") }) })
public class AnnocationDemo {

}
