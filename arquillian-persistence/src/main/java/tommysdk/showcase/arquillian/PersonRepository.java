package tommysdk.showcase.arquillian;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@LocalBean
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(final Person person) {
        entityManager.persist(person);
    }

    public int countPersons() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Person.class)));
        return entityManager.createQuery(cq).getSingleResult().intValue();
    }

}
