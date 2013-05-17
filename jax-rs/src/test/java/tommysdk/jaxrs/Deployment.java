package tommysdk.jaxrs;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author Tommy Tynj&auml;
 */
public class Deployment {

    public static WebArchive forRestfulService() {
        return ShrinkWrap.create(WebArchive.class, "jax-rs.war")
                .addClasses(RestEnabler.class, RestfulService.class)
                .addAsWebInfResource(new StringAsset("<web-app></web-app>"), "web-.xml");
    }
}
