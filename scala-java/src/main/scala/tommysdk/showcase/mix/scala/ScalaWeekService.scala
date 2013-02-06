package tommysdk.showcase.mix.scala

import java.util.Calendar
import javax.ejb.{LocalBean, Stateless}

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@LocalBean
class ScalaWeekService {

  def currentWeek() = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)

}
