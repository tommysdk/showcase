package tommysdk.showcase.arquillian.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class FileViewerTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "fileviewer.war")
                .addClass(EnableRest.class)
                .addClass(FileViewer.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(
                    DependencyResolvers.use(MavenDependencyResolver.class)
                        .artifact("commons-io:commons-io:2.1")
                        .artifact("org.json:json:20090211")
                        .resolveAsFiles());
    }

    @Inject
    FileViewer fileViewer;

    @Test
    public void shouldListFiles() {
        Response response = fileViewer.list();
        Assert.assertTrue(((String) response.getEntity()).contains(getAvailableFile()));
    }

    @Test
    public void shouldGetFileContents() {
        Response response = fileViewer.get(getAvailableFile());
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotSame("", response.getEntity());
    }

    @Test
    public void shouldNotGetFileContents() {
        Response response = fileViewer.get("non-existing-file" + System.currentTimeMillis() + ".txt");
        Assert.assertEquals(404, response.getStatus());
        Assert.assertEquals("File not found", response.getEntity());
    }

    @Test
    public void shouldGetFileAsJson() {
        Response response = fileViewer.json(getAvailableFile());
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotSame("", response.getEntity());
        Assert.assertTrue(((String) response.getEntity()).startsWith("{"));
        Assert.assertTrue(((String) response.getEntity()).endsWith("}"));
    }

    private String getAvailableFile() {
        File dir = new File(FileViewer.exposedDirectory);
        String[] files = dir.list();
        if (files.length == 0) return "";
        else return files[0];
    }
}
