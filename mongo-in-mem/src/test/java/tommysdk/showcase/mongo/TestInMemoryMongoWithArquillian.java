package tommysdk.showcase.mongo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class TestInMemoryMongoWithArquillian extends TestInMemoryMongo {

    @Deployment
    public static Archive getDeployment() {
        return ShrinkWrap.create(WebArchive.class, "mongo.war")
                .addClass(MongoPersistenceContext.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new StringAsset("<web-app></web-app>"), "web.xml")
                .addAsLibraries(DependencyResolvers.use(MavenDependencyResolver.class)
                        .artifact("de.flapdoodle.embed:de.flapdoodle.embed.mongo:jar:1.27")
                        .artifact("org.mongodb:mongo-java-driver:jar:2.9.1")
                        .resolveAs(JavaArchive.class));
    }

    @Inject MongoPersistenceContext mpc;

    @Override
    protected MongoPersistenceContext getMongoPersistenceContext() {
        return mpc;
    }
}
