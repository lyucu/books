package _15nativesql;

import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

//@Entity
@SqlResultSetMapping(name = "implicit", entities = @EntityResult(entityClass = ImplicitResult.class))
@NamedNativeQuery(name = "implictExample", query = "select * from ImplicitResult", resultSetMapping = "implicit")
public class ImplicitResult {

}
