package tommysdk.showcase.arquillian.ejb;

import tommysdk.showcase.arquillian.entity.Document;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DocumentEntityManager implements DocumentManager {

    @PersistenceContext(unitName = "DocumentContext")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document get(final String id) {
        if (id == null) return null;
        Query q = getEntityManager().createQuery("select d from Document d where d.id = " + id);
        q.setMaxResults(1);
        q.setHint("javax.persistence.query.timeout", "5000");
        List results = q.getResultList();
        if (results == null || results.isEmpty()) return null;
        else return (Document) results.get(0);
    }
}
