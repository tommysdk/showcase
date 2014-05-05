package tommysdk.showcase.arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class JpaPersistenceTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "jpa-test.jar")
                .addClass(Person.class)
                .addClass(PersonRepository.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private PersonRepository personRepository;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction tx;

    @Before
    public void prepareTestCase() throws Exception {
        purgeDatabase();
        insertTestData();
        startTransaction();
    }

    private void purgeDatabase() throws Exception {
        tx.begin();
        em.joinTransaction();
        em.createQuery("delete from Person").executeUpdate();
        tx.commit();
    }

    private void insertTestData() throws Exception {
        tx.begin();
        em.joinTransaction();
        em.persist(
                new Person()
                        .withFirstName("Ernest")
                        .withSurname("Hemingway")
                        .withAddress("907 Whitehead Street")
                        .withCity("Key West, FL"));
        tx.commit();
        em.clear();
    }

    private void startTransaction() throws Exception {
        tx.begin();
        em.joinTransaction();
    }

    @After
    public void postTestCase() throws Exception {
        tx.commit();
    }

    @Test
    public void shouldCountEntities() throws Exception {
        assertThat(personRepository.countPersons(), is(1));
    }
}
