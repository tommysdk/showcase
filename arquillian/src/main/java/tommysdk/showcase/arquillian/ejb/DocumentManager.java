package tommysdk.showcase.arquillian.ejb;

import tommysdk.showcase.arquillian.entity.Document;

import javax.ejb.Local;

/**
 * @author Tommy Tynj&auml;
 */
@Local
public interface DocumentManager {

    public Document get(final String id);

}
