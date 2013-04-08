package tommysdk.showcase.arquillian.rest;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tommysdk.showcase.arquillian.ejb.DocumentManager;
import tommysdk.showcase.arquillian.entity.Document;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class DocumentServiceTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "showcase.jar")
                .addClass(DocumentService.class)
                .addPackage(DocumentManager.class.getPackage())
                .addClass(Document.class)
                .addAsManifestResource(new ClassLoaderAsset("test-persistence.xml"), "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB(mappedName = "java:app/showcase/DocumentService")
    private DocumentService documentService;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    private Long id;

    private String content = "My Document Content";

    @Before
    public void initializeDatabase() throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        Assert.assertNotNull("EntityManager not injected? Check your deployment configuration.", em);
        utx.begin();
        em.joinTransaction();
        Document entity = new Document();
        entity.setContent("{\n   \"content\": \"" + content + "\"\n}");
        em.persist(entity);
        id = entity.getId();
        em.flush();
        utx.commit();
    }

    @Test
    public void shouldGetContent() {
        Response response = documentService.get(id.toString());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getEntity());
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(((String) response.getEntity()).contains(content));
    }

    @Test
    public void shouldReturnErrorResponseOnNullParam() {
        Response response = documentService.get(null);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getEntity());
        Assert.assertEquals(404, response.getStatus());
    }
}
