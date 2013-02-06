package tommysdk.showcase.mix

import _root_.java.util.Calendar
import org.jboss.arquillian.junit.Arquillian
import org.junit.runner.RunWith
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.junit.Test
import org.junit.Assert._
import scala.ScalaWeekService
import org.jboss.arquillian.container.test.api.Deployment
import javax.inject.Inject
import org.jboss.shrinkwrap.api.asset.StringAsset

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(classOf[Arquillian])
object ScalaIntegrationTest {

  @Deployment
  def deployment(): JavaArchive = {
    val jar = ShrinkWrap.create(classOf[JavaArchive], "week.jar")
    jar addClass classOf[ScalaWeekService]
    jar addAsManifestResource(new StringAsset("<beans/>"), "beans.xml")
  }
}

@RunWith(classOf[Arquillian])
class ScalaIntegrationTest {

  @Inject
  var weekService : ScalaWeekService = null

  @Test
  def shouldProduceGreenBar() {
    assertTrue(true)
  }

  @Test
  def shouldReturnCurrentWeek() {
    assertNotNull("Bean not injected?", weekService)
    assertEquals(Calendar.getInstance.get(Calendar.WEEK_OF_YEAR), weekService.currentWeek())
  }
}
