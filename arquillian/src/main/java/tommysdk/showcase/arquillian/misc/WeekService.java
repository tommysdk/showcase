package tommysdk.showcase.arquillian.misc;

import javax.ejb.Stateless;
import java.util.Calendar;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
public class WeekService {

  public int weekOfYear() {
      return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
  }

}
