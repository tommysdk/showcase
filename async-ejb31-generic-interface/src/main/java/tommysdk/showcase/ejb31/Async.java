package tommysdk.showcase.ejb31;

import javax.ejb.Local;

/**
 * @author Tommy Tynj&auml;
 */
@Local(Async.class)
public interface Async<TYPE> {

    public void method(TYPE t);

}
